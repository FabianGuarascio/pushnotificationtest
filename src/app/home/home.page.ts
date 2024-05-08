import { Component, inject } from '@angular/core';
import { Capacitor } from '@capacitor/core';
import { PushNotifications } from '@capacitor/push-notifications';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import { PushNotificationsService } from '../push-notifications.service';


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent],
})
export class HomePage {
  pushNotifService = inject(PushNotificationsService)



  ngOnInit(){
    this.pushNotifService.registrationListener()
  }

  registrar(){
    this.pushNotifService.registerNotifications()
  }
  desregistrar(){
    this.pushNotifService.unregistrer()
  }
}
