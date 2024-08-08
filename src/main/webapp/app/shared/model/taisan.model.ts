import { Moment } from 'moment';
import { IBanghiKiemke } from 'app/shared/model/banghi-kiemke.model';

export interface ITaisan {
  id?: string;
  maTaisan?: string;
  tenTaisan?: string;
  tenRutgon?: string;
  model?: string;
  serial?: string;
  loaiTaisan?: number;
  mota?: string;
  ngayNhan?: Moment;
  ngaySudung?: Moment;
  ngaySanxuat?: Moment;
  thoigianSudung?: number;
  ngayHethan?: Moment;
  haomon?: number;
  nguyengia?: number;
  soHopdongMua?: string;
  soHoadonMua?: string;
  ngayHoadon?: Moment;
  trangthai?: number;
  hangSanxuat?: string;
  nuocSanxuat?: string;
  congsuatSudung?: number;
  dientichSudung?: number;
  donviTinh?: string;
  giatriConlai?: number;
  nguon?: string;
  vitri?: string;
  donviQuanly?: string;
  donviSudung?: string;
  soluong?: number;
  nguoiQuanlyTen?: string;
  nguoiQuanlyId?: string;
  banghiKiemkes?: IBanghiKiemke[];
  danhmucTaisanTen?: string;
  danhmucTaisanId?: string;
  donviCungcapTen?: string;
  donviCungcapId?: string;
}

export class Taisan implements ITaisan {
  constructor(
    public id?: string,
    public maTaisan?: string,
    public tenTaisan?: string,
    public tenRutgon?: string,
    public model?: string,
    public serial?: string,
    public loaiTaisan?: number,
    public mota?: string,
    public ngayNhan?: Moment,
    public ngaySudung?: Moment,
    public ngaySanxuat?: Moment,
    public thoigianSudung?: number,
    public ngayHethan?: Moment,
    public haomon?: number,
    public nguyengia?: number,
    public soHopdongMua?: string,
    public soHoadonMua?: string,
    public ngayHoadon?: Moment,
    public trangthai?: number,
    public hangSanxuat?: string,
    public nuocSanxuat?: string,
    public congsuatSudung?: number,
    public dientichSudung?: number,
    public donviTinh?: string,
    public giatriConlai?: number,
    public nguon?: string,
    public vitri?: string,
    public donviQuanly?: string,
    public donviSudung?: string,
    public soluong?: number,
    public nguoiQuanlyTen?: string,
    public nguoiQuanlyId?: string,
    public banghiKiemkes?: IBanghiKiemke[],
    public danhmucTaisanTen?: string,
    public danhmucTaisanId?: string,
    public donviCungcapTen?: string,
    public donviCungcapId?: string
  ) {}
}
