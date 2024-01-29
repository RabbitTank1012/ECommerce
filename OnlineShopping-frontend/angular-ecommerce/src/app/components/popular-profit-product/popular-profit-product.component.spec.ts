import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopularProfitProductComponent } from './popular-profit-product.component';

describe('PopularProfitProductComponent', () => {
  let component: PopularProfitProductComponent;
  let fixture: ComponentFixture<PopularProfitProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PopularProfitProductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PopularProfitProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
