package cz.martin.winconk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import cz.martin.winconk.SocketUtils.Connect;
import cz.martin.winconk.SocketUtils.Sender;

public class ConnectedActivity extends AppCompatActivity
{
  Thread networkingThread;
  Connect connect;
  ViewFlipper flipper;
  private final String meet_prefix = "GM";
  private final String teams_prefix = "MT";
  private TextView text;

  @SuppressLint ("ClickableViewAccessibility")
  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);

    setContentView (R.layout.activity_connected);
    WifiManager wifi = (WifiManager) getApplicationContext ().getSystemService (Context.WIFI_SERVICE);
    wifi.setWifiEnabled (true);

    View decorView = getWindow ().getDecorView ();
    int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    decorView.setOnSystemUiVisibilityChangeListener (visibility ->
    {
      decorView.setSystemUiVisibility (uiOptions);
    });




    String ip = getIntent ().getExtras ().getString ("serverIp");
    connect = new Connect (ip , this);

    networkingThread = new Thread (connect);
    networkingThread.start ();

    flipper = findViewById (R.id.flipper);
    //flipper.setDisplayedChild (flipper.indexOfChild (findViewById (R.id.selection)));
    flipper.setDisplayedChild (flipper.indexOfChild (findViewById (R.id.mouseLayout)));

    text = findViewById (R.id.coords);

    TextView textView = findViewById (R.id.mouseImage);
    textView.setOnTouchListener ((View v , MotionEvent event) ->
    {
      float x = event.getX ();
      float y = event.getY ();
      text.setText ("X: " + x + "\nY: " + y);
      return false;
    });
  }

  @Override
  public void onWindowFocusChanged (boolean hasFocus)
  {
    super.onWindowFocusChanged (hasFocus);
    getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
  }

  //==================================================================================================

  public void goBack (View v)
  {
    flipper.setDisplayedChild (flipper.indexOfChild (findViewById (R.id.selection)));
  }

//==================================================================================================

  public void startMeet (View v)
  {
    flipper.setDisplayedChild (flipper.indexOfChild (findViewById (R.id.meetLayout)));
  }

  public void meetMute (View v)
  {
    new Thread (new Sender (meet_prefix + "mute\n" , connect.getPrintWriter ())).start ();
  }

  public void meetCam (View v)
  {
    new Thread (new Sender (meet_prefix + "cam\n" , connect.getPrintWriter ())).start ();
  }

  public void meetHand (View v)
  {
    new Thread (new Sender (meet_prefix + "hand\n" , connect.getPrintWriter ())).start ();
  }

  public void meetLeave (View v)
  {
    new Thread (new Sender (meet_prefix + "leave\n" , connect.getPrintWriter ())).start ();
  }

//==================================================================================================

  public void startTeams (View v)
  {
    flipper.setDisplayedChild (flipper.indexOfChild (findViewById (R.id.teamsLayout)));
  }

  public void teamsMute (View v)
  {
    new Thread (new Sender (teams_prefix + "mute\n" , connect.getPrintWriter ())).start ();
  }

  public void teamsCam (View v)
  {
    new Thread (new Sender (teams_prefix + "cam\n" , connect.getPrintWriter ())).start ();
  }

  public void teamsHand (View v)
  {
    new Thread (new Sender (teams_prefix + "hand\n" , connect.getPrintWriter ())).start ();
  }

  public void teamsLeave (View v)
  {
    new Thread (new Sender (teams_prefix + "leave\n" , connect.getPrintWriter ())).start ();
  }

//==================================================================================================

  public void startVideo (View v)
  {
    flipper.setDisplayedChild (flipper.indexOfChild (findViewById (R.id.videoLayout)));
  }

}