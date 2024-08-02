import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { forkJoin, Observable } from 'rxjs';

import { IKiemkeTaisan, KiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';
import { KiemkeTaisanService } from './kiemke-taisan.service';
import { NhanvienService } from '../nhanvien/nhanvien.service';
import { TaisanService } from '../taisan/taisan.service';

@Component({
  selector: 'jhi-kiemke-taisan-update',
  templateUrl: './kiemke-taisan-update.component.html',
  styleUrls: ['./kiemke-taisan-update.component.scss']
})
export class KiemkeTaisanUpdateComponent implements OnInit {
  isSaving = false;
  ngayLapphieuDp: any;
  ngayKiemkeDp: any;
  nhanviens: any;
  taisans: any;
  page = 1;
  sizePerPage = 1;
  donviSudungOptions: string[] = [
    'Phòng Hành chính - Quản trị',
    'Phòng Kế hoạch - Tài chính',
    'Phòng Vật tư - Thiết bị y tế',
    'Phòng Điều dưỡng',
    'Khoa Khám bệnh và Điều trị',
    'Phòng Nghiên cứu và Phát triển',
    'Phòng Công nghệ Thông tin',
    'Phòng Hậu cần',
    'Phòng Kiểm soát nội bộ',
    'Phòng Quản lý chất lượng',
    'Khoa Nội',
    'Khoa Ngoại',
    'Khoa Sản',
    'Khoa Nhi',
    'Khoa Phẫu thuật',
    'Khoa Gây mê hồi sức',
    'Khoa Chẩn đoán hình ảnh',
    'Khoa Xét nghiệm',
    'Khoa Dược',
    'Khoa Vật lý trị liệu và Phục hồi chức năng',
    'Khoa Y học cổ truyền',
    'Khoa Truyền nhiễm',
    'Khoa Dinh dưỡng',
    'Khoa Kiểm soát nhiễm khuẩn',
    'Khoa Hóa sinh',
    'Khoa Vi sinh',
    'Phòng Quản lý chất thải y tế',
    'Khoa Thần kinh',
    'Khoa Tim mạch',
    'Khoa Hô hấp',
    'Khoa Tiêu hóa',
    'Khoa Thận - Tiết niệu',
    'Khoa Nội tiết',
    'Khoa Cấp cứu',
    'Khoa Chăm sóc giảm nhẹ',
    'Phòng Bảo vệ',
    'Phòng Thư viện'
  ];

  editForm = this.fb.group({
    id: [],
    sophieu: [null, [Validators.required]],
    ngayLapphieu: [null, [Validators.required]],
    ngayKiemke: [null, [Validators.required]],
    donviSudung: [null, [Validators.required]],
    ghichu: [],
    nhanvienKiemkes: this.fb.array([this.nhanvienKiemkeForm()]),
    banghiKiemkes: this.fb.array([this.banghiKiemkeForm()])
  });

  constructor(
    protected kiemkeTaisanService: KiemkeTaisanService,
    protected activatedRoute: ActivatedRoute,
    protected nhanvienService: NhanvienService,
    protected taisanService: TaisanService,
    private cdr: ChangeDetectorRef,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kiemkeTaisan }) => {
      this.updateForm(kiemkeTaisan);
      if (!kiemkeTaisan.id) {
        this.nhanvienKiemkes.push(this.nhanvienKiemkeForm());
      }
    });

    this.nhanvienService.query().subscribe((res: any) => {
      this.nhanviens = res.body;
      this.cdr.detectChanges();
    });
  }

  onDonviSudungChange(): void {
    this.editForm.get('donviSudung')!.valueChanges.subscribe((res: any) => {
      this.taisanService.getByDonviSudung(res).subscribe((val: any) => {
        this.taisans = val.body;
        this.updateBanghiKiemke();
      });
    });
  }

  nhanvienKiemkeForm(): FormGroup {
    return this.fb.group({
      nhanvienId: [null, [Validators.required]],
      daidien: [null, [Validators.required]],
      vaitro: [null, [Validators.required]]
    });
  }

  banghiKiemkeForm(): FormGroup {
    return this.fb.group({
      taisanId: [null, [Validators.required]],
      maTaisan: [],
      tenTaisan: [],
      donviSudung: [],
      soluongBandau: [],
      nguyengiaBandau: [],
      giatriConlaiBandau: [],
      soluong: [null, [Validators.required]],
      nguyengia: [null, [Validators.required]],
      giatriConlai: [null, [Validators.required]],
      tinhtrangSudung: [null, [Validators.required]],
      hinhthucXuly: [null, [Validators.required]],
      ghichu: []
    });
  }

  get nhanvienKiemkes(): FormArray {
    return this.editForm.get('nhanvienKiemkes') as FormArray;
  }

  get banghiKiemkes(): FormArray {
    return this.editForm.get('banghiKiemkes') as FormArray;
  }

  updateBanghiKiemke(): void {
    while (this.banghiKiemkes.length > this.taisans.length) {
      this.banghiKiemkes.removeAt(this.banghiKiemkes.length - 1);
    }

    while (this.banghiKiemkes.length < this.taisans.length) {
      this.banghiKiemkes.push(this.banghiKiemkeForm());
    }

    for (let i = 0; i < this.taisans.length; i++) {
      const banghi = this.banghiKiemkes.at(i) as FormGroup;
      banghi.patchValue({
        taisanId: this.taisans[i].id,
        nguyengia: this.taisans[i].nguyengia,
        soluong: this.taisans[i].soluong,
        giatriConlai: this.taisans[i].giatriConlai,
        maTaisan: this.taisans[i].maTaisan,
        tenTaisan: this.taisans[i].tenTaisan,
        donviSudung: this.taisans[i].donviSudung,
        soluongBandau: this.taisans[i].soluong,
        nguyengiaBandau: this.taisans[i].nguyengia,
        giatriConlaiBandau: this.taisans[i].giatriConlai,
        ghichu: ['']
      });
    }
    this.cdr.detectChanges();
  }

  addNewNhanvienKiemke(): void {
    if (this.nhanvienKiemkes.length < 3) {
      this.nhanvienKiemkes.push(this.nhanvienKiemkeForm());
    }
  }

  removeNhanvienKiemke(i: Required<number>): void {
    if (this.nhanvienKiemkes.length > 1) {
      this.nhanvienKiemkes.removeAt(i);
    }
  }

  // getControlNameOfBanghiTaisan(i: number, controlName: string): any {
  //   return this.banghiKiemkes.at(i).get(controlName)!.value;
  // }

  updateForm(kiemkeTaisan: IKiemkeTaisan): void {
    this.editForm.patchValue({
      id: kiemkeTaisan.id,
      sophieu: kiemkeTaisan.sophieu,
      ngayLapphieu: kiemkeTaisan.ngayLapphieu,
      ngayKiemke: kiemkeTaisan.ngayKiemke,
      donviSudung: kiemkeTaisan.donviSudung,
      ghichu: kiemkeTaisan.ghichu
    });

    this.banghiKiemkes.clear();

    if (kiemkeTaisan.banghiKiemkes) {
      const taisanRequests = kiemkeTaisan.banghiKiemkes.map(banghiKiemke =>
        this.taisanService.find(banghiKiemke.taisanId ? banghiKiemke.taisanId : '')
      );

      forkJoin(taisanRequests).subscribe(responses => {
        responses.forEach((res, index) => {
          const banghiKiemke = kiemkeTaisan.banghiKiemkes![index];
          const taisan = res.body!;

          this.banghiKiemkes.push(
            this.fb.group({
              taisanId: banghiKiemke.taisanId,
              nguyengia: banghiKiemke.nguyengia,
              soluong: banghiKiemke.soluong,
              giatriConlai: banghiKiemke.giatriConlai,
              tinhtrangSudung: banghiKiemke.tinhtrangSudung,
              hinhthucXuly: banghiKiemke.hinhthucXuly,
              maTaisan: taisan.maTaisan,
              tenTaisan: taisan.tenTaisan,
              donviSudung: taisan.donviSudung,
              soluongBandau: taisan.soluong,
              nguyengiaBandau: taisan.nguyengia,
              giatriConlaiBandau: taisan.giatriConlai,
              ghichu: banghiKiemke.ghichu
            })
          );
        });
      });
    }

    this.nhanvienKiemkes.clear();
    if (kiemkeTaisan.nhanvienKiemkes) {
      const nhanvienKiemkesRequest = kiemkeTaisan.nhanvienKiemkes;
      nhanvienKiemkesRequest.map(nhanvienKiemke => {
        this.nhanvienKiemkes.push(
          this.fb.group({
            nhanvienId: nhanvienKiemke.nhanvienId,
            daidien: nhanvienKiemke.daidien,
            vaitro: nhanvienKiemke.vaitro
          })
        );
      });
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const kiemkeTaisan = this.createFromForm();
    if (kiemkeTaisan.id !== undefined) {
      this.subscribeToSaveResponse(this.kiemkeTaisanService.update(kiemkeTaisan));
    } else {
      this.subscribeToSaveResponse(this.kiemkeTaisanService.create(kiemkeTaisan));
    }
  }

  nextPage(): void {
    if (this.page < Math.ceil(this.banghiKiemkes.controls.length / this.sizePerPage)) {
      this.page += 1;
      this.cdr.detectChanges();
    }
  }

  private createFromForm(): IKiemkeTaisan {
    return {
      ...new KiemkeTaisan(),
      id: this.editForm.get(['id'])!.value || undefined,
      sophieu: this.editForm.get(['sophieu'])!.value,
      ngayLapphieu: this.editForm.get(['ngayLapphieu'])!.value,
      ngayKiemke: this.editForm.get(['ngayKiemke'])!.value,
      donviSudung: this.editForm.get(['donviSudung'])!.value,
      ghichu: this.editForm.get(['ghichu'])!.value,
      banghiKiemkes: this.editForm.get('banghiKiemkes')!.value,
      nhanvienKiemkes: this.editForm.get('nhanvienKiemkes')!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKiemkeTaisan>>): void {
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
