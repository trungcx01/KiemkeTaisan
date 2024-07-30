import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaisanSharedModule } from 'app/shared/shared.module';
import { DonviCungcapComponent } from './donvi-cungcap.component';
import { DonviCungcapDetailComponent } from './donvi-cungcap-detail.component';
import { DonviCungcapUpdateComponent } from './donvi-cungcap-update.component';
import { DonviCungcapDeleteDialogComponent } from './donvi-cungcap-delete-dialog.component';
import { donviCungcapRoute } from './donvi-cungcap.route';

@NgModule({
  imports: [TaisanSharedModule, RouterModule.forChild(donviCungcapRoute)],
  declarations: [DonviCungcapComponent, DonviCungcapDetailComponent, DonviCungcapUpdateComponent, DonviCungcapDeleteDialogComponent],
  entryComponents: [DonviCungcapDeleteDialogComponent],
})
export class TaisanDonviCungcapModule {}
