import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'nhanvien',
        loadChildren: () => import('./nhanvien/nhanvien.module').then(m => m.TaisanNhanvienModule),
      },
      {
        path: 'danhmuc-taisan',
        loadChildren: () => import('./danhmuc-taisan/danhmuc-taisan.module').then(m => m.TaisanDanhmucTaisanModule),
      },
      {
        path: 'donvi-cungcap',
        loadChildren: () => import('./donvi-cungcap/donvi-cungcap.module').then(m => m.TaisanDonviCungcapModule),
      },
      {
        path: 'banghi-kiemke',
        loadChildren: () => import('./banghi-kiemke/banghi-kiemke.module').then(m => m.TaisanBanghiKiemkeModule),
      },
      {
        path: 'kiemke-taisan',
        loadChildren: () => import('./kiemke-taisan/kiemke-taisan.module').then(m => m.TaisanKiemkeTaisanModule),
      },
      {
        path: 'nhanvien-kiemke',
        loadChildren: () => import('./nhanvien-kiemke/nhanvien-kiemke.module').then(m => m.TaisanNhanvienKiemkeModule),
      },
      {
        path: 'taisan',
        loadChildren: () => import('./taisan/taisan.module').then(m => m.TaisanTaisanModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TaisanEntityModule {}
