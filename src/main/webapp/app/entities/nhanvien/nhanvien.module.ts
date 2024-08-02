import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaisanSharedModule } from 'app/shared/shared.module';
import { NhanvienComponent } from './nhanvien.component';
import { NhanvienDetailComponent } from './nhanvien-detail.component';
import { NhanvienUpdateComponent } from './nhanvien-update.component';
import { NhanvienDeleteDialogComponent } from './nhanvien-delete-dialog.component';
import { nhanvienRoute } from './nhanvien.route';

@NgModule({
  imports: [TaisanSharedModule, RouterModule.forChild(nhanvienRoute)],
  declarations: [NhanvienComponent, NhanvienDetailComponent, NhanvienUpdateComponent, NhanvienDeleteDialogComponent],
  entryComponents: [NhanvienDeleteDialogComponent]
})
export class TaisanNhanvienModule {}
