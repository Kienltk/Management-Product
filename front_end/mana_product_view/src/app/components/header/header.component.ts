import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  activeRoute: string = '';

  constructor(private router: Router) {}

  ngOnInit() {
    // Subscribe to router events to update active route
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.activeRoute = event.urlAfterRedirects.split('?')[0];
      });
  }

  setActiveRoute(route: string) {
    this.activeRoute = route;
    this.router.navigate([route]);
  }
}