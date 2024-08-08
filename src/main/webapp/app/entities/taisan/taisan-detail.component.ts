import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITaisan } from 'app/shared/model/taisan.model';

@Component({
  selector: 'jhi-taisan-detail',
  templateUrl: './taisan-detail.component.html'
})
export class TaisanDetailComponent implements OnInit {
  taisan: ITaisan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taisan }) => {
      this.taisan = taisan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
