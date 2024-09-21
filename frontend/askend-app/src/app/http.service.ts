import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FilterDTO} from "./main/dto/Dto";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

    private BASE_URL = '/api/v1/';
    constructor(private http: HttpClient) {}


    getAllFilters() {
        return this.http.get(this.BASE_URL + 'filter/filters');
    }

    saveFilter(filterDto: FilterDTO) {
        return this.http.post(this.BASE_URL + 'filter/save', filterDto);
    }

    loadClassifiers(domainCode: string) {
        return this.http.get(this.BASE_URL + `classifier/${domainCode}`);
    }
}
