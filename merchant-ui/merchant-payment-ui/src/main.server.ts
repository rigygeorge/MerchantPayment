import { bootstrapApplication } from '@angular/platform-browser';
import { provideServerRendering } from '@angular/platform-server';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { authInterceptor } from './app/interceptors/auth.interceptor';
import { LoginComponent } from './app/auth/login/login.component'; 
import { MerchantListComponent } from './app/components/merchant/merchant-list/merchant-list.component';
import { AuthGuard } from './app/guards/auth.guard';
import { AppComponent } from './app/app.component';

const routes = [
  { path: '', component: LoginComponent },
  { path: 'merchants', component: MerchantListComponent, canActivate: [AuthGuard] }
];

const serverConfig = {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor]), withFetch()),
    provideRouter(routes),
    provideServerRendering()
  ]
};

export default () => bootstrapApplication(AppComponent, serverConfig);