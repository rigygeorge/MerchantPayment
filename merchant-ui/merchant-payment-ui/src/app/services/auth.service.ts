import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { isPlatformBrowser } from '@angular/common';


interface LoginResponse {
  token: string;
  refreshToken?: string; // optional if backend supports refresh
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = `${environment.authApi}`;

  constructor(private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object) { }

  // Helper method to check if we're in browser environment
  private isBrowser(): boolean {
    return isPlatformBrowser(this.platformId);
  }

  //Login Request
  login(credentials: { username: string; password: string }): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, credentials).pipe(
      tap((res: LoginResponse) => {
        localStorage.setItem('token', res.token);
        if (res.refreshToken) {
          localStorage.setItem('refreshToken', res.refreshToken);
        }
      })
    );
  }

  // 🚪 Logout clears tokens
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('refreshToken');
  }

  // 📌 Get token
  getToken(): string {
    if (typeof window !== 'undefined') {
      return localStorage.getItem('token') ?? ''; // <-- return empty string if null
    }
    return '';
  }

  // ✅ Check login state
  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
