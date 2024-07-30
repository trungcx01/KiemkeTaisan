import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaisanSharedModule } from 'app/shared/shared.module';
import { TaisanComponent } from './taisan.component';
import { TaisanDetailComponent } from './taisan-detail.component';
import { TaisanUpdateComponent } from './taisan-update.component';
import { TaisanDeleteDialogComponent } from './taisan-delete-dialog.component';
import { taisanRoute } from './taisan.route';

@NgModule({
  imports: [TaisanSharedModule, RouterModule.forChild(taisanRoute)],
  declarations: [TaisanComponent, TaisanDetailComponent, TaisanUpdateComponent, TaisanDeleteDialogComponent],
  entryComponents: [TaisanDeleteDialogComponent],
})
export class TaisanTaisanModule {}
