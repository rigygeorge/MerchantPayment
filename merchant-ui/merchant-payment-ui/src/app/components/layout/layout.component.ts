import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { NavbarComponent } from "../navbar/navbar.component";

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [SidebarComponent, CommonModule, RouterModule, NavbarComponent],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {

}
