import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';
import { DanhmucTaisanService } from './danhmuc-taisan.service';

@Component({
  templateUrl: './danhmuc-taisan-delete-dialog.component.html',
})
export class DanhmucTaisanDeleteDialogComponent {
  danhmucTaisan?: IDanhmucTaisan;

  constructor(
    protected danhmucTaisanService: DanhmucTaisanService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.danhmucTaisanService.delete(id).subscribe(() => {
      this.eventManager.broadcast('danhmucTaisanListModification');
      this.activeModal.close();
    });
  }
}
