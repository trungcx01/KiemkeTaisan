import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaisanSharedModule } from 'app/shared/shared.module';
import { DanhmucTaisanComponent } from './danhmuc-taisan.component';
import { DanhmucTaisanDetailComponent } from './danhmuc-taisan-detail.component';
import { DanhmucTaisanUpdateComponent } from './danhmuc-taisan-update.component';
import { DanhmucTaisanDeleteDialogComponent } from './danhmuc-taisan-delete-dialog.component';
import { danhmucTaisanRoute } from './danhmuc-taisan.route';

@NgModule({
  imports: [TaisanSharedModule, RouterModule.forChild(danhmucTaisanRoute)],
  declarations: [DanhmucTaisanComponent, DanhmucTaisanDetailComponent, DanhmucTaisanUpdateComponent, DanhmucTaisanDeleteDialogComponent],
  entryComponents: [DanhmucTaisanDeleteDialogComponent],
})
export class TaisanDanhmucTaisanModule {}
