import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { MerchantListComponent } from './components/merchant/merchant-list/merchant-list.component';
import { MerchantAddEditComponent } from './components/merchant/merchant-add-edit/merchant-add-edit.component';
import { MerchantDetailComponent } from './components/merchant/merchant-detail/merchant-detail.component';
import { AuthGuard } from './guards/auth.guard';
/*import { PaymentListComponent } from './components/payment/payment-list/payment-list.component';*/
import { DashboardComponent } from './components/dashboard/dashboard.component'; 
import { LayoutComponent } from './components/layout/layout.component';

export const routes: Routes = [
  // Login page → no sidebar
  { path: '', component: LoginComponent },

  // Protected routes → wrapped inside LayoutComponent
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'merchants', component: MerchantListComponent },
      { path: 'merchants/add', component: MerchantAddEditComponent },
      { path: 'merchants/:id', component: MerchantDetailComponent },
      // { path: 'payments', component: PaymentListComponent } (if needed)
    ]
  },

  // Wildcard → redirect to login
  { path: '**', redirectTo: '' }
];
