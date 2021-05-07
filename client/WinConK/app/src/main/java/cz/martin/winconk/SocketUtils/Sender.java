package cz.martin.winconk.SocketUtils;

import java.io.PrintWriter;

public class Sender implements Runnable
{
  String msg;
  PrintWriter output;

  public Sender (String message , PrintWriter output)
  {
    msg = message;
    this.output = output;
  }

  @Override
  public void run ()
  {
    try
    {
      output.write (msg);
      output.flush ();
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
  }
}