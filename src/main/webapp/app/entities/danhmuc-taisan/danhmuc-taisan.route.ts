import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDanhmucTaisan, DanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';
import { DanhmucTaisanService } from './danhmuc-taisan.service';
import { DanhmucTaisanComponent } from './danhmuc-taisan.component';
import { DanhmucTaisanDetailComponent } from './danhmuc-taisan-detail.component';
import { DanhmucTaisanUpdateComponent } from './danhmuc-taisan-update.component';

@Injectable({ providedIn: 'root' })
export class DanhmucTaisanResolve implements Resolve<IDanhmucTaisan> {
  constructor(private service: DanhmucTaisanService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDanhmucTaisan> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((danhmucTaisan: HttpResponse<DanhmucTaisan>) => {
          if (danhmucTaisan.body) {
            return of(danhmucTaisan.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DanhmucTaisan());
  }
}

export const danhmucTaisanRoute: Routes = [
  {
    path: '',
    component: DanhmucTaisanComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taisanApp.danhmucTaisan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DanhmucTaisanDetailComponent,
    resolve: {
      danhmucTaisan: DanhmucTaisanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.danhmucTaisan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DanhmucTaisanUpdateComponent,
    resolve: {
      danhmucTaisan: DanhmucTaisanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.danhmucTaisan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DanhmucTaisanUpdateComponent,
    resolve: {
      danhmucTaisan: DanhmucTaisanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.danhmucTaisan.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
