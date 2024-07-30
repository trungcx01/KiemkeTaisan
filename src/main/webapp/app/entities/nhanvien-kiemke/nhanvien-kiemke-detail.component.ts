import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';

@Component({
  selector: 'jhi-nhanvien-kiemke-detail',
  templateUrl: './nhanvien-kiemke-detail.component.html',
})
export class NhanvienKiemkeDetailComponent implements OnInit {
  nhanvienKiemke: INhanvienKiemke | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nhanvienKiemke }) => {
      this.nhanvienKiemke = nhanvienKiemke;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
