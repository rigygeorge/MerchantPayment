import { Component, OnInit } from '@angular/core';
import { MerchantService } from '../../../services/merchant.service';
import { Merchant } from '../../../models/merchant.model';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-merchant-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './merchant-list.component.html',
  styleUrl: './merchant-list.component.css'
})
export class MerchantListComponent implements OnInit {
  merchants: Merchant[] =[];
  error = '';  
  loading = false;  

  constructor(private merchantService:MerchantService) {}

  ngOnInit(): void {
    this.loadMerchants();
  }
  loadMerchants(): void {
    this.loading = true;
    this.merchantService.getMerchants().subscribe({
      next: (data) => {
        this.merchants = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Failed to load merchants';
        this.loading = false;
      }
    });
  }

  deleteMerchant(id ?: number): void {

    if (id === undefined) return;
    
    if (confirm('Are you sure you want to delete this merchant?')) {
      this.merchantService.deleteMerchant(id).subscribe({
        next: () => {
          this.merchants = this.merchants.filter(m => m.id !== id); // update list without reload
        },
        error: () => {
          this.error = 'Failed to delete merchant';
        }
      });
    }
  }
}
