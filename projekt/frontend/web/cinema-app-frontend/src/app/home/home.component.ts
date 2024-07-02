import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieComponent } from '../movie/movie.component';
import { MovieDetails } from '../movie-details';
import { MoviesService } from '../movies.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MovieComponent],
  template: `
    <h1 class="text-center">Movies that are screening right now</h1>
    <section class="results">
      <app-movie *ngFor="let movieDetails of movieDetailsList" [movieDetails]="movieDetails"></app-movie>
    </section>
  `,
  styleUrl: './home.component.css'
})
export class HomeComponent {
  movieDetailsList: MovieDetails[] = [];
  moviesService: MoviesService = inject(MoviesService);

  constructor() {
    this.movieDetailsList = this.moviesService.getAllMovies();
  }

}
