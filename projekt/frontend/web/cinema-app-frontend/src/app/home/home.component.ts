import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieComponent } from '../movie/movie.component';
import { MovieDetails } from '../movie-details';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MovieComponent],
  template: `
    <h1 class="movies-screening">Movies that are screening right now</h1>
    <section class="results">
      <app-movie *ngFor="let movieDetails of movieDetailsList" [movieDetails]="movieDetails"></app-movie>
    </section>
  `,
  styleUrl: './home.component.css'
})
export class HomeComponent {
  movieDetailsList: MovieDetails[] = [
    {
      "id": 0,
      "name": "Pulp fiction",
      "photo": "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
      "description": "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
      "rating": 4.9
    },
    {
      "id": 1,
      "name": "Django unchained",
      "photo": "https://m.media-amazon.com/images/S/pv-target-images/b9839cd791f871c956edeead6becda8e374277ae832aa265625d80a43dad4334.jpg",
      "description": "With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal plantation owner in Mississippi.",
      "rating": 4.7
    },
    {
      "id": 2,
      "name": "Inception",
      "photo": "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg",
      "description": "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
      "rating": 4.7
    }
  ];
}
