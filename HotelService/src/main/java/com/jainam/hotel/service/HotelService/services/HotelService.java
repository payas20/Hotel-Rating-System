package com.jainam.hotel.service.HotelService.services;

import com.jainam.hotel.service.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    Hotel getHotel(String hotelId);

    List<Hotel> getAllHotel();
}
