package io.ionic.starter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.getcapacitor.BridgeActivity;
import com.mycompany.plugins.example.ExamplePlugin;
import com.salesforce.marketingcloud.MCLogListener;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions;
import com.salesforce.marketingcloud.sfmcsdk.BuildConfig;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogLevel;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogListener;

public class MainActivity extends BridgeActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("ENABLE_PUSH2","esto es en el oncreate");

    var getIntent = getIntent();
    var getIntentExtras = getIntent().getExtras();

    if (getIntent().getExtras() != null) {
      // Extract data from the intent
      String title = getIntent().getExtras().getString("title");
      String body = getIntent().getExtras().getString("body");

      // Store the notification data (in SharedPreferences, SQLite, etc.)
      if (title != null && body != null) {
        Log.d("ENABLE_PUSH2","se trigereo la cosa del get extras");
      }
    }

    registerPlugin(ExamplePlugin.class);
    // Initialize logging before initializing the SDK to avoid losing valuable
    // debugging information.
    if (true) {
      SFMCSdk.setLogging(LogLevel.DEBUG, new LogListener.AndroidLogger());
      MarketingCloudSdk.setLogLevel(MCLogListener.VERBOSE);
      MarketingCloudSdk.setLogListener(new MCLogListener.AndroidLogListener());
    }

    SFMCSdk.configure(this, SFMCSdkModuleConfig.build(builder -> {
      builder.setPushModuleConfig(MarketingCloudConfig.builder()
          .setApplicationId("79b9c631-473d-4a6c-ac8b-5a9f143cb0e9")
          .setAccessToken("vvs4Eu37QWV6HdjP8OZEm9pI")
          .setSenderId("994856421032")
          .setMarketingCloudServerUrl("https://mcch1h4cmmnq241dn8qwt36v6kfq.device.marketingcloudapis.com/")
          .setNotificationCustomizationOptions(
              NotificationCustomizationOptions.create(R.drawable.ic_notification_icon))
          // Other configuration options
          .build(getApplicationContext()));
      return null;
    }), initStatus -> {
      // TODO handle initialization status
      return null;
    });

    MarketingCloudManager mkt = new MarketingCloudManager();
    mkt.getPushToken();

    //MarketingCloudSdk.setLogListener(new MyLogger());
  }
}
