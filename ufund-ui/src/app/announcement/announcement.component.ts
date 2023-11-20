import { Component, OnInit } from '@angular/core';
import { Announcement } from '../announcement';
import { AnnouncementsService } from '../announcements.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit{
  announcements: Announcement[] = [];
  announcementText: string = "";

  constructor(
    private announcementService: AnnouncementsService,
    private router: Router, private http: HttpClient,
  ) { }

  ngOnInit(): void {
    this.getAnnouncements();
  }

  getAnnouncements(): void {
    this.announcementService.getAnnouncements().subscribe(announcements => {
      this.announcements = announcements.filter(announcement => announcement.id);
    });
  }

  createAnnouncement() {
    this.announcementService.addAnnouncement(this.announcementText).subscribe(() => {
      this.getAnnouncements();
      this.announcementText = "";
    });
  }

  deleteAnnouncement(id: number) {
    this.announcementService.deleteAnnouncement(id).subscribe(() => {
      this.getAnnouncements();
    });
  }
  
}
