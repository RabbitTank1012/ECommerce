import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdlistadminComponent } from './prodlistadmin.component';

describe('ProdlistadminComponent', () => {
  let component: ProdlistadminComponent;
  let fixture: ComponentFixture<ProdlistadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProdlistadminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProdlistadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
