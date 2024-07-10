import { Component } from '@angular/core';
import { SeatComponent } from '../seat/seat.component';

@Component({
  selector: 'app-theatre',
  standalone: true,
  imports: [SeatComponent],
  templateUrl: './theatre.component.html',
  styleUrl: './theatre.component.css'
})
export class TheatreComponent {

}
