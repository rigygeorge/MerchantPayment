import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Merchant } from '../models/merchant.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class MerchantService {

  private baseUrl = environment.merchantApi;

  constructor(private http: HttpClient) {}

  //Returns list of Merchants
  getMerchants(): Observable<Merchant[]> {
    return this.http.get<Merchant[]>(this.baseUrl);
  
  }

  // ✅ Returns single merchant
  getMerchantById(id: number): Observable<Merchant> {
    return this.http.get<Merchant>(`${this.baseUrl}/${id}`);
  }

  // ✅ Add new merchant
  addMerchant(merchant: Merchant): Observable<Merchant> {
    return this.http.post<Merchant>(this.baseUrl, merchant);
  }

  // ✅ Update merchant
  updateMerchant(id: number, merchant: Merchant): Observable<Merchant> {
    return this.http.put<Merchant>(`${this.baseUrl}/${id}`, merchant);
  }

  // ✅ Delete merchant
  deleteMerchant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
