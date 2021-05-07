package cz.martin.winconk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity
{
  String ip;

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_confirm);

    TextView text = findViewById (R.id.question);
    ip = getIntent () . getExtras () . getString ("serverIp");

    if ( ip == null )
      finishAffinity ();

    String string = getString (R.string.activity_confirm_qestion) + ip + "?";

    text . setText ( string );
  }

  public void yes ( View v )
  {
    Intent i = new Intent ( this , ConnectedActivity. class );
    Bundle b = new Bundle ();
    b . putString ( "serverIp" , ip );
    i . putExtras (b);
    startActivity (i);
  }

  public void no ( View v )
  {
    Intent i = new Intent ( this , ConnectingActivity . class );
    startActivity (i);
  }
}