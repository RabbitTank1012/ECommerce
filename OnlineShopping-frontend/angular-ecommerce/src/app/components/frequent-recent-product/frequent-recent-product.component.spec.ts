import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FrequentRecentProductComponent } from './frequent-recent-product.component';

describe('FrequentRecentProductComponent', () => {
  let component: FrequentRecentProductComponent;
  let fixture: ComponentFixture<FrequentRecentProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FrequentRecentProductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FrequentRecentProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
