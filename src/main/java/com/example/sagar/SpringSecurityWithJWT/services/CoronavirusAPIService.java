package com.example.sagar.SpringSecurityWithJWT.services;


import com.example.sagar.SpringSecurityWithJWT.model.LocationStats;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusAPIService {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> statsList=new ArrayList<>();

    public  List<LocationStats> getStatsList()
    {
        return statsList;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *") //s-m-h-d-d  it runs first hour of every day
    public void FetchData() throws IOException, InterruptedException {
        List<LocationStats> newStats=new ArrayList<>();
        HttpClient httpClient=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

      HttpResponse<String> httpResponse=  httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        StringReader csvBodyReader=new StringReader(httpResponse.body());
      Iterable<CSVRecord> records= CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
      for (CSVRecord record:records)
      {
          String state=record.get("Province/State");
          String country=record.get("Country/Region");
          int latestCases = Integer.parseInt(record.get(record.size() - 1));//getting only last entry
          int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
          newStats.add(new LocationStats(
                  state,
                  country,
                  latestCases,
                  latestCases-prevDayCases

                  )
          );

      }
      this.statsList=newStats;


    }

}
