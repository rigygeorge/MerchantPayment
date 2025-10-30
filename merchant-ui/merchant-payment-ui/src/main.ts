import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { provideRouter, Routes } from '@angular/router';
import { authInterceptor } from './app/interceptors/auth.interceptor';
import { AppComponent } from './app/app.component';
import { provideClientHydration } from '@angular/platform-browser';
import { routes } from './app/app.routes';



/*const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'merchants', component: MerchantListComponent, canActivate: [AuthGuard] }
];*/

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor]), withFetch()),
    provideRouter(routes),
    provideClientHydration()
  ]
}).catch(err => console.error(err));