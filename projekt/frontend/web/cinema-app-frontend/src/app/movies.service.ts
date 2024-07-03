import { Injectable } from '@angular/core';
import { MovieDetails } from './movie-details';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {
  protected movieDetailsList: MovieDetails[] = [
    {
      "id": 0,
      "name": "Pulp fiction",
      "photo": "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
      "description": "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
      "rating": 4.9,
      "genre": "Action"
    },
    {
      "id": 1,
      "name": "Django unchained",
      "photo": "https://m.media-amazon.com/images/S/pv-target-images/b9839cd791f871c956edeead6becda8e374277ae832aa265625d80a43dad4334.jpg",
      "description": "With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal plantation owner in Mississippi.",
      "rating": 4.7,
      "genre": "Action"
    },
    {
      "id": 2,
      "name": "Inception",
      "photo": "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg",
      "description": "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
      "rating": 4.7,
      "genre": "Action"
    }
  ];
  constructor() { }
  getAllMovies() : MovieDetails[] {
    return this.movieDetailsList;
  }
  getMovieById(id: Number): MovieDetails | 
  undefined {
    return this.movieDetailsList.find(movieDetails => movieDetails.id === id);
  }
}
