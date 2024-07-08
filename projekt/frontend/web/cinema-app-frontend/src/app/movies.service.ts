import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MovieDetails } from './movie-details';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {
  private apiUrl = 'http://localhost:3000/movies'; 

  constructor(private http: HttpClient) { }

  getAllMovies(): Observable<MovieDetails[]> {
    return this.http.get<MovieDetails[]>(this.apiUrl);
  }

  getMovieById(id: number): Observable<MovieDetails> {
    return this.http.get<MovieDetails>(`${this.apiUrl}/${id}`);
  }
}
