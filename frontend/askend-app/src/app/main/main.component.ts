import { Component, OnInit } from '@angular/core';
import {FilterDialogComponent} from "../filter-dialog/filter-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {FilterDTO} from "./dto/Dto";
import {HttpService} from "../http.service";

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
    filters: FilterDTO[] = [];

    constructor(
        private dialog: MatDialog,
        private service: HttpService) {}

    ngOnInit(): void {
        this.getAllFilters();
    }

    openAddingModal() {
        const dialogRef = this.dialog.open(FilterDialogComponent, {
            width: '85vh',
            disableClose: true
        });

        dialogRef.afterClosed().subscribe(() => {
            this.getAllFilters();
        });
    }

    private getAllFilters() {
        this.service.getAllFilters().subscribe(
            (filters: any) => {
                this.filters = filters;
            },
            (error) => {
                console.error('Error saving filter data:', error);
            }
        );
    }
}
