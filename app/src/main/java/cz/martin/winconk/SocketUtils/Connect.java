package cz.martin.winconk.SocketUtils;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import cz.martin.winconk.ErrorActivity;
import cz.martin.winconk.R;

public class Connect implements Runnable
{
  private AppCompatActivity connected;
  Socket s;
  PrintWriter output;
  String ip;

  public Connect (String ip , AppCompatActivity c)
  {
    connected = c;
    this.ip = ip;
  }

  @Override
  public void run ()
  {
    try
    {
      s = new Socket (ip , 8998);
      output = new PrintWriter (s.getOutputStream ());
    }
    catch (ConnectException e)
    {
      sendError ();
    }
    catch (IOException e)
    {
      e.printStackTrace ();
    }

  }

  private void sendError()
  {
    connected.runOnUiThread (() ->
    {
      Intent i = new Intent (connected , ErrorActivity.class);
      Bundle b = new Bundle ();
      b.putString ("error" , connected.getString (R.string.activity_error_qrerror));
      i.putExtras (b);
      connected.startActivity (i);
    });
  }

  public PrintWriter getPrintWriter ()
  {
    return output;
  }
}