import { Component, Input, inject } from '@angular/core';
import { MovieDetails } from '../movie-details';
import { ActivatedRoute } from '@angular/router';
import { MoviesService } from '../movies.service';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [],
  template: `
    <p>
      details: {{ movieDetails?.id }}
    </p>
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
