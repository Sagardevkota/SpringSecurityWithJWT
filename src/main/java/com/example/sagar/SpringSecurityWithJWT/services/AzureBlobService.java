package com.example.sagar.SpringSecurityWithJWT.services;
/*
 * @created 27/{03}/2021 - 1:28 PM
 * @project azure-blob
 * @author SAGAR DEVKOTA
 */

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AzureBlobService {

    private final BlobContainerClient containerClient;

    @Autowired
    AzureBlobService(BlobContainerClient blobContainerClient){
        this.containerClient = blobContainerClient;
    }

    public List<String> listFiles() {
        List<String> list = new ArrayList<String>();
        for (BlobItem blobItem : containerClient.listBlobs()) {
            list.add(blobItem.getName());
        }
        return list;
    }

    public ByteArrayOutputStream downloadFile(String blobitem) {
        BlobClient blobClient = containerClient.getBlobClient(blobitem);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        blobClient.download(os);
        return os;
    }

    public String storeFile(String filename, InputStream content, long length) {
        //This class provides a client that contains generic blob operations for Azure Storage Blobs.
        // Operations allowed by the client are downloading and copying a blob, retrieving and setting metadata, retrieving and setting HTTP headers, and deleting and un-deleting a blob.
        BlobClient client = containerClient.getBlobClient(filename);
        //set headers to this otherwise it will be application/octet and image will be downloaded instead of viewing
        BlobHttpHeaders blobHttpHeaders = new BlobHttpHeaders();
        blobHttpHeaders.setContentType("image/jpeg");

        if (!client.exists()){
            client.upload(content,length);
            client.setHttpHeaders(blobHttpHeaders); //set it after upload otherwise client will return null
            log.info("Successfully uploaded "+filename);
            return "File uploaded with success!";
        }

        else {
            client.upload(content,length,true);
            client.setHttpHeaders(blobHttpHeaders);
            log.info("File overwritten "+filename);
            return "File overwritten";
        }



    }

    public String deleteFile(String fileName){
        BlobClient client = containerClient.getBlobClient(fileName);
        if (client.exists()){
            client.delete();//delete if file exists
            return "Deleted Successfully";
        }
        else return "File doesnt exist";




    }
}
