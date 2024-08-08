import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaisanSharedModule } from 'app/shared/shared.module';
import { BanghiKiemkeComponent } from './banghi-kiemke.component';
import { BanghiKiemkeDetailComponent } from './banghi-kiemke-detail.component';
import { BanghiKiemkeUpdateComponent } from './banghi-kiemke-update.component';
import { BanghiKiemkeDeleteDialogComponent } from './banghi-kiemke-delete-dialog.component';
import { banghiKiemkeRoute } from './banghi-kiemke.route';

@NgModule({
  imports: [TaisanSharedModule, RouterModule.forChild(banghiKiemkeRoute)],
  declarations: [BanghiKiemkeComponent, BanghiKiemkeDetailComponent, BanghiKiemkeUpdateComponent, BanghiKiemkeDeleteDialogComponent],
  entryComponents: [BanghiKiemkeDeleteDialogComponent]
})
export class TaisanBanghiKiemkeModule {}
