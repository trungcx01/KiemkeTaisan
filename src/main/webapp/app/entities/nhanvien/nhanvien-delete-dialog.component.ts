import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INhanvien } from 'app/shared/model/nhanvien.model';
import { NhanvienService } from './nhanvien.service';

@Component({
  templateUrl: './nhanvien-delete-dialog.component.html'
})
export class NhanvienDeleteDialogComponent {
  nhanvien?: INhanvien;

  constructor(protected nhanvienService: NhanvienService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.nhanvienService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nhanvienListModification');
      this.activeModal.close();
    });
  }
}
