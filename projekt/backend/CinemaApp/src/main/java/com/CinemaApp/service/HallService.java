package com.CinemaApp.service;

import com.CinemaApp.model.Hall;
import com.CinemaApp.repository.HallRepository;
import org.springframework.stereotype.Service;

@Service
public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }



}
