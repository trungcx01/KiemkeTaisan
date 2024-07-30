import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INhanvien } from 'app/shared/model/nhanvien.model';

@Component({
  selector: 'jhi-nhanvien-detail',
  templateUrl: './nhanvien-detail.component.html',
})
export class NhanvienDetailComponent implements OnInit {
  nhanvien: INhanvien | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nhanvien }) => {
      this.nhanvien = nhanvien;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
