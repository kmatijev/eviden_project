package com.CinemaApp.service;

import com.CinemaApp.model.Seat;
import com.CinemaApp.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    public SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    public List<Seat> getAvailableSeats(){
        return seatRepository.findByStatus(true);
    }


}
