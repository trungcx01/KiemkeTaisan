import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INhanvien } from 'app/shared/model/nhanvien.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NhanvienService } from './nhanvien.service';
import { NhanvienDeleteDialogComponent } from './nhanvien-delete-dialog.component';

@Component({
  selector: 'jhi-nhanvien',
  templateUrl: './nhanvien.component.html',
})
export class NhanvienComponent implements OnInit, OnDestroy {
  nhanviens?: INhanvien[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected nhanvienService: NhanvienService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.nhanvienService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<INhanvien[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError(),
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInNhanviens();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INhanvien): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNhanviens(): void {
    this.eventSubscriber = this.eventManager.subscribe('nhanvienListModification', () => this.loadPage());
  }

  delete(nhanvien: INhanvien): void {
    const modalRef = this.modalService.open(NhanvienDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nhanvien = nhanvien;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: INhanvien[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/nhanvien'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
      },
    });
    this.nhanviens = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
