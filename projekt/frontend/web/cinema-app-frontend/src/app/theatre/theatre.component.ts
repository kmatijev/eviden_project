import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MoviesService } from '../movies.service';
import { Observable, BehaviorSubject } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { SeatComponent } from '../seat/seat.component';

@Component({
  selector: 'app-theatre',
  standalone: true,
  imports: [CommonModule, SeatComponent],
  templateUrl: './theatre.component.html',
  styleUrls: ['./theatre.component.css']
})
export class TheatreComponent implements OnChanges {
  @Input() screeningId!: number;
  private seatsSubject = new BehaviorSubject<any[]>([]);
  seats$ = this.seatsSubject.asObservable();
  rows: { start: number, end: number }[] = [
    { start: 0, end: 8 },
    { start: 8, end: 16 },
    { start: 16, end: 24 },
    { start: 24, end: 32 },
    { start: 32, end: 40 },
    { start: 40, end: 48 },
    { start: 48, end: 56 },
    { start: 56, end: 64 },
  ];

  constructor(private moviesService: MoviesService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['screeningId'] && this.screeningId) {
      // Fetch all seats as available for any screening
      this.moviesService.getSeats().subscribe(seats => {
        this.seatsSubject.next(seats);
        console.log('Seats loaded for screening:', this.screeningId, seats);
      });
    }
  }

  reserveSeat(seatId: number) {
    this.moviesService.updateSeatStatus(seatId, false).subscribe(() => {
      const seats = this.seatsSubject.getValue().map(seat => {
        if (seat.id === seatId) {
          seat.status = false; // Mark seat as reserved
        }
        return seat;
      });
      this.seatsSubject.next(seats);
      console.log('Reserving seat with ID:', seatId);
    });
  }
}
