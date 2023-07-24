package com.jainam.user.service.UserService.service.impl;

import com.jainam.user.service.UserService.entity.Hotel;
import com.jainam.user.service.UserService.entity.Rating;
import com.jainam.user.service.UserService.entity.User;
import com.jainam.user.service.UserService.exceptions.ResourceNotFoundException;
import com.jainam.user.service.UserService.external.services.HotelService;
import com.jainam.user.service.UserService.repository.UserRepository;
import com.jainam.user.service.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

//    final Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String uid = UUID.randomUUID().toString();
        user.setUserId(uid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // get user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server with given id:"+userId));

        // get ratings for user from RatingService
        //http://localhost:8083/ratings/users/86b6558c-16cb-4cc5-b63c-bd537261d2c8
        //Whenever a service calls another service it does it with help of client
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating>ratingList = Arrays.stream(ratings).toList();
        log.info("User ratings "+ratings);

        List<Rating> rate = ratingList.stream().map(rating -> {
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        log.info("rate"+rate);
        user.setRatings(rate);
        return user;
    }
}
