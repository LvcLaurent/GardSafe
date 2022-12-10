import { Component, OnInit } from "@angular/core";

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  {
    path: "/dashboard",
    title: "Dashboard",
    icon: "icon-chart-pie-36",
    class: ""
  },
  {
    path: "/icons",
    title: "Gestion Compte",
    icon: "icon-atom",
    class: ""
  },
  {
    path: "/maps",
    title: "Liste des opérations",
    icon: "icon-pin",
    class: "" },
  {
    path: "/notifications",
    title: "Notifications",
    icon: "icon-bell-55",
    class: ""
  },

  {
    path: "/user",
    title: "User Profile",
    icon: "icon-single-02",
    class: ""
  },
  {
    path: "/tables",
    title: "Configuration",
    icon: "icon-puzzle-10",
    class: ""
  },
  {
    path: "/typography",
    title: "Catégorie",
    icon: "icon-align-center",
    class: "Test"
  },
  {
    path: "/rtl",
    title: "Import Fichier CSV",
    icon: "icon-world",
    class: ""
  }
];
export const ROUTESNONAUTH: RouteInfo[] = [
  {
    path: "/login",
    title: "Creation Compte",
    icon: "icon-single-02",
    class: ""
  }
];

@Component({
  selector: "app-sidebar",
  templateUrl: "./sidebar.component.html",
  styleUrls: ["./sidebar.component.css"]
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() {}

  ngOnInit() {
    this.menuItems = ROUTESNONAUTH.filter(menuItem => menuItem );
  }
  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }
}
