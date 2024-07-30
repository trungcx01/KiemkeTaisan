import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITaisan } from 'app/shared/model/taisan.model';
import { TaisanService } from './taisan.service';

@Component({
  templateUrl: './taisan-delete-dialog.component.html',
})
export class TaisanDeleteDialogComponent {
  taisan?: ITaisan;

  constructor(
    protected taisanService: TaisanService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.taisanService.delete(id).subscribe(() => {
      this.eventManager.broadcast('taisanListModification');
      this.activeModal.close();
    });
  }
}
