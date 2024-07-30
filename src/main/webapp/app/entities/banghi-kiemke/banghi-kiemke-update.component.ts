import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBanghiKiemke, BanghiKiemke } from 'app/shared/model/banghi-kiemke.model';
import { BanghiKiemkeService } from './banghi-kiemke.service';
import { ITaisan } from 'app/shared/model/taisan.model';
import { TaisanService } from 'app/entities/taisan/taisan.service';
import { IKiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';
import { KiemkeTaisanService } from 'app/entities/kiemke-taisan/kiemke-taisan.service';

type SelectableEntity = ITaisan | IKiemkeTaisan;

@Component({
  selector: 'jhi-banghi-kiemke-update',
  templateUrl: './banghi-kiemke-update.component.html',
})
export class BanghiKiemkeUpdateComponent implements OnInit {
  isSaving = false;

  taisans: ITaisan[] = [];

  kiemketaisans: IKiemkeTaisan[] = [];

  editForm = this.fb.group({
    id: [],
    soluong: [null, [Validators.required]],
    nguyengia: [null, [Validators.required]],
    giatriConlai: [null, [Validators.required]],
    ghichu: [],
    tinhtrangSudung: [],
    hinhthucXuly: [],
    taisanId: [null, Validators.required],
    kiemkeTaisanId: [],
  });

  constructor(
    protected banghiKiemkeService: BanghiKiemkeService,
    protected taisanService: TaisanService,
    protected kiemkeTaisanService: KiemkeTaisanService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banghiKiemke }) => {
      this.updateForm(banghiKiemke);

      this.taisanService
        .query()
        .pipe(
          map((res: HttpResponse<ITaisan[]>) => {
            return res.body ? res.body : [];
          }),
        )
        .subscribe((resBody: ITaisan[]) => (this.taisans = resBody));

      this.kiemkeTaisanService
        .query()
        .pipe(
          map((res: HttpResponse<IKiemkeTaisan[]>) => {
            return res.body ? res.body : [];
          }),
        )
        .subscribe((resBody: IKiemkeTaisan[]) => (this.kiemketaisans = resBody));
    });
  }

  updateForm(banghiKiemke: IBanghiKiemke): void {
    this.editForm.patchValue({
      id: banghiKiemke.id,
      soluong: banghiKiemke.soluong,
      nguyengia: banghiKiemke.nguyengia,
      giatriConlai: banghiKiemke.giatriConlai,
      ghichu: banghiKiemke.ghichu,
      tinhtrangSudung: banghiKiemke.tinhtrangSudung,
      hinhthucXuly: banghiKiemke.hinhthucXuly,
      taisanId: banghiKiemke.taisanId,
      kiemkeTaisanId: banghiKiemke.kiemkeTaisanId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const banghiKiemke = this.createFromForm();
    if (banghiKiemke.id !== undefined) {
      this.subscribeToSaveResponse(this.banghiKiemkeService.update(banghiKiemke));
    } else {
      this.subscribeToSaveResponse(this.banghiKiemkeService.create(banghiKiemke));
    }
  }

  private createFromForm(): IBanghiKiemke {
    return {
      ...new BanghiKiemke(),
      id: this.editForm.get(['id'])!.value,
      soluong: this.editForm.get(['soluong'])!.value,
      nguyengia: this.editForm.get(['nguyengia'])!.value,
      giatriConlai: this.editForm.get(['giatriConlai'])!.value,
      ghichu: this.editForm.get(['ghichu'])!.value,
      tinhtrangSudung: this.editForm.get(['tinhtrangSudung'])!.value,
      hinhthucXuly: this.editForm.get(['hinhthucXuly'])!.value,
      taisanId: this.editForm.get(['taisanId'])!.value,
      kiemkeTaisanId: this.editForm.get(['kiemkeTaisanId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBanghiKiemke>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
