import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { INhanvien, Nhanvien } from 'app/shared/model/nhanvien.model';
import { NhanvienService } from './nhanvien.service';

@Component({
  selector: 'jhi-nhanvien-update',
  templateUrl: './nhanvien-update.component.html'
})
export class NhanvienUpdateComponent implements OnInit {
  isSaving = false;
  ngayThamgiaDp: any;

  editForm = this.fb.group({
    id: [],
    maNV: [null, [Validators.required]],
    ten: [null, [Validators.required]],
    chucvu: [null, [Validators.required]],
    diachi: [],
    gioitinh: [null, [Validators.required]],
    sdt: [null, [Validators.required, Validators.maxLength(11)]],
    phongban: [null, [Validators.required]],
    email: [null, [Validators.required]],
    ngayThamgia: []
  });

  constructor(protected nhanvienService: NhanvienService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nhanvien }) => {
      this.updateForm(nhanvien);
    });
  }

  updateForm(nhanvien: INhanvien): void {
    this.editForm.patchValue({
      id: nhanvien.id,
      maNV: nhanvien.maNV,
      ten: nhanvien.ten,
      chucvu: nhanvien.chucvu,
      diachi: nhanvien.diachi,
      gioitinh: nhanvien.gioitinh,
      sdt: nhanvien.sdt,
      phongban: nhanvien.phongban,
      email: nhanvien.email,
      ngayThamgia: nhanvien.ngayThamgia
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nhanvien = this.createFromForm();
    if (nhanvien.id !== undefined) {
      this.subscribeToSaveResponse(this.nhanvienService.update(nhanvien));
    } else {
      this.subscribeToSaveResponse(this.nhanvienService.create(nhanvien));
    }
  }

  private createFromForm(): INhanvien {
    return {
      ...new Nhanvien(),
      id: this.editForm.get(['id'])!.value,
      maNV: this.editForm.get(['maNV'])!.value,
      ten: this.editForm.get(['ten'])!.value,
      chucvu: this.editForm.get(['chucvu'])!.value,
      diachi: this.editForm.get(['diachi'])!.value,
      gioitinh: this.editForm.get(['gioitinh'])!.value,
      sdt: this.editForm.get(['sdt'])!.value,
      phongban: this.editForm.get(['phongban'])!.value,
      email: this.editForm.get(['email'])!.value,
      ngayThamgia: this.editForm.get(['ngayThamgia'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INhanvien>>): void {
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
