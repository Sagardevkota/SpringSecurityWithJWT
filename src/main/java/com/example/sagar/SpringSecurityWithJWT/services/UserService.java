package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.mapper.ProductMapper;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.repository.FeedbackRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    private OrderService orderService;   //this formed circular dependency so doing field injection

    private final ProductRepository productRepository;
    private final FeedbackRepository feedbackRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    UserService(UserRepository userRepository,
                ProductRepository productRepository,
                FeedbackRepository feedbackRepository,
                BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = userRepository;
        this.productRepository = productRepository;
        this.feedbackRepository = feedbackRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean isVerified(String userName) {
        return repository.isVerified(userName);
    }

    public void register(User user) {

        repository.save(new User(user.getUserName(),
                bCryptPasswordEncoder.encode(user.getPassword()),
                user.getDeliveryAddress(),
                user.getPhone(),
                user.getRole(),
                user.getAge(),
                user.getGender(),
                user.getLatitude(),
                user.getLongitude()));

    }

    public int checkifUserExists(String userName) {
        return repository.checkIfUserExist(userName).size();
    }

    public int checkIfUserPhoneExists(String phone) {
        return repository.checkIfUserPhoneExist(phone).size();
    }

    public String updateEmail(Integer userId, String newUserName) {
        if (checkifUserExists(newUserName) > 0)
            return "User already exists";
        else {
            repository.updateUserName(userId, newUserName);
            return "Updated Email";
        }
    }

    public String updatePhone(Integer userId, String newPhone) {
        if (checkIfUserPhoneExists(newPhone) > 0)
            return "Phone is taken";
        else {
            repository.updatePhone(userId, newPhone);
            return "Updated Phone";
        }

    }

    public String updateDelivery(Integer userId, String newDelivery) {
        repository.updateDelivery(userId, newDelivery);
        return "Updated Delivery";
    }

    public String getUserName(Integer seller_id) {
        return repository.getSellerName(seller_id);

    }


    public List<ProductDto> getNearbyPeopleOrders(Integer userId) {

        List<ProductDto> productDtoList = new ArrayList<>();

        //get latlng
        User user = repository.findAllByUserId(userId);
        Double latitude = user.getLatitude();
        Double longitude = user.getLongitude();

        List<Integer> productIds = new ArrayList<>();
        //get Ids of nearBy people
        List<Integer> userIds = findNearByUser(latitude, longitude, userId);


        //get orders of those ids
        userIds.forEach(id -> {
            List<OrderDto> orderRespons = orderService.getOrdersResponse(id, "completed");
            orderRespons.forEach(orderResponse -> productIds.add(orderResponse.getProductId()));
        });

        List<Products> products = new ArrayList<>();

        //get products
        productIds.stream().distinct()
                .sorted()
                .forEach(productId -> {
                    products.add(productRepository.getOneProduct(productId));
                });


        //finally get product response
        products.forEach(products1 -> {
            ProductDto productDto = productMapper.toDto(products1);
            String rating = String.valueOf(productRepository.getRating(products1.getProductId()));
            productMapper.setRating(rating, productDto);
            productDtoList.add(productDto);
        });


        return productDtoList;
    }

    public User getUser(int userId) {
        return repository.getOne(userId);
    }

    public List<Integer> findNearByUser(Double latitude, Double longitude, Integer user_id) {

        int radius = 50; // Km

        // Every lat|lon degree° is ~ 111Km
        Double angle_radius = radius / (111 * Math.cos(latitude));

        Double min_lat = latitude - angle_radius;
        Double max_lat = latitude + angle_radius;
        Double min_lon = longitude - angle_radius;
        Double max_lon = longitude + angle_radius;

        return repository.findNearByUser(max_lat, min_lat, max_lon, min_lon, user_id);
    }


    public String getPhone(Integer userId) {
        return repository.getPhone(userId);
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
