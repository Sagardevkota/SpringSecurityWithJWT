package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.mapper.ProductMapper;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.repository.FeedbackRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Slf4j
@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    private OrderService orderService;   //this formed circular dependency so doing field injection

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    public void register(User user,String role) {
        user.setRole(role.toUpperCase(Locale.ROOT));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);

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
        return getProductResponse(products);


    }

    public User getUser(int userId) {
        User user = repository.getOne(userId);
        user.setCartCount(cartService.getBadgeCount(userId));
        return user;
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

    private List<ProductDto> getProductResponse(List<Products> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        products.forEach(product -> {
            ProductDto productDto = productMapper.toDto(product); //get product dto without rating as ignore=true
            String rating = String.valueOf(productRepository.getRating(product.getProductId()));
            try {
                //convert string to list of string
                productMapper.setColors(product.getColors(),productDto);
                productMapper.setSizes(product.getSizes(),productDto);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }

            if (!rating .equals("null") ) {
                productMapper.setRating(rating, productDto); //set rating in product dto
            } else {
                productMapper.setRating("0", productDto);
            }
            productDtoList.add(productDto);
        });
        return productDtoList;
    }


    public String getPhone(Integer userId) {
        return repository.getPhone(userId);
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public String updateUser(User user) {
        User dbUser = getUser(user.getId());
        if (dbUser==null)
            return "User doesnt exist";
        if (!bCryptPasswordEncoder.matches(user.getUserName(),dbUser.getPassword()))
            return "Password is incorrect";
        if (checkifUserExists(user.getUserName())>0)
        return "User already exists";
        if (checkIfUserPhoneExists(user.getPhone())>0)
            return "Phone already taken";

        //now we can update with our user's details
        dbUser.setUserName(user.getUserName());
        dbUser.setPhone(user.getPhone());
        dbUser.setDeliveryAddress(user.getDeliveryAddress());

        repository.updateUser(
                dbUser.getId(),
                dbUser.getUserName(),
                dbUser.getPhone(),
                dbUser.getDeliveryAddress()
        );

        return "User Updated";



    }


    public String updatePassword(int userId,String currentPassword,String newPassword) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(getUserName(userId),currentPassword));
            repository.updatePassword(userId,bCryptPasswordEncoder.encode(newPassword));
            return "Updated Password";
        }

        catch (Exception e){
            return "Incorrect password";
        }


    }
}
