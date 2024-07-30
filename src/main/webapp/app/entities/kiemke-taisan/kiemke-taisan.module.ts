import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaisanSharedModule } from 'app/shared/shared.module';
import { KiemkeTaisanComponent } from './kiemke-taisan.component';
import { KiemkeTaisanDetailComponent } from './kiemke-taisan-detail.component';
import { KiemkeTaisanUpdateComponent } from './kiemke-taisan-update.component';
import { KiemkeTaisanDeleteDialogComponent } from './kiemke-taisan-delete-dialog.component';
import { kiemkeTaisanRoute } from './kiemke-taisan.route';

@NgModule({
  imports: [TaisanSharedModule, RouterModule.forChild(kiemkeTaisanRoute)],
  declarations: [KiemkeTaisanComponent, KiemkeTaisanDetailComponent, KiemkeTaisanUpdateComponent, KiemkeTaisanDeleteDialogComponent],
  entryComponents: [KiemkeTaisanDeleteDialogComponent],
})
export class TaisanKiemkeTaisanModule {}
