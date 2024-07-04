import { Component, Input, inject } from '@angular/core';
import { MovieDetails } from '../movie-details';
import { ActivatedRoute } from '@angular/router';
import { MoviesService } from '../movies.service';
import { SeatComponent } from '../seat/seat.component';
import { TheatreComponent } from '../theatre/theatre.component';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [TheatreComponent],
  template: `
    <article class="movie">
      <div class="movie-content">
        <img class="movie-photo" [src]="movieDetails?.photo" alt="{{movieDetails?.name}}">
        <section class="movie-description">
          <h1 class="movie-heading">{{movieDetails?.name}}</h1>
          <p>{{movieDetails?.description}}</p>
          <h2 class="movie-rating">Rating: {{movieDetails?.rating}} <i class="bi bi-star-fill"></i></h2>
          <h2 class="movie-genre">Genre: {{movieDetails?.genre}}</h2>
        </section>
      </div>
    </article>
    <app-theatre></app-theatre>

  `,
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  moviesService = inject(MoviesService);
  movieDetails: MovieDetails | undefined;

  constructor() {
    const movieId = Number(this.route.snapshot.params['id']);
    this.movieDetails = this.moviesService.getMovieById(movieId);
  }
}
