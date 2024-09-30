import { ChangeDetectorRef, Component, inject, signal } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';

import { Example } from 'second-custom-plugin';
import { Preferences } from '@capacitor/preferences';
import { JsonPipe } from '@angular/common';

type Notification = {title:string,body:string,timeId:string}

const setName = async () => {
  await Preferences.set({
    key: 'name',
    value: 'Max',
  });
};

const checkName = async () => {
  const { value } = await Preferences.get({ key: 'name' });

  console.log(`Hello ${value}!`);
};

const removeName = async () => {
  await Preferences.remove({ key: 'name' });
};

const setArray = async (key:string, array:any[]) => {
  await Preferences.set({
    key: key,
    value: JSON.stringify(array),
  });
  console.log("se agrego el coso")
};

function generateTimeBasedId() {
  const now = new Date();
  
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0'); // Months are zero-indexed
  const day = String(now.getDate()).padStart(2, '0');
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');

  return `id_${year}-${month}-${day}-${hours}-${minutes}-${seconds}`;
}

// Example usage


const getArray = async (key:string) => {
  const { value } = await Preferences.get({ key: key });

  if(value){
    return JSON.parse(value)
  }else{
    const emptyArr:any[] = []
    setArray('notifications',emptyArr)
    return emptyArr
  }
};

const addElementToArray = async (key:string, element:Notification) => {
  const array = await getArray(key);
  array.push(element);
  await setArray(key, array);

};

const removeElementFromArray = async (key:string, element:Notification) => {
  let array = await getArray(key);
  array = array.filter((item:Notification) => item.timeId !== element.timeId);
  await setArray(key, array);
};

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent,JsonPipe],
})
export class HomePage {

  private cdr= inject(ChangeDetectorRef)
  
  mockArray:Notification[] = []
  mockArraySignal = signal<Notification[]>([])


  async ngOnInit(){
    console.log("ng on init")
    this.mockArray = await getArray('notifications')
    this.mockArraySignal.set(await getArray('notifications'))
    await this.registrar()
  }

  async getStoredNotifications(){
    const coso = await Example.getStoredNotifications()
    console.log("coso",coso)
    const cosoParseado = JSON.parse(coso.notifications)
    console.log("coso pero parseado",cosoParseado)
  }

  async setNotification(){
    const notif:Notification = {title:"titulo de aca",body:"el body",timeId:generateTimeBasedId()}
    await addElementToArray('notifications',notif)
    this.mockArray = await getArray('notifications')
  }
  async setNotification2(notification:Pick<Notification, 'title'| 'body'>){
    const notif:Notification = {
      title:notification.title,
      body:notification.body,
      timeId:generateTimeBasedId()
    }

    await addElementToArray('notifications',notif)

    

    console.log("el await en set notification",await getArray('notifications'))
    this.mockArray = await getArray('notifications')
    setTimeout(async () => {
      getArray('notifications').then(res=>{

        this.mockArraySignal.set(res)
      })
      
    }, 1000);
  }
  async borrar(notif : Notification){
    await removeElementFromArray('notifications',notif)
    this.mockArray = await getArray('notifications')
  }

  setName(){
    setName()
  }

  ver(){
    checkName()
  }

  remove(){
    removeName()
  }
  
  async registrar(){
    await Example.addListener('pushNotificationReceived',(msg:Pick<Notification, 'title'| 'body'>)=>{
      this.setNotification2(msg)
      this.cdr.detectChanges()
      console.log("paso el listener")
    })
  }


  
  
  // pushNotifService = inject(PushNotificationsService)
  // stewanFCM = inject(StewanFCMService)

  

  // ngOnInit(){
  //   this.pushNotifService.registrationListener()
  // }

  // registrar(){
  //   this.pushNotifService.registerNotifications()
  // }
  // desregistrar(){
  //   this.pushNotifService.unregistrer()
  // }

  // regisTopic(){
  //   this.stewanFCM.subscribeToTopic()
  // }
  // regisTopic2(){
  //   this.stewanFCM.subscribeToTopic2()
  // }
  // unSubscribeFomTopic(){
  //   this.stewanFCM.unsubscribeFromTopic()
  // }
}
