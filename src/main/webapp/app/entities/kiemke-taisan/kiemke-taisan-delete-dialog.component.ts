import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';
import { KiemkeTaisanService } from './kiemke-taisan.service';

@Component({
  templateUrl: './kiemke-taisan-delete-dialog.component.html',
})
export class KiemkeTaisanDeleteDialogComponent {
  kiemkeTaisan?: IKiemkeTaisan;

  constructor(
    protected kiemkeTaisanService: KiemkeTaisanService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.kiemkeTaisanService.delete(id).subscribe(() => {
      this.eventManager.broadcast('kiemkeTaisanListModification');
      this.activeModal.close();
    });
  }
}
