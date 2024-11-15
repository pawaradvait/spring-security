import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { User } from 'src/app/model/user.model';
import { KeycloakService } from 'keycloak-angular';
import * as Keycloak from 'keycloak-js';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {
  user = new User();
  constructor(
    private router: Router,
    private readonly keycloak: KeycloakService
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let httpHeaders = new HttpHeaders();

    let xsrf = sessionStorage.getItem('XSRF-TOKEN');
    if (xsrf) {
      httpHeaders = httpHeaders.append('X-XSRF-TOKEN', xsrf);
    }

    let token = this.keycloak.getKeycloakInstance().token;
    if (token) {
      httpHeaders = httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    console.log('working..');

    httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
    const xhr = req.clone({
      headers: httpHeaders,
    });
    return next.handle(xhr).pipe(
      tap((err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401) {
            return;
          }
          this.router.navigate(['dashboard']);
        }
      })
    );
  }
}
