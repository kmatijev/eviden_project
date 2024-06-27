import { Component } from '@angular/core';
import { MovieComponent } from '../movie/movie.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MovieComponent],
  template: `
    <p>
      sort by genre implementation?
    </p>
    <section class="results">
      <app-movie></app-movie>
    </section>
  `,
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
