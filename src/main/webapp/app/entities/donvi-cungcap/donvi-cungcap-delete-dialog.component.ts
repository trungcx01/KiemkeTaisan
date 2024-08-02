import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDonviCungcap } from 'app/shared/model/donvi-cungcap.model';
import { DonviCungcapService } from './donvi-cungcap.service';

@Component({
  templateUrl: './donvi-cungcap-delete-dialog.component.html'
})
export class DonviCungcapDeleteDialogComponent {
  donviCungcap?: IDonviCungcap;

  constructor(
    protected donviCungcapService: DonviCungcapService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.donviCungcapService.delete(id).subscribe(() => {
      this.eventManager.broadcast('donviCungcapListModification');
      this.activeModal.close();
    });
  }
}
