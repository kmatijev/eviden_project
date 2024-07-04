package com.CinemaApp.service;

import com.CinemaApp.model.Movie;
import com.CinemaApp.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MovieService   {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

}
