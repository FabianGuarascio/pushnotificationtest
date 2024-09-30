import { Injectable } from '@angular/core';
// import { FCM } from '@capacitor-community/fcm';
// import { PushNotifications } from '@capacitor/push-notifications';

@Injectable({
  providedIn: 'root'
})
export class StewanFCMService {



// // external required step
// // register for push
// async resquestPermissions(){
//   await PushNotifications.requestPermissions();

// }
// async register(){
//   await PushNotifications.register();
// }

// // now you can subscribe to a specific topic
// subscribeToTopic(){
//   FCM.subscribeTo({ topic: 'test' })
//     .then(r => alert(`subscribed to topic`))
//     .catch(err => console.log(err));
// }
// subscribeToTopic2(){
//   FCM.subscribeTo({ topic: 'test2' })
//     .then(r => alert(`subscribed to topic test2`))
//     .catch(err => console.log(err));
// }
// // Unsubscribe from a specific topic
// unsubscribeFromTopic(){

//   FCM.unsubscribeFrom({ topic: 'test' })
//     .then(() => alert(`unsubscribed from topic`))
//     .catch(err => console.log(err));
// }

// // Get FCM token instead of the APN one returned by Capacitor
// getToken(){
//   FCM.getToken()
//     .then(r => alert(`Token ${r.token}`))
//     .catch(err => console.log(err));
// }

// // Delete the old FCM token and get a new one
// refreshToken(){
//   FCM.refreshToken()
//     .then(r => alert(`Token ${r.token}`))
//     .catch(err => console.log(err));
// }

// // Remove FCM instance
// deleteInstance(){
//   FCM.deleteInstance()
//     .then(() => alert(`Token deleted`))
//     .catch(err => console.log(err));
// }

// // Enable the auto initialization of the library
// setAutoInit(){
//   FCM.setAutoInit({ enabled: true }).then(() => alert(`Auto init enabled`));
// }

// // Check the auto initialization status
// isAutoIitEnabled(){
//   FCM.isAutoInitEnabled().then(r => {
//     console.log('Auto init is ' + (r.enabled ? 'enabled' : 'disabled'));
//   });
// }
}
