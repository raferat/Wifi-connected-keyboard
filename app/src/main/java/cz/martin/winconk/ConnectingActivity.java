package cz.martin.winconk;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ConnectingActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
  private ZXingScannerView mScannerView;

  @Override
  public void onCreate (Bundle state)
  {
    super.onCreate (state);
    String[] permissions = new String[]
            {
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE

            };
    for (String permission : permissions)
    {
      if (ContextCompat.checkSelfPermission (this , permission) == PackageManager.PERMISSION_DENIED)
      {
        ActivityCompat.requestPermissions (this , permissions , 1);
        break;
      }
    }
    mScannerView = new ZXingScannerView (this);
    setContentView (mScannerView);
  }

  @Override
  public void onResume ()
  {
    super.onResume ();
    mScannerView.setResultHandler (this);
    mScannerView.startCamera ();
  }

  @Override
  public void onPause ()
  {
    super.onPause ();
    mScannerView.stopCamera ();
  }

  @Override
  public void handleResult (Result rawResult)
  {
    Intent i = new Intent (this , ConfirmActivity.class);
    Bundle b = new Bundle ();
    b.putString ("serverIp" , rawResult.getText ());
    i.putExtras (b);

    startActivity (i);
  }
}
