import { Component, Input } from '@angular/core';
import { Need } from '../need';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { NeedService } from '../need.service';


@Component({
  selector: 'app-need-detail',
  templateUrl: './need-detail.component.html',
  styleUrls: ['./need-detail.component.css']
})
export class NeedDetailComponent {
  @Input() selectedNeed?: Need;
  //selectedNeed?: Need;
  hideDetails = false;

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getNeed();
  }
  
  getNeed(): void {
    const name = this.route.snapshot.paramMap.get('name');
    if (name) {
    this.needService.getNeed(name)
      .subscribe(need => this.selectedNeed = need);
    }
  }
  goBack(): void {
    this.location.back();
  }

toggleHideDetails() {
  this.hideDetails = !this.hideDetails;
}
save(): void {
  if (this.selectedNeed && this.isNeedValid()) {
    this.needService.updateNeed(this.selectedNeed)
      .subscribe(() => this.goBack());
  }
}

delete(): void {
  if (this.selectedNeed && confirm('Are you sure you want to delete this need?')) {
      this.needService.deleteNeed(this.selectedNeed).subscribe(() => this.goBack());
  }
}

isNeedValid(): boolean {
  return (
    this.selectedNeed?.cost !== undefined && this.selectedNeed.cost >= 0 &&
    this.selectedNeed?.currentQuantity !== undefined && this.selectedNeed.currentQuantity >= 0 &&
    this.selectedNeed?.requiredQuantity !== undefined && this.selectedNeed.requiredQuantity >= 0
  );
}
}