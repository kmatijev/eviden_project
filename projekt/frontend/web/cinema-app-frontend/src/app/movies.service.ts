import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MovieDetails } from './movie-details';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {
  private apiUrl = 'http://localhost:3000'; 

  constructor(private http: HttpClient) { }

  getAllMovies(): Observable<MovieDetails[]> {
    return this.http.get<MovieDetails[]>(`${this.apiUrl}/movies`);
  }

  getMovieById(id: number): Observable<MovieDetails> {
    return this.http.get<MovieDetails>(`${this.apiUrl}/movies/${id}`);
  }

  getScreeningsByMovieId(movieId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/screenings?movie_id=${movieId}`);
  }

  getSeats(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/screeningSeats`);
  }

  updateSeatStatus(seatId: number, status: boolean): Observable<any> {
    return this.http.patch<any>(`${this.apiUrl}/screeningSeats/${seatId}`, { status });
  }
}
