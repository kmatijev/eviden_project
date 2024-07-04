package com.CinemaApp.api;


import com.CinemaApp.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/halls")
public class HallController {

    @Autowired
    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }


}
