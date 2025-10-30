import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MerchantService } from '../../../services/merchant.service';
import { Merchant } from '../../../models/merchant.model';

@Component({
  selector: 'app-merchant-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './merchant-detail.component.html',
  styleUrl: './merchant-detail.component.css'
})
export class MerchantDetailComponent implements OnInit {
  merchant?: Merchant;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private merchantService: MerchantService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')); // get merchant id from URL

    if (id) {
      this.merchantService.getMerchantById(id).subscribe({
        next: (data) => this.merchant = data,
        error: () => this.error = 'Failed to load merchant details'
      });
    } else {
      this.error = 'Invalid merchant ID';
    }
  }
}
