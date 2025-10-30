import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MerchantAddEditComponent } from './merchant-add-edit.component';

describe('MerchantAddEditComponent', () => {
  let component: MerchantAddEditComponent;
  let fixture: ComponentFixture<MerchantAddEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MerchantAddEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MerchantAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
