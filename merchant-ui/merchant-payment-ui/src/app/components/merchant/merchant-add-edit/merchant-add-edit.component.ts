import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MerchantService } from '../../../services/merchant.service';
import { Merchant } from '../../../models/merchant.model';

@Component({
  selector: 'app-merchant-add-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './merchant-add-edit.component.html',
  styleUrl: './merchant-add-edit.component.css'
})
export class MerchantAddEditComponent implements OnInit{
  merchantForm!:FormGroup;
  isEdit = false;
  merchantId?: number;
  error = '';

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private merchantService: MerchantService
  ) {}
  ngOnInit(): void {
    this.merchantForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required]
    });
    // Check if editing
    this.merchantId = Number(this.route.snapshot.queryParamMap.get('id'));
    if (this.merchantId) {
      this.isEdit = true;
      this.merchantService.getMerchantById(this.merchantId).subscribe({
        next: (merchant) => this.merchantForm.patchValue(merchant),
        error: () => this.error = 'Failed to load merchant details'
      });
    }
  }
  onSubmit(): void {
    if (this.merchantForm.invalid) return;

    if (this.isEdit && this.merchantId) {
      this.merchantService.updateMerchant(this.merchantId, this.merchantForm.value).subscribe({
        next: () => this.router.navigate(['/merchants']),
        error: () => this.error = 'Failed to update merchant'
      });
    } else {
      this.merchantService.addMerchant(this.merchantForm.value).subscribe({
        next: () => this.router.navigate(['/merchants']),
        error: () => this.error = 'Failed to add merchant'
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/merchants']);
  }

}
