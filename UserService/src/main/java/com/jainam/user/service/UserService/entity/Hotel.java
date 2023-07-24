package com.jainam.user.service.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
