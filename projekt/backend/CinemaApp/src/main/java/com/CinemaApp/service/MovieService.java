package com.CinemaApp.service;

import com.CinemaApp.model.Movie;
import com.CinemaApp.repository.MovieRepository;
import org.springframework.stereotype.Service;


@Service
public class MovieService   {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }



}
