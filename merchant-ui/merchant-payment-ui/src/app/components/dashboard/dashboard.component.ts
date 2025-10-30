import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Merchant } from '../../models/merchant.model';
import { MerchantService } from '../../services/merchant.service';
import { SidebarComponent } from "../sidebar/sidebar.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  totalMerchants = 0;
  totalTransactions = 0;
  pendingPayments = 0;
  merchants: any[] = [];  error = '';

  constructor(private merchantService: MerchantService
    //,
   // private transactionService: TransactionService
   ) { }

  ngOnInit(): void {
    // Load merchants
    this.merchantService.getMerchants().subscribe({
      next: (data) => {
        this.totalMerchants = data.length;
        
        this.merchants = data;

        //this.recentMerchants = merchants.slice(-5).reverse(); // last 5 added
      },
      error: (err) => console.error('Failed to load merchants', err)

    });
 // Load transactions
/* this.transactionService.getTransactions().subscribe({
  next: (transactions) => {
    this.totalTransactions = transactions.length;
    this.pendingPayments = transactions.filter(t => !t.completed).length;
  },
  error: () => this.error = 'Failed to load transaction data'
}); */
}
}