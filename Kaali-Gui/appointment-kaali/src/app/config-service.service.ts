import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ConfigServiceService {

  constructor(private http: HttpClient) { }
  company: string = "Gautam";
  branch: string = "OCC";
  year: number = 0;
  month: number = 1;
  day: number = 1;
  dayStr:String="";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  postBlog(blog: any, operation: String) {
    let url = "http://localhost:8080/" + operation;
    return this.http.post(url, blog, this.httpOptions);
  }
}
