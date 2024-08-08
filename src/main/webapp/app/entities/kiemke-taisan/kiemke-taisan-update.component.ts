import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormArray, FormArrayName, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { combineLatest, forkJoin, Observable } from 'rxjs';

import { IKiemkeTaisan, KiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';
import { KiemkeTaisanService } from './kiemke-taisan.service';
import { NhanvienService } from '../nhanvien/nhanvien.service';
import { TaisanService } from '../taisan/taisan.service';
import { startWith } from 'rxjs/operators';
import { Donvi } from '../donvi.enum';

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
  filterBanghis: FormArray = new FormArray([]);
  paginationBanghis: FormArray = new FormArray([]);
  page = 1;
  sizePerPage = 2;
  totalPages = 1;
  sortMaTs = false;
  sortTenTs = false;
  hiddenPagination = false;
  Donvi = Donvi;

  donviSudungOptions = Object.keys(Donvi);

  maTaisanSearchControl = new FormControl('');
  tenTaisanSearchControl = new FormControl('');
  searchMaTs = '';
  searchTenTs = '';

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

    combineLatest([
      this.maTaisanSearchControl.valueChanges.pipe(startWith('')),
      this.tenTaisanSearchControl.valueChanges.pipe(startWith(''))
    ]).subscribe(([maTaisan, tenTaisan]) => {
      this.filter(maTaisan, tenTaisan);
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

  filter(maTaisan: string, tenTaisan: string): void {
    this.searchMaTs = maTaisan;
    this.searchTenTs = tenTaisan;
    if (this.searchMaTs === '' && this.searchTenTs === '') {
      this.hiddenPagination = false;
    } else this.hiddenPagination = true;
    const filtered = this.banghiKiemkes.controls.filter(
      group =>
        (group.get('maTaisan') as FormControl).value.toLowerCase().includes(maTaisan.toLowerCase()) &&
        (group.get('tenTaisan') as FormControl).value.toLowerCase().includes(tenTaisan.toLowerCase())
    );
    this.filterBanghis.clear();
    while (this.filterBanghis.length !== 0) {
      this.filterBanghis.removeAt(0);
    }
    filtered.forEach(group => this.filterBanghis.controls.push(this.fb.group(group.value)));
    this.page = 1;
    this.updatePagination();
    this.totalPages = Math.ceil(this.filterBanghis.length / this.sizePerPage);
  }

  onChanges(): void {
    this.banghiKiemkes.controls.forEach((group, index) => {
      group.get('soluong')!.valueChanges.subscribe(() => {
        this.updateChenhlechSoluong(index);
      });

      group.get('giatriConlai')!.valueChanges.subscribe(() => {
        this.updateChenhlechGTCL(index);
      });
    });
  }

  updateChenhlechSoluong(index: number): number {
    const group = this.banghiKiemkes.at(index) as FormGroup;
    const soluong = group.get('soluong')!.value;
    const soluongBandau = group.get('soluongBandau')!.value;
    return soluong - soluongBandau;
  }

  updateChenhlechGTCL(index: number): number {
    const group = this.banghiKiemkes.at(index) as FormGroup;
    const soluong = group.get('giatriConlai')!.value;
    const soluongBandau = group.get('giatriConlaiBandau')!.value;
    return soluong - soluongBandau;
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
      ghichu: [null]
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
        ghichu: ''
      });
    }
    this.updatePagination();
    this.totalPages = Math.ceil(this.banghiKiemkes.length / this.sizePerPage);
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
              soluongBandau: banghiKiemke.soluongBandau,
              nguyengiaBandau: taisan.nguyengia,
              giatriConlaiBandau: banghiKiemke.giatriConlaiBandau,
              ghichu: banghiKiemke.ghichu
            })
          );
          this.updatePagination();
          this.totalPages = Math.ceil(this.banghiKiemkes.length / this.sizePerPage);
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
      this.updatePagination();
    }
  }

  previousPage(): void {
    if (this.page > 1) {
      this.page -= 1;
      this.updatePagination();
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

  private updatePagination(): void {
    this.paginationBanghis.clear();
    while (this.paginationBanghis.length !== 0) {
      this.paginationBanghis.removeAt(0);
    }
    (this.searchMaTs === '' && this.searchTenTs === '' ? this.banghiKiemkes.controls : this.filterBanghis.controls)
      .slice((this.page - 1) * this.sizePerPage, this.page * this.sizePerPage)
      .forEach(banghi => this.paginationBanghis.controls.push(this.fb.group(banghi.value)));
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

  sort(field: string): void {
    const banghis =
      this.searchMaTs === '' && this.searchTenTs === ''
        ? (this.banghiKiemkes.controls as FormGroup[])
        : (this.filterBanghis.controls as FormGroup[]);
    banghis.sort((a, b) => {
      const A = (a.get(field)!.value || '').toLowerCase();
      const B = (b.get(field)!.value || '').toLowerCase();
      if (field === 'maTaisan') {
        return this.sortMaTs ? B.localeCompare(A) : A.localeCompare(B);
      }
      if (field === 'tenTaisan') {
        return this.sortTenTs ? B.localeCompare(A) : A.localeCompare(B);
      }
      return 0;
    });

    if (field === 'maTaisan') {
      this.sortMaTs = !this.sortMaTs;
    }
    if (field === 'tenTaisan') {
      this.sortTenTs = !this.sortTenTs;
    }

    for (let i = 0; i < banghis.length; i++) {
      this.banghiKiemkes.setControl(i, banghis[i]);
    }
    this.updatePagination();
  }
}
