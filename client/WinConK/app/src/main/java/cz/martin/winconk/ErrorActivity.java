package cz.martin.winconk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ErrorActivity extends AppCompatActivity
{

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_error);
    String error = getIntent ().getExtras ().getString ("error");
    if ( error == null )
      finishAffinity ();
    TextView t = findViewById (R.id.errorText);
    t . setText (error);
  }

  public void backToStart ( View v )
  {
    Intent i = new Intent (this,ConnectingActivity.class);
    startActivity (i);
  }
}