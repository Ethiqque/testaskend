import {Component, OnInit} from '@angular/core';
import { MatDialogRef } from "@angular/material/dialog";
import { CriteriaDTO, FilterDTO } from "../main/dto/Dto";
import {HttpService} from "../http.service";

@Component({
    selector: 'app-filter-dialog',
    templateUrl: './filter-dialog.component.html',
    styleUrls: ['./filter-dialog.component.css']
})
export class FilterDialogComponent implements OnInit {

    comparisonTypes: any;
    criteriaTypes: any;
    criterias: CriteriaDTO[] = [];
    filter: FilterDTO = {
        id: null,
        name: '',
        criterias: [],
        createdAt: null
    };
    defaultCriteriaType: any;
    valdationErrors: any[] = [];

    constructor(private dialogRef: MatDialogRef<FilterDialogComponent>,
                private service: HttpService) {
    }

    async ngOnInit(): Promise<void> {
        await this.loadCriteriaTypes();
        await this.loadComparisonTypes();
        await this.addRow();
    }

    onClose(): void {
        this.dialogRef.close();
    }

    onSave(): void {
        this.filter.criterias = this.criterias;
        this.service.saveFilter(this.filter).subscribe(
            (types) => {
                this.dialogRef.close();
            },
            (error) => {
                this.valdationErrors = error.error.errors;
                console.error('Error saving filter data:', error);
            }
        );
    }

    deleteRow(index: number) {
        this.criterias.splice(index, 1);
    }

    async addRow() {
        const newCriteria: CriteriaDTO = {
            id: null,
            criteriaType: this.defaultCriteriaType,
            condition: null,
            value: null,
            isSelected: false
        };
        this.criterias.push(newCriteria);
    }

    private loadCriteriaTypes(): Promise<void> {
        return new Promise((resolve, reject) => {
            this.service.loadClassifiers('CRITERIA_TYPE').subscribe(
                (types) => {
                    this.criteriaTypes = types;
                    this.defaultCriteriaType = this.criteriaTypes.find((x: { code: string; }) => x.code === 'CRITERIA_TYPE_AMOUNT');
                    resolve();
                },
                (error) => {
                    console.error('Error loading criteria data:', error);
                    reject(error);
                }
            );
        });
    }

    private loadComparisonTypes(): Promise<void> {
        return new Promise((resolve, reject) => {
            this.service.loadClassifiers('CRITERIA_COMPARISON').subscribe(
                (compTypes) => {
                    this.comparisonTypes = compTypes;
                    resolve();
                },
                (error) => {
                    console.error('Error loading comparison data:', error);
                    reject(error);
                }
            );
        });
    }

    onCriteriaChange(event: any) {
        const selectedCriteria = event.value;

        this.criterias.forEach(criteria => {
            criteria.isSelected = criteria === selectedCriteria;
        });
    }
}
