import { Component, Input } from '@angular/core';
import { MovieDetails } from '../movie-details';

@Component({
  selector: 'app-movie',
  standalone: true,
  imports: [],
  template: `
    <section class="movie">
      <img class="movie-photo" [src]="movieDetails.photo" alt="Movie poster of {{movieDetails.name}}">
      <h2 class="movie-heading">{{ movieDetails.name }}</h2>
      <p class="movie-description">{{ movieDetails.description }}</p>
      <h3 class="movie-rating">Rating: {{ movieDetails.rating }} <i class="bi bi-star-fill"></i></h3>

    </section>
  `,
  styleUrl: './movie.component.css'
})
export class MovieComponent {
  @Input() movieDetails!: MovieDetails;
}
