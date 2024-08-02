import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IDanhmucTaisan, DanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';
import { DanhmucTaisanService } from './danhmuc-taisan.service';

@Component({
  selector: 'jhi-danhmuc-taisan-update',
  templateUrl: './danhmuc-taisan-update.component.html'
})
export class DanhmucTaisanUpdateComponent implements OnInit {
  isSaving = false;
  ngaytaoDp: any;

  editForm = this.fb.group({
    id: [],
    maDMTS: [null, [Validators.required]],
    ten: [null, [Validators.required]],
    mota: [],
    ngaytao: []
  });

  constructor(protected danhmucTaisanService: DanhmucTaisanService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ danhmucTaisan }) => {
      this.updateForm(danhmucTaisan);
    });
  }

  updateForm(danhmucTaisan: IDanhmucTaisan): void {
    this.editForm.patchValue({
      id: danhmucTaisan.id,
      maDMTS: danhmucTaisan.maDMTS,
      ten: danhmucTaisan.ten,
      mota: danhmucTaisan.mota,
      ngaytao: danhmucTaisan.ngaytao
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const danhmucTaisan = this.createFromForm();
    if (danhmucTaisan.id !== undefined) {
      this.subscribeToSaveResponse(this.danhmucTaisanService.update(danhmucTaisan));
    } else {
      this.subscribeToSaveResponse(this.danhmucTaisanService.create(danhmucTaisan));
    }
  }

  private createFromForm(): IDanhmucTaisan {
    return {
      ...new DanhmucTaisan(),
      id: this.editForm.get(['id'])!.value,
      maDMTS: this.editForm.get(['maDMTS'])!.value,
      ten: this.editForm.get(['ten'])!.value,
      mota: this.editForm.get(['mota'])!.value,
      ngaytao: this.editForm.get(['ngaytao'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDanhmucTaisan>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
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
