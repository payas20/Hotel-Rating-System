package com.jainam.hotel.service.HotelService.services.impl;

import com.jainam.hotel.service.HotelService.entity.Hotel;
import com.jainam.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.jainam.hotel.service.HotelService.repository.HotelRepository;
import com.jainam.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found on server with given id:"+hotelId));
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }
}
