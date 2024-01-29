import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
} from '@angular/router';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor() {}

  // Information about the route and state of the router
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const token = localStorage.getItem('token');

    if (!token) {
      alert('Please login first!');
      return false;
    }

    return true;
  }
}
