package io.ionic.starter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;

public class MarketingCloudManager {
  public void getPushToken() {
    SFMCSdk.requestSdk(new SFMCSdkReadyListener() {
      @Override
      public void ready(@NonNull SFMCSdk sdk) {
        sdk.mp(new PushModuleReadyListener() {
          @Override
          public void ready(@NonNull PushModuleInterface pushModuleInterface) {
            String pushToken = pushModuleInterface.getPushMessageManager().getPushToken();
            PushMessageManager pushToken2 = pushModuleInterface.getPushMessageManager();
            var registrationManager = pushModuleInterface.getRegistrationManager();
            var inAppMessageManager = pushModuleInterface.getInAppMessageManager();
            var analyticsManager = pushModuleInterface.getAnalyticsManager();
            var notificationManager = pushModuleInterface.getNotificationManager();
            var eventManager = pushModuleInterface.getEventManager();

            PushMessageManager pushMessageManager = pushModuleInterface.getPushMessageManager();
            notificationManager.registerNotificationMessageDisplayedListener(notificationMessage -> {
              Log.d("ENABLE_PUSH3", "Sanwango");
            });






            sdk.identity.setProfileId("kjhkj", ModuleIdentifier.PUSH);




            if (pushToken2 != null) {
              Log.d("ENABLE_PUSH", "logro meter el enable push");
              JSONObject debuginfo = pushToken2.getPushDebugInfo();
              JSONObject sdkState = sdk.getSdkState();
              pushToken2.enablePush();


              boolean isEnabled = pushToken2.isPushEnabled();

              String deviceId = registrationManager.getDeviceId();
              String systemToken = registrationManager.getSystemToken();

              String contactKey = registrationManager.getContactKey();
              String signedString = registrationManager.getSignedString();
              Set<String> tags = registrationManager.getTags();





              Log.d("ENABLE_PUSH", "habilitado: " + isEnabled);
              Log.d("ENABLE_PUSH", "json object: " + debuginfo.toString());
              Log.d("ENABLE_PUSH", "Device Id: " + deviceId);
              Log.d("ENABLE_PUSH", "Systemtoken: " + systemToken);
              Log.d("ENABLE_PUSH", "contactKey: " + contactKey);
              Log.d("ENABLE_PUSH", "tags: " + tags.toString());
              Log.d("ENABLE_PUSH", "signedStrings: " + signedString);
              Log.d("ENABLE_PUSH_TOKEN", "TOKEN push: " + pushToken2.getPushToken());
              //Log.d("ENABLE_PUSH", ": " + sdkState.toString());

              /*
              String tempDir = System.getProperty("java.io.tmpdir");
              String filePath = tempDir + "output.txt";
              try (FileWriter file = new FileWriter(filePath)) {
                file.write(sdkState.toString(4)); // 4 is for pretty-printing with indentation
                file.flush();
                Log.d("ENABLE_PUSH", "se guardo el sdk state ");
                Log.d("ENABLE_PUSH","File saved at: " + filePath);
              } catch (IOException e) {
                Log.d("ENABLE_PUSH", "hubo un error " + e.toString());
                e.printStackTrace();
              } catch (JSONException e) {
                Log.d("ENABLE_PUSH", "hubo un error 2");
                  throw new RuntimeException(e);
              }

              */
            }

            /*
            if (pushToken != null) {
              Log.d("TOKEN", pushToken);
            } else {
              Log.d("TOKEN", "Push token is null");
            }*/
          }
        });
      }
    });
  }

}
