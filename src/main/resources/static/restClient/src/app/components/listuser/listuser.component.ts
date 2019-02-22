import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../user';
import {Router} from '@angular/router';
@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.css']
})
export class ListuserComponent implements OnInit {
  private users;
  constructor(private _userService: UserService, private _router: Router) { }

  ngOnInit() {
    this._userService.getUsers().subscribe((users) => {
      console.log(JSON.stringify(users));
      this.users = users;
    }, (error) => {
      console.log(error);
    });
  }
  deleteUser(user) {
    this._userService.deleteUser(user.id).subscribe((data) => {
      this.users.splice(this.users.indexOf(user), 1);
    }, (error) => {
      console.log(error);
    });
  }
  updateUser(user) {
    this._userService.setter(user);
    this._router.navigate(['/op'] );
  }
  createUser() {
    const user = new User();
    this._userService.setter(user);
    this._router.navigate(['/op']);
  }

}
