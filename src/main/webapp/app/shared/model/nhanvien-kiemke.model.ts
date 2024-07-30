export interface INhanvienKiemke {
  id?: string;
  daidien?: string;
  vaitro?: number;
  nhanvienTen?: string;
  nhanvienId?: string;
  kiemkeTaisanId?: string;
}

export class NhanvienKiemke implements INhanvienKiemke {
  constructor(
    public id?: string,
    public daidien?: string,
    public vaitro?: number,
    public nhanvienTen?: string,
    public nhanvienId?: string,
    public kiemkeTaisanId?: string,
  ) {}
}
