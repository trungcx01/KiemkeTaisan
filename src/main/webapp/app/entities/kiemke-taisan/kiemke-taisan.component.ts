import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IKiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { KiemkeTaisanService } from './kiemke-taisan.service';
import { KiemkeTaisanDeleteDialogComponent } from './kiemke-taisan-delete-dialog.component';
import { Donvi } from '../donvi.enum';

@Component({
  selector: 'jhi-kiemke-taisan',
  templateUrl: './kiemke-taisan.component.html'
})
export class KiemkeTaisanComponent implements OnInit, OnDestroy {
  kiemkeTaisans?: IKiemkeTaisan[];
  Donvi = Donvi;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected kiemkeTaisanService: KiemkeTaisanService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.kiemkeTaisanService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IKiemkeTaisan[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInKiemkeTaisans();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IKiemkeTaisan): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInKiemkeTaisans(): void {
    this.eventSubscriber = this.eventManager.subscribe('kiemkeTaisanListModification', () => this.loadPage());
  }

  delete(kiemkeTaisan: IKiemkeTaisan): void {
    const modalRef = this.modalService.open(KiemkeTaisanDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.kiemkeTaisan = kiemkeTaisan;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IKiemkeTaisan[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/kiemke-taisan'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.kiemkeTaisans = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
