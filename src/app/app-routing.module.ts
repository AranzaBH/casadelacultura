import { NormalGuard } from './services/normal.guard';
import { AdminGuard } from './services/admin.guard';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './pages/profile/profile.component';
import { TalleresComponent } from './talleres/talleres.component';
import { CrearTallerComponent } from './talleres/crear-taller/crear-taller.component';
const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'admin/dash',
    component: DashboardComponent,
    pathMatch: 'full',
    canActivate: [AdminGuard],
    children: [
      {
        path: 'profile',
        component: ProfileComponent
      }
    ]
  },
  {
    path: 'user-dashboard',
    component: UserDashboardComponent,
    pathMatch: 'full',
    canActivate: [NormalGuard]
    
  },
  {
    path: 'admin/profile',
    component: ProfileComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [NormalGuard]
  },{
    path: 'talleres', // Nueva ruta para el componente de talleres
    component: TalleresComponent,
    canActivate: [NormalGuard] // Usando NormalGuard para usuarios normales
  
  },{
    path: 'admin/crearT', // Nueva ruta para el componente de talleres
    component: CrearTallerComponent,
    canActivate: [AdminGuard] // Usando NormalGuard para usuarios normales
  
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
