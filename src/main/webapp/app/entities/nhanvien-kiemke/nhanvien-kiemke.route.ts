import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INhanvienKiemke, NhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';
import { NhanvienKiemkeService } from './nhanvien-kiemke.service';
import { NhanvienKiemkeComponent } from './nhanvien-kiemke.component';
import { NhanvienKiemkeDetailComponent } from './nhanvien-kiemke-detail.component';
import { NhanvienKiemkeUpdateComponent } from './nhanvien-kiemke-update.component';

@Injectable({ providedIn: 'root' })
export class NhanvienKiemkeResolve implements Resolve<INhanvienKiemke> {
  constructor(
    private service: NhanvienKiemkeService,
    private router: Router,
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INhanvienKiemke> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nhanvienKiemke: HttpResponse<NhanvienKiemke>) => {
          if (nhanvienKiemke.body) {
            return of(nhanvienKiemke.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        }),
      );
    }
    return of(new NhanvienKiemke());
  }
}

export const nhanvienKiemkeRoute: Routes = [
  {
    path: '',
    component: NhanvienKiemkeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taisanApp.nhanvienKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NhanvienKiemkeDetailComponent,
    resolve: {
      nhanvienKiemke: NhanvienKiemkeResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.nhanvienKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NhanvienKiemkeUpdateComponent,
    resolve: {
      nhanvienKiemke: NhanvienKiemkeResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.nhanvienKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NhanvienKiemkeUpdateComponent,
    resolve: {
      nhanvienKiemke: NhanvienKiemkeResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.nhanvienKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
