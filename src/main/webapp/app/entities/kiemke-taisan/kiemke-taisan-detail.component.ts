import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';

@Component({
  selector: 'jhi-kiemke-taisan-detail',
  templateUrl: './kiemke-taisan-detail.component.html',
})
export class KiemkeTaisanDetailComponent implements OnInit {
  kiemkeTaisan: IKiemkeTaisan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kiemkeTaisan }) => {
      this.kiemkeTaisan = kiemkeTaisan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
