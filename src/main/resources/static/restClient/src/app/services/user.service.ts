import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {User} from '../user';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8080/api';
  // private headers = new Headers({'Content-Type': 'application/json'});
  // private options = new RequestOptions({headers: this.headers});
  private user: User;
  constructor(private _http: HttpClient) { }
  getUsers() {
    return this._http.get(this.baseUrl + '/employees').map((response) => response).catch(this.errorHandler);
  }
  getUser(id: string) {
    return this._http.get(this.baseUrl + '/employees/' + id)
      .map((response) => response).catch(this.errorHandler);
  }
  deleteUser(id: string) {
    return this._http.delete(this.baseUrl + '/employees/' + id)
      .map((response) => response).catch(this.errorHandler);
  }
  createUser(user: User) {
    return this._http.post(this.baseUrl + '/employees/', JSON.stringify(user))
      .map((response) => response).catch(this.errorHandler);
  }
  updateUser(user: User) {
    return this._http.put(this.baseUrl + '/employees/', JSON.stringify(user))
      .map((response) => response).catch(this.errorHandler);
  }
  errorHandler(error: Response) {
    return Observable.throw(error || 'Internal Error!');
  }
  setter(user) {
    this.user = user;
  }
  getter() {
    return this.user;
  }
}
