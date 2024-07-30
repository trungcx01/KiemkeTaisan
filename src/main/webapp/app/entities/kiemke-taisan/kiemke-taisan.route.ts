import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IKiemkeTaisan, KiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';
import { KiemkeTaisanService } from './kiemke-taisan.service';
import { KiemkeTaisanComponent } from './kiemke-taisan.component';
import { KiemkeTaisanDetailComponent } from './kiemke-taisan-detail.component';
import { KiemkeTaisanUpdateComponent } from './kiemke-taisan-update.component';

@Injectable({ providedIn: 'root' })
export class KiemkeTaisanResolve implements Resolve<IKiemkeTaisan> {
  constructor(
    private service: KiemkeTaisanService,
    private router: Router,
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IKiemkeTaisan> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((kiemkeTaisan: HttpResponse<KiemkeTaisan>) => {
          if (kiemkeTaisan.body) {
            return of(kiemkeTaisan.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        }),
      );
    }
    return of(new KiemkeTaisan());
  }
}

export const kiemkeTaisanRoute: Routes = [
  {
    path: '',
    component: KiemkeTaisanComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taisanApp.kiemkeTaisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: KiemkeTaisanDetailComponent,
    resolve: {
      kiemkeTaisan: KiemkeTaisanResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.kiemkeTaisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: KiemkeTaisanUpdateComponent,
    resolve: {
      kiemkeTaisan: KiemkeTaisanResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.kiemkeTaisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: KiemkeTaisanUpdateComponent,
    resolve: {
      kiemkeTaisan: KiemkeTaisanResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.kiemkeTaisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
