import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieDetails } from '../movie-details';
import { ActivatedRoute } from '@angular/router';
import { MoviesService } from '../movies.service';
import { SeatComponent } from '../seat/seat.component';
import { TheatreComponent } from '../theatre/theatre.component';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule, TheatreComponent],
  template: `
    <article class="movie" *ngIf="movieDetails$ | async as movieDetails">
      <div class="movie-content">
        <img class="movie-photo" [src]="movieDetails.image" alt="{{movieDetails.name}}">
        <section class="movie-description">
          <h1 class="movie-heading">{{movieDetails.name}}</h1>
          <p>{{movieDetails.description}}</p>
          <h2 class="movie-genre">Genre: {{movieDetails.genre}}</h2>
          <p class="movie-director">Director: {{movieDetails.director}}</p>
          <p class="movie-actors">Actors: {{movieDetails.actors}}</p>
          <h2 class="movie-duration">Duration: {{movieDetails.duration}} minutes</h2>
        </section>
      </div>
    </article>
    <app-theatre></app-theatre>
  `,
  styleUrl: './details.component.css'
})
export class DetailsComponent implements OnInit {
  movieDetails$!: Observable<MovieDetails>;

  constructor(
    private route: ActivatedRoute,
    private moviesService: MoviesService
  ) {}

  ngOnInit() {
    this.movieDetails$ = this.route.params.pipe(
      switchMap(params => this.moviesService.getMovieById(+params['id']))
    );
  }
}
