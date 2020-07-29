package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.controller.OrderResponse;
import com.example.sagar.SpringSecurityWithJWT.model.Feedback;
import com.example.sagar.SpringSecurityWithJWT.model.ProductResponse;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.repository.FeedbackRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
   private OrderService orderService;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;


    public Boolean isVerified(String userName){
        return   repository.isVerified(userName);
    }

    public String register(User user){

       repository.save(new User(user.getUserName(),new BCryptPasswordEncoder().encode(user.getPassword()),user.getDeliveryAddress(),user.getPhone(),user.getRole(),user.getAge(),user.getGender(),user.getLatitude(),user.getLongitude()));

       return "ok";

    }

    public int getUserId(String userName){
        return repository.getUserId(userName);
    }

    public int checkifUserExists(String userName){
        return repository.checkIfUserExist(userName).size();
    }
    public int checkIfUserPhoneExists(String phone){
        return repository.checkIfUserPhoneExist(phone).size();
    }


    public User findUserDetails(String userName){
        return repository.findAllByUserName(userName);
    }


    public String updateEmail(Integer userId,String newUserName){
        if (checkifUserExists(newUserName)>0)
            return "User already exists";
        else {
            repository.updateUserName(userId,newUserName);
            return "Updated Email";
        }
    }

    public String updatePhone(Integer userId, String newPhone) {
        if (checkIfUserPhoneExists(newPhone)>0)
            return "Phone is taken";
        else {
            repository.updatePhone(userId,newPhone);
            return "Updated Phone";
        }

    }

    public String updateDelivery(Integer userId, String newDelivery) {
        repository.updateDelivery(userId,newDelivery);
        return "Updated Delivery";
    }


    public String getUserRole(String userName) {
        return repository.getRole(userName);
    }

    public String getSellerName(Integer seller_id){

        return repository.getSellerName(seller_id);

    }


    public List<ProductResponse> getNearbyPeopleOrders(Integer userId){

        List<ProductResponse> productResponses=new ArrayList<>();

        //get latlng
     User user=   repository.findAllByUserId(userId);
     Double latitude=user.getLatitude();
     Double longitude=user.getLongitude();

        List<OrderResponse> Totalorders=new ArrayList<>();
        //get Ids of nearBy people
        List<Integer> user_Id=  findNearByUser(latitude,longitude,userId);

  //get orders of those ids
    for (Integer id:user_Id){
        List<OrderResponse> orderResponses=orderService.getOrdersResponse(id,"completed");
        for (OrderResponse orderResponse:orderResponses)
        {
            Totalorders.add(new OrderResponse(orderResponse));
        }


    }

    List<Products> products=new ArrayList<>();

    //get productIds
        Integer [] productId=new Integer[Totalorders.size()];
        int i=0;
    for (OrderResponse orderResponse:Totalorders){
        productId[i]=orderResponse.getProduct_id();
        i++;
    }

        Arrays.sort(productId);
    int n=productId.length;

    n=removeDuplicates(productId,n);

    //get products
        for (int count=0;count<n;count++){
            products.add(new Products(productRepository.getOneProduct(productId[count])));
        }
        for (Products products1:products)
        {
            productResponses.add(new ProductResponse(
                    products1.getProductId(),
                    products1.getProductName(),
                    products1.getDesc(),
                    products1.getPrice(),
                    products1.getCategory(),
                    products1.getBrand(),
                    products1.getSku(),
                    products1.getType(),
                    products1.getPicture_path(),
                    products1.getDiscount(),
                    products1.getStock(),
                    products1.getSeller_id(),
                   String.valueOf(productRepository.getRating(products1.getProductId()))
            ));
        }
        return productResponses;
    }

    public List<Integer> findNearByUser(Double latitude,Double longitude,Integer user_id){

        int radius = 50; // Km


        // Every lat|lon degree° is ~ 111Km
        Double angle_radius = radius / ( 111 * Math.cos( latitude ) );

    Double min_lat = latitude - angle_radius;
    Double max_lat = latitude + angle_radius;
    Double min_lon = longitude - angle_radius;
    Double max_lon = longitude + angle_radius;

        return  repository.findNearByUser(max_lat,min_lat,max_lon,min_lon,user_id);
    }

    // Function to remove duplicate elements
    // This function returns new size of modified
    // array.
    static int removeDuplicates(Integer[] arr, int n)
    {
        // Return, if array is empty
        // or contains a single element
        if (n==0 || n==1)
            return n;

        int[] temp = new int[n];

        // Start traversing elements
        int j = 0;
        for (int i=0; i<n-1; i++)
            // If current element is not equal
            // to next element then store that
            // current element
            if (!arr[i].equals(arr[i + 1]))
                temp[j++] = arr[i];

        // Store the last element as whether
        // it is unique or repeated, it hasn't
        // stored previously
        temp[j++] = arr[n-1];

        // Modify original array
        for (int i=0; i<j; i++)
            arr[i] = temp[i];

        return j;
    }

    public String  getUserName(Integer userId) {
       return repository.getUserName(userId);
    }

    public String getPhone(Integer userId) {
        return repository.getPhone(userId);
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
