import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';

@Component({
  selector: 'jhi-danhmuc-taisan-detail',
  templateUrl: './danhmuc-taisan-detail.component.html',
})
export class DanhmucTaisanDetailComponent implements OnInit {
  danhmucTaisan: IDanhmucTaisan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ danhmucTaisan }) => {
      this.danhmucTaisan = danhmucTaisan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
