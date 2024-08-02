import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DanhmucTaisanService } from './danhmuc-taisan.service';
import { DanhmucTaisanDeleteDialogComponent } from './danhmuc-taisan-delete-dialog.component';

@Component({
  selector: 'jhi-danhmuc-taisan',
  templateUrl: './danhmuc-taisan.component.html'
})
export class DanhmucTaisanComponent implements OnInit, OnDestroy {
  danhmucTaisans?: IDanhmucTaisan[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected danhmucTaisanService: DanhmucTaisanService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.danhmucTaisanService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IDanhmucTaisan[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
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
    this.registerChangeInDanhmucTaisans();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDanhmucTaisan): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDanhmucTaisans(): void {
    this.eventSubscriber = this.eventManager.subscribe('danhmucTaisanListModification', () => this.loadPage());
  }

  delete(danhmucTaisan: IDanhmucTaisan): void {
    const modalRef = this.modalService.open(DanhmucTaisanDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.danhmucTaisan = danhmucTaisan;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IDanhmucTaisan[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/danhmuc-taisan'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.danhmucTaisans = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
