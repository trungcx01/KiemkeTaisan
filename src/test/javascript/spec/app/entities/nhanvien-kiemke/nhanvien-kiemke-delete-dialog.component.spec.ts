import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TaisanTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { NhanvienKiemkeDeleteDialogComponent } from 'app/entities/nhanvien-kiemke/nhanvien-kiemke-delete-dialog.component';
import { NhanvienKiemkeService } from 'app/entities/nhanvien-kiemke/nhanvien-kiemke.service';

describe('Component Tests', () => {
  describe('NhanvienKiemke Management Delete Component', () => {
    let comp: NhanvienKiemkeDeleteDialogComponent;
    let fixture: ComponentFixture<NhanvienKiemkeDeleteDialogComponent>;
    let service: NhanvienKiemkeService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [NhanvienKiemkeDeleteDialogComponent],
      })
        .overrideTemplate(NhanvienKiemkeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NhanvienKiemkeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NhanvienKiemkeService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        }),
      ));
      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.clear();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
