import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDonviCungcap, DonviCungcap } from 'app/shared/model/donvi-cungcap.model';
import { DonviCungcapService } from './donvi-cungcap.service';
import { DonviCungcapComponent } from './donvi-cungcap.component';
import { DonviCungcapDetailComponent } from './donvi-cungcap-detail.component';
import { DonviCungcapUpdateComponent } from './donvi-cungcap-update.component';

@Injectable({ providedIn: 'root' })
export class DonviCungcapResolve implements Resolve<IDonviCungcap> {
  constructor(private service: DonviCungcapService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDonviCungcap> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((donviCungcap: HttpResponse<DonviCungcap>) => {
          if (donviCungcap.body) {
            return of(donviCungcap.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DonviCungcap());
  }
}

export const donviCungcapRoute: Routes = [
  {
    path: '',
    component: DonviCungcapComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taisanApp.donviCungcap.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DonviCungcapDetailComponent,
    resolve: {
      donviCungcap: DonviCungcapResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.donviCungcap.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DonviCungcapUpdateComponent,
    resolve: {
      donviCungcap: DonviCungcapResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.donviCungcap.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DonviCungcapUpdateComponent,
    resolve: {
      donviCungcap: DonviCungcapResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taisanApp.donviCungcap.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
