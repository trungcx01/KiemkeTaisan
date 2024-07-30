import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBanghiKiemke, BanghiKiemke } from 'app/shared/model/banghi-kiemke.model';
import { BanghiKiemkeService } from './banghi-kiemke.service';
import { BanghiKiemkeComponent } from './banghi-kiemke.component';
import { BanghiKiemkeDetailComponent } from './banghi-kiemke-detail.component';
import { BanghiKiemkeUpdateComponent } from './banghi-kiemke-update.component';

@Injectable({ providedIn: 'root' })
export class BanghiKiemkeResolve implements Resolve<IBanghiKiemke> {
  constructor(
    private service: BanghiKiemkeService,
    private router: Router,
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBanghiKiemke> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((banghiKiemke: HttpResponse<BanghiKiemke>) => {
          if (banghiKiemke.body) {
            return of(banghiKiemke.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        }),
      );
    }
    return of(new BanghiKiemke());
  }
}

export const banghiKiemkeRoute: Routes = [
  {
    path: '',
    component: BanghiKiemkeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taisanApp.banghiKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BanghiKiemkeDetailComponent,
    resolve: {
      banghiKiemke: BanghiKiemkeResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.banghiKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BanghiKiemkeUpdateComponent,
    resolve: {
      banghiKiemke: BanghiKiemkeResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.banghiKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BanghiKiemkeUpdateComponent,
    resolve: {
      banghiKiemke: BanghiKiemkeResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.banghiKiemke.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
