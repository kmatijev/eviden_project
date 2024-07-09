import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-seat',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './seat.component.html',
  styleUrls: ['./seat.component.css']
})
export class SeatComponent {
  @Input() seat!: { id: number, status: boolean };
  @Output() reserve = new EventEmitter<number>();

  onReserve() {
    if (this.seat.status) {
      this.reserve.emit(this.seat.id);
    }
  }
}
