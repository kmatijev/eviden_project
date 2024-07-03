import { Component, Input } from '@angular/core';
import { MovieDetails } from '../movie-details';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-movie',
  standalone: true,
  imports: [RouterModule],
  template: `
   <section class="movie">
      <img class="movie-photo" [src]="movieDetails.photo" alt="Movie poster of {{movieDetails.name}}">
      <h2 class="movie-heading">{{ movieDetails.name }}</h2>
      <p class="movie-description">{{ movieDetails.description }}</p>
      <h3 class="movie-rating">Rating: {{ movieDetails.rating }} <i class="bi bi-star-fill"></i></h3>
      <div class="link-container">
        <a [routerLink]="['/details', movieDetails.id]" class="btn btn-primary">Details</a>
      </div>
    </section>

  `,
  styleUrl: './movie.component.css'
})
export class MovieComponent {
  @Input() movieDetails!: MovieDetails;
}
