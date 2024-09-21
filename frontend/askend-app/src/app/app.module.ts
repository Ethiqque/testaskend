import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from "./footer/footer.component";
import { HeaderComponent } from "./header/header.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MainComponent } from "./main/main.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import {MatButton, MatFabButton, MatIconButton} from "@angular/material/button";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {FilterDialogComponent} from "./filter-dialog/filter-dialog.component";
import {MatDialogActions, MatDialogContent, MatDialogTitle} from "@angular/material/dialog";
import {MatIcon} from "@angular/material/icon";
import {MatFormField, MatFormFieldModule, MatHint, MatLabel} from "@angular/material/form-field";
import {
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerModule,
    MatDatepickerToggle
} from "@angular/material/datepicker";
import {MatInput, MatInputModule} from "@angular/material/input";
import {provideNativeDateAdapter} from "@angular/material/core";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {
   MatExpansionModule
} from "@angular/material/expansion";

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    MainComponent,
    HeaderComponent,
    FilterDialogComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatFormFieldModule,
        MatDialogTitle,
        MatDialogContent,
        MatDialogActions,
        MatButton,
        MatIcon,
        MatInputModule,
        MatFabButton,
        MatFormField,
        MatDatepickerToggle,
        MatDatepickerModule,
        MatDatepicker,
        MatInput,
        MatHint,
        MatLabel,
        MatDatepickerInput,
        MatIconButton,
        MatRadioButton,
        MatRadioGroup,
        MatExpansionModule,
    ],
  providers: [
    provideAnimationsAsync(),
    provideNativeDateAdapter()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
