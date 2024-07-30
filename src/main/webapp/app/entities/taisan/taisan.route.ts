import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITaisan, Taisan } from 'app/shared/model/taisan.model';
import { TaisanService } from './taisan.service';
import { TaisanComponent } from './taisan.component';
import { TaisanDetailComponent } from './taisan-detail.component';
import { TaisanUpdateComponent } from './taisan-update.component';

@Injectable({ providedIn: 'root' })
export class TaisanResolve implements Resolve<ITaisan> {
  constructor(
    private service: TaisanService,
    private router: Router,
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITaisan> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((taisan: HttpResponse<Taisan>) => {
          if (taisan.body) {
            return of(taisan.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        }),
      );
    }
    return of(new Taisan());
  }
}

export const taisanRoute: Routes = [
  {
    path: '',
    component: TaisanComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taisanApp.taisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TaisanDetailComponent,
    resolve: {
      taisan: TaisanResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.taisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TaisanUpdateComponent,
    resolve: {
      taisan: TaisanResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.taisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TaisanUpdateComponent,
    resolve: {
      taisan: TaisanResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.taisan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
