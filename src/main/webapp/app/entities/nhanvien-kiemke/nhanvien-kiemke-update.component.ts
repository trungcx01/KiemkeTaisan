import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { INhanvienKiemke, NhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';
import { NhanvienKiemkeService } from './nhanvien-kiemke.service';
import { INhanvien } from 'app/shared/model/nhanvien.model';
import { NhanvienService } from 'app/entities/nhanvien/nhanvien.service';
import { IKiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';
import { KiemkeTaisanService } from 'app/entities/kiemke-taisan/kiemke-taisan.service';

type SelectableEntity = INhanvien | IKiemkeTaisan;

@Component({
  selector: 'jhi-nhanvien-kiemke-update',
  templateUrl: './nhanvien-kiemke-update.component.html',
})
export class NhanvienKiemkeUpdateComponent implements OnInit {
  isSaving = false;

  nhanviens: INhanvien[] = [];

  kiemketaisans: IKiemkeTaisan[] = [];

  editForm = this.fb.group({
    id: [],
    daidien: [null, [Validators.required]],
    vaitro: [null, [Validators.required]],
    nhanvienId: [null, Validators.required],
    kiemkeTaisanId: [],
  });

  constructor(
    protected nhanvienKiemkeService: NhanvienKiemkeService,
    protected nhanvienService: NhanvienService,
    protected kiemkeTaisanService: KiemkeTaisanService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nhanvienKiemke }) => {
      this.updateForm(nhanvienKiemke);

      this.nhanvienService
        .query({ filter: 'nhanvienkiemke-is-null' })
        .pipe(
          map((res: HttpResponse<INhanvien[]>) => {
            return res.body ? res.body : [];
          }),
        )
        .subscribe((resBody: INhanvien[]) => {
          if (!nhanvienKiemke.nhanvienId) {
            this.nhanviens = resBody;
          } else {
            this.nhanvienService
              .find(nhanvienKiemke.nhanvienId)
              .pipe(
                map((subRes: HttpResponse<INhanvien>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                }),
              )
              .subscribe((concatRes: INhanvien[]) => {
                this.nhanviens = concatRes;
              });
          }
        });

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

  updateForm(nhanvienKiemke: INhanvienKiemke): void {
    this.editForm.patchValue({
      id: nhanvienKiemke.id,
      daidien: nhanvienKiemke.daidien,
      vaitro: nhanvienKiemke.vaitro,
      nhanvienId: nhanvienKiemke.nhanvienId,
      kiemkeTaisanId: nhanvienKiemke.kiemkeTaisanId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nhanvienKiemke = this.createFromForm();
    if (nhanvienKiemke.id !== undefined) {
      this.subscribeToSaveResponse(this.nhanvienKiemkeService.update(nhanvienKiemke));
    } else {
      this.subscribeToSaveResponse(this.nhanvienKiemkeService.create(nhanvienKiemke));
    }
  }

  private createFromForm(): INhanvienKiemke {
    return {
      ...new NhanvienKiemke(),
      id: this.editForm.get(['id'])!.value,
      daidien: this.editForm.get(['daidien'])!.value,
      vaitro: this.editForm.get(['vaitro'])!.value,
      nhanvienId: this.editForm.get(['nhanvienId'])!.value,
      kiemkeTaisanId: this.editForm.get(['kiemkeTaisanId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INhanvienKiemke>>): void {
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
