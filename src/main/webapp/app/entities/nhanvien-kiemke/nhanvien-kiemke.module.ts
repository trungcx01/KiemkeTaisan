import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaisanSharedModule } from 'app/shared/shared.module';
import { NhanvienKiemkeComponent } from './nhanvien-kiemke.component';
import { NhanvienKiemkeDetailComponent } from './nhanvien-kiemke-detail.component';
import { NhanvienKiemkeUpdateComponent } from './nhanvien-kiemke-update.component';
import { NhanvienKiemkeDeleteDialogComponent } from './nhanvien-kiemke-delete-dialog.component';
import { nhanvienKiemkeRoute } from './nhanvien-kiemke.route';

@NgModule({
  imports: [TaisanSharedModule, RouterModule.forChild(nhanvienKiemkeRoute)],
  declarations: [
    NhanvienKiemkeComponent,
    NhanvienKiemkeDetailComponent,
    NhanvienKiemkeUpdateComponent,
    NhanvienKiemkeDeleteDialogComponent,
  ],
  entryComponents: [NhanvienKiemkeDeleteDialogComponent],
})
export class TaisanNhanvienKiemkeModule {}
