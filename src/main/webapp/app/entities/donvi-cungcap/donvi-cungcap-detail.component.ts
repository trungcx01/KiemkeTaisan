import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDonviCungcap } from 'app/shared/model/donvi-cungcap.model';

@Component({
  selector: 'jhi-donvi-cungcap-detail',
  templateUrl: './donvi-cungcap-detail.component.html'
})
export class DonviCungcapDetailComponent implements OnInit {
  donviCungcap: IDonviCungcap | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ donviCungcap }) => {
      this.donviCungcap = donviCungcap;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
