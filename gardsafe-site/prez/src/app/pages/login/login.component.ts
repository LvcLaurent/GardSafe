import { Component, OnInit } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

interface Marker {
lat: number;
lng: number;
label?: string;
draggable?: boolean;
}

@Component({
  selector: "app-login",
  templateUrl: "login.component.html"
})
export class LoginComponent implements OnInit {
  constructor(private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router  ) {}

  rapport:string;
  couleur:string;

  identifiant:string;
  password:string;
  password2:string;
  prenom:string;
  nom:string;
  email:string;

  creation(){
    if(this.password==this.password2){
      this.http.post<any>('https://laurentetpauline.fr:445/utilisateur/creation', { login: this.identifiant, password:this.password, prenom:this.prenom, nom:this.nom, mail:this.email }).subscribe(data => {
              console.log(data);
              if(data.code == 4001){
                this.rapport=data.rapport;
              } else {
                this.rapport=data.message + ' : ' + data.info;
              }
          });
    } else {
      alert("password différent");
    }
  }

  ngOnInit() {
    this.rapport='';
      
  }
}
