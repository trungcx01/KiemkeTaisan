import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITaisan, Taisan } from 'app/shared/model/taisan.model';
import { TaisanService } from './taisan.service';
import { INhanvien } from 'app/shared/model/nhanvien.model';
import { NhanvienService } from 'app/entities/nhanvien/nhanvien.service';
import { IDanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';
import { DanhmucTaisanService } from 'app/entities/danhmuc-taisan/danhmuc-taisan.service';
import { IDonviCungcap } from 'app/shared/model/donvi-cungcap.model';
import { DonviCungcapService } from 'app/entities/donvi-cungcap/donvi-cungcap.service';

type SelectableEntity = INhanvien | IDanhmucTaisan | IDonviCungcap;

@Component({
  selector: 'jhi-taisan-update',
  templateUrl: './taisan-update.component.html'
})
export class TaisanUpdateComponent implements OnInit {
  isSaving = false;

  nguoiquanlies: INhanvien[] = [];

  danhmuctaisans: IDanhmucTaisan[] = [];

  donvicungcaps: IDonviCungcap[] = [];
  ngayNhanDp: any;
  ngaySudungDp: any;
  ngaySanxuatDp: any;
  ngayHethanDp: any;
  ngayHoadonDp: any;

  editForm = this.fb.group({
    id: [],
    maTaisan: [null, [Validators.required]],
    tenTaisan: [null, [Validators.required]],
    tenRutgon: [null, [Validators.required]],
    model: [],
    serial: [],
    loaiTaisan: [null, [Validators.required]],
    mota: [],
    ngayNhan: [null, [Validators.required]],
    ngaySudung: [],
    ngaySanxuat: [],
    thoigianSudung: [],
    ngayHethan: [],
    haomon: [],
    nguyengia: [],
    soHopdongMua: [],
    soHoadonMua: [],
    ngayHoadon: [],
    trangthai: [],
    hangSanxuat: [],
    nuocSanxuat: [],
    congsuatSudung: [],
    dientichSudung: [],
    donviTinh: [],
    giatriConlai: [],
    tangNguyengia: [],
    nguon: [],
    vitri: [],
    donviQuanly: [],
    donviSudung: [],
    nguoiQuanlyId: [null, Validators.required],
    danhmucTaisanId: [null, Validators.required],
    donviCungcapId: [],
    soluong: [null, Validators.required]
  });

  constructor(
    protected taisanService: TaisanService,
    protected nhanvienService: NhanvienService,
    protected danhmucTaisanService: DanhmucTaisanService,
    protected donviCungcapService: DonviCungcapService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taisan }) => {
      this.updateForm(taisan);

      this.nhanvienService
        .query({ filter: 'taisan-is-null' })
        .pipe(
          map((res: HttpResponse<INhanvien[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: INhanvien[]) => {
          if (!taisan.nguoiQuanlyId) {
            this.nguoiquanlies = resBody;
          } else {
            this.nhanvienService
              .find(taisan.nguoiQuanlyId)
              .pipe(
                map((subRes: HttpResponse<INhanvien>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: INhanvien[]) => {
                this.nguoiquanlies = concatRes;
              });
          }
        });

      this.danhmucTaisanService
        .query()
        .pipe(
          map((res: HttpResponse<IDanhmucTaisan[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IDanhmucTaisan[]) => (this.danhmuctaisans = resBody));

      this.donviCungcapService
        .query()
        .pipe(
          map((res: HttpResponse<IDonviCungcap[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IDonviCungcap[]) => (this.donvicungcaps = resBody));
    });
  }

  updateForm(taisan: ITaisan): void {
    this.editForm.patchValue({
      id: taisan.id,
      maTaisan: taisan.maTaisan,
      tenTaisan: taisan.tenTaisan,
      tenRutgon: taisan.tenRutgon,
      model: taisan.model,
      serial: taisan.serial,
      loaiTaisan: taisan.loaiTaisan,
      mota: taisan.mota,
      ngayNhan: taisan.ngayNhan,
      ngaySudung: taisan.ngaySudung,
      ngaySanxuat: taisan.ngaySanxuat,
      thoigianSudung: taisan.thoigianSudung,
      ngayHethan: taisan.ngayHethan,
      haomon: taisan.haomon,
      nguyengia: taisan.nguyengia,
      soHopdongMua: taisan.soHopdongMua,
      soHoadonMua: taisan.soHoadonMua,
      ngayHoadon: taisan.ngayHoadon,
      trangthai: taisan.trangthai,
      hangSanxuat: taisan.hangSanxuat,
      nuocSanxuat: taisan.nuocSanxuat,
      congsuatSudung: taisan.congsuatSudung,
      dientichSudung: taisan.dientichSudung,
      donviTinh: taisan.donviTinh,
      giatriConlai: taisan.giatriConlai,
      tangNguyengia: taisan.tangNguyengia,
      nguon: taisan.nguon,
      vitri: taisan.vitri,
      donviQuanly: taisan.donviQuanly,
      donviSudung: taisan.donviSudung,
      nguoiQuanlyId: taisan.nguoiQuanlyId,
      danhmucTaisanId: taisan.danhmucTaisanId,
      donviCungcapId: taisan.donviCungcapId,
      soluong: taisan.soluong
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const taisan = this.createFromForm();
    if (taisan.id !== undefined) {
      this.subscribeToSaveResponse(this.taisanService.update(taisan));
    } else {
      this.subscribeToSaveResponse(this.taisanService.create(taisan));
    }
  }

  private createFromForm(): ITaisan {
    return {
      ...new Taisan(),
      id: this.editForm.get(['id'])!.value,
      maTaisan: this.editForm.get(['maTaisan'])!.value,
      tenTaisan: this.editForm.get(['tenTaisan'])!.value,
      tenRutgon: this.editForm.get(['tenRutgon'])!.value,
      model: this.editForm.get(['model'])!.value,
      serial: this.editForm.get(['serial'])!.value,
      loaiTaisan: this.editForm.get(['loaiTaisan'])!.value,
      mota: this.editForm.get(['mota'])!.value,
      ngayNhan: this.editForm.get(['ngayNhan'])!.value,
      ngaySudung: this.editForm.get(['ngaySudung'])!.value,
      ngaySanxuat: this.editForm.get(['ngaySanxuat'])!.value,
      thoigianSudung: this.editForm.get(['thoigianSudung'])!.value,
      ngayHethan: this.editForm.get(['ngayHethan'])!.value,
      haomon: this.editForm.get(['haomon'])!.value,
      nguyengia: this.editForm.get(['nguyengia'])!.value,
      soHopdongMua: this.editForm.get(['soHopdongMua'])!.value,
      soHoadonMua: this.editForm.get(['soHoadonMua'])!.value,
      ngayHoadon: this.editForm.get(['ngayHoadon'])!.value,
      trangthai: this.editForm.get(['trangthai'])!.value,
      hangSanxuat: this.editForm.get(['hangSanxuat'])!.value,
      nuocSanxuat: this.editForm.get(['nuocSanxuat'])!.value,
      congsuatSudung: this.editForm.get(['congsuatSudung'])!.value,
      dientichSudung: this.editForm.get(['dientichSudung'])!.value,
      donviTinh: this.editForm.get(['donviTinh'])!.value,
      giatriConlai: this.editForm.get(['giatriConlai'])!.value,
      tangNguyengia: this.editForm.get(['tangNguyengia'])!.value,
      nguon: this.editForm.get(['nguon'])!.value,
      vitri: this.editForm.get(['vitri'])!.value,
      donviQuanly: this.editForm.get(['donviQuanly'])!.value,
      donviSudung: this.editForm.get(['donviSudung'])!.value,
      nguoiQuanlyId: this.editForm.get(['nguoiQuanlyId'])!.value,
      danhmucTaisanId: this.editForm.get(['danhmucTaisanId'])!.value,
      donviCungcapId: this.editForm.get(['donviCungcapId'])!.value,
      soluong: this.editForm.get(['soluong'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaisan>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
