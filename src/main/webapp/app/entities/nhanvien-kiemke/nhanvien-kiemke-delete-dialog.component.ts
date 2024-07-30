import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';
import { NhanvienKiemkeService } from './nhanvien-kiemke.service';

@Component({
  templateUrl: './nhanvien-kiemke-delete-dialog.component.html',
})
export class NhanvienKiemkeDeleteDialogComponent {
  nhanvienKiemke?: INhanvienKiemke;

  constructor(
    protected nhanvienKiemkeService: NhanvienKiemkeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.nhanvienKiemkeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nhanvienKiemkeListModification');
      this.activeModal.close();
    });
  }
}
