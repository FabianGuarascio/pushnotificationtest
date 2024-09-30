package io.ionic.starter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.salesforce.marketingcloud.MCLogListener;


import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogListener;

public class MyLogger implements MCLogListener, LogListener {
  @Override
  public void out(int i, @NonNull String s, @NonNull String s1, @Nullable Throwable throwable) {
    Log.d("LOGGER1", "i: "+i );
    Log.d("LOGGER1", "s: "+ s );
    Log.d("LOGGER1", "s1: "+ s1 );
    if(throwable != null){
      Log.d("LOGGER1", "throwable: "+ throwable.toString() );
    }

  }

  @Override
  public void out(@NonNull com.salesforce.marketingcloud.sfmcsdk.components.logging.LogLevel logLevel, @NonNull String s, @NonNull String s1, @Nullable Throwable throwable) {

    Log.d("LOGGER2", "loglevel: "+logLevel.toString() );
    Log.d("LOGGER2", "s: "+ s );
    Log.d("LOGGER2", "s1: "+ s1 );
    if(throwable != null){
      Log.d("LOGGER1", "throwable: "+ throwable.toString() );
    }
  }


  /*
  // Implement MCLogListener method
  @Override
  public void out(int level, String tag, String message, Throwable throwable) {
    // Handle the log output here
    // level: VERBOSE, DEBUG, INFO, WARN, ERROR
    System.out.println("MCLogListener - Level: " + level + ", Tag: " + tag + ", Message: " + message);
    if (throwable != null) {
      throwable.printStackTrace();
    }
  }

  // Implement LogListener method
  @Override
  public void out(LogListener.LogLevel level, String tag, String message, Throwable throwable) {
    // Handle the log output here
    // level: DEBUG, WARN, ERROR
    System.out.println("LogListener - Level: " + level + ", Tag: " + tag + ", Message: " + message);
    if (throwable != null) {
      throwable.printStackTrace();
    }
  }*/
}
