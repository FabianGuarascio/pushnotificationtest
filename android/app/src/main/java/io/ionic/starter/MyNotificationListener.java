package io.ionic.starter;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.getcapacitor.*;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mycompany.plugins.example.ExamplePlugin;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyNotificationListener extends FirebaseMessagingService {

  private static final String TAG = "ENABLE_PUSH4";
  private static final String CHANNEL_ID = "my_channel_id";
  //ExamplePlugin examplePlugin = new ExamplePlugin();
  @Override
  public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
    super.onMessageReceived(remoteMessage);
    // Log the message data for debugging

    Log.d(TAG, "From: asldfkjasldk");
    Log.d(TAG, "From: " + remoteMessage.getFrom());
    RemoteMessage.Notification notification = remoteMessage.getNotification();

    if (remoteMessage.getData().size() > 0) {
      Log.d(TAG, "Data Payload: " + remoteMessage.getData().toString());

      // Extract the data you need to display the notification
      String title = remoteMessage.getData().get("title");
      String message = remoteMessage.getData().get("message");

      // Show the notification manually
      sendNotification(title, message);
    }

    String body = notification != null ? notification.getBody() : null;
    String title = notification != null ? notification.getTitle() : null;



    JSObject data = new JSObject();
    data.put("title", title);
    data.put("body", body);

    ExamplePlugin.sendRemoteMessage(data);
    storeNotification(title, body);

    // Extract the data you need to display the notification
    String title2 = remoteMessage.getData().get("title");
    String message = remoteMessage.getData().get("message");

    // Show the notification manually
    sendNotification(title2, message);

  }

  private void storeNotification(String title, String body) {
    SharedPreferences prefs = getSharedPreferences("notifications", MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();

    // Retrieve current notifications list
    String notifications = prefs.getString("notification_list", "[]");
    JSONArray notificationsArray;
    try {
      notificationsArray = new JSONArray(notifications);
      JSONObject newNotification = new JSONObject();
      newNotification.put("title", title);
      newNotification.put("body", body);
      notificationsArray.put(newNotification);

      // Save updated list
      editor.putString("notification_list", notificationsArray.toString());
      editor.apply();
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void sendNotification(String title, String messageBody) {
    // Create an intent that opens the app when the notification is tapped
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

    // Create a notification manager
    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    // Create a notification channel if necessary (required for Android 8.0+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
      notificationManager.createNotificationChannel(channel);
    }

    // Build the notification
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_notification_icon)  // Replace with your app's notification icon
      .setContentTitle(title)
      .setContentText(messageBody)
      .setAutoCancel(true)
      .setContentIntent(pendingIntent)
      .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    // Display the notification
    notificationManager.notify(0, notificationBuilder.build());
  }
}
