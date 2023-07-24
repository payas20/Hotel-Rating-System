package com.jainam.user.service.UserService.external.services;

import com.jainam.user.service.UserService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    Rating createRating(Rating value);
}
