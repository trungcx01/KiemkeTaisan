import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBanghiKiemke } from 'app/shared/model/banghi-kiemke.model';

@Component({
  selector: 'jhi-banghi-kiemke-detail',
  templateUrl: './banghi-kiemke-detail.component.html'
})
export class BanghiKiemkeDetailComponent implements OnInit {
  banghiKiemke: IBanghiKiemke | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banghiKiemke }) => {
      this.banghiKiemke = banghiKiemke;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
