import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDonviCungcap, DonviCungcap } from 'app/shared/model/donvi-cungcap.model';
import { DonviCungcapService } from './donvi-cungcap.service';

@Component({
  selector: 'jhi-donvi-cungcap-update',
  templateUrl: './donvi-cungcap-update.component.html',
})
export class DonviCungcapUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maDVCC: [null, [Validators.required]],
    ten: [null, [Validators.required]],
    diachi: [],
    soDienThoai: [],
    email: [],
  });

  constructor(
    protected donviCungcapService: DonviCungcapService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ donviCungcap }) => {
      this.updateForm(donviCungcap);
    });
  }

  updateForm(donviCungcap: IDonviCungcap): void {
    this.editForm.patchValue({
      id: donviCungcap.id,
      maDVCC: donviCungcap.maDVCC,
      ten: donviCungcap.ten,
      diachi: donviCungcap.diachi,
      soDienThoai: donviCungcap.soDienThoai,
      email: donviCungcap.email,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const donviCungcap = this.createFromForm();
    if (donviCungcap.id !== undefined) {
      this.subscribeToSaveResponse(this.donviCungcapService.update(donviCungcap));
    } else {
      this.subscribeToSaveResponse(this.donviCungcapService.create(donviCungcap));
    }
  }

  private createFromForm(): IDonviCungcap {
    return {
      ...new DonviCungcap(),
      id: this.editForm.get(['id'])!.value,
      maDVCC: this.editForm.get(['maDVCC'])!.value,
      ten: this.editForm.get(['ten'])!.value,
      diachi: this.editForm.get(['diachi'])!.value,
      soDienThoai: this.editForm.get(['soDienThoai'])!.value,
      email: this.editForm.get(['email'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDonviCungcap>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError(),
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
