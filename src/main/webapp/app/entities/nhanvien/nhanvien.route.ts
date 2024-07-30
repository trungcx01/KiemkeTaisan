import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INhanvien, Nhanvien } from 'app/shared/model/nhanvien.model';
import { NhanvienService } from './nhanvien.service';
import { NhanvienComponent } from './nhanvien.component';
import { NhanvienDetailComponent } from './nhanvien-detail.component';
import { NhanvienUpdateComponent } from './nhanvien-update.component';

@Injectable({ providedIn: 'root' })
export class NhanvienResolve implements Resolve<INhanvien> {
  constructor(
    private service: NhanvienService,
    private router: Router,
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INhanvien> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nhanvien: HttpResponse<Nhanvien>) => {
          if (nhanvien.body) {
            return of(nhanvien.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        }),
      );
    }
    return of(new Nhanvien());
  }
}

export const nhanvienRoute: Routes = [
  {
    path: '',
    component: NhanvienComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taisanApp.nhanvien.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NhanvienDetailComponent,
    resolve: {
      nhanvien: NhanvienResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.nhanvien.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NhanvienUpdateComponent,
    resolve: {
      nhanvien: NhanvienResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.nhanvien.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NhanvienUpdateComponent,
    resolve: {
      nhanvien: NhanvienResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.nhanvien.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
