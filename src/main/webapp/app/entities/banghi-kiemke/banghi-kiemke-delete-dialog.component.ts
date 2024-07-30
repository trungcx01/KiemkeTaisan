import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBanghiKiemke } from 'app/shared/model/banghi-kiemke.model';
import { BanghiKiemkeService } from './banghi-kiemke.service';

@Component({
  templateUrl: './banghi-kiemke-delete-dialog.component.html',
})
export class BanghiKiemkeDeleteDialogComponent {
  banghiKiemke?: IBanghiKiemke;

  constructor(
    protected banghiKiemkeService: BanghiKiemkeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.banghiKiemkeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('banghiKiemkeListModification');
      this.activeModal.close();
    });
  }
}
