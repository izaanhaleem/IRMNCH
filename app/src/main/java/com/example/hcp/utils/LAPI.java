package com.example.hcp.utils;

import android.app.Activity;
import android.util.Log;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class LAPI
{
  public static final int DEF_MATCH_SCORE = 45;
  public static final int DEF_QUALITY_SCORE = 30;
  public static final int FALSE = 0;
  public static final int FPINFO_STD_MAX_SIZE = 1024;
  public static final int HEIGHT = 360;
  public static final int IMAGE_SIZE = 92160;
  static final String PAGE_KEY = "LAPI";
  static final File PWFILE;
  public static final int TRUE = 1;
  public static final int WIDTH = 256;
  private static Activity m_content;
  
  static
  {
    try
    {
//      System.loadLibrary("biofp_e_lapi");
      PWFILE = new File("/sys/class/power_supply/usb/device/CONTROL_GPIO114");
      m_content = null;
      //return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      for (;;)
      {
        Log.e("LAPI", "biofp_e_lapi", localUnsatisfiedLinkError);
      }
    }
  }
  
  public LAPI(Activity paramActivity)
  {
    m_content = paramActivity;
  }
  
  private native int CloseDevice(int paramInt);
  
  private native int OpenDevice();
  
  public native int Calibration(int paramInt);
  
  public int CloseDeviceEx(int paramInt)
  {
    paramInt = CloseDevice(paramInt);
    POWER_OFF();
    return paramInt;
  }
  
  public int CompareANSI_Templates(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return CompareTemplates(paramInt, paramArrayOfByte1, paramArrayOfByte2);
  }
  
  public int CompareISO_Templates(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return CompareTemplates(paramInt, paramArrayOfByte1, paramArrayOfByte2);
  }
  
  public native int CompareTemplates(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
  
  public native long CompressToWSQImage(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
  
  public native int CreateANSITemplate(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
  
  public native int CreateISOTemplate(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
  
  public native int GetImage(int paramInt, byte[] paramArrayOfByte);
  
  public native int GetImageQuality(int paramInt, byte[] paramArrayOfByte);
  
  public native String GetVersion();
  
  public native int IsPressFinger(int paramInt, byte[] paramArrayOfByte);
  
  public int OpenDeviceEx()
  {
    POWER_ON();
    return OpenDevice();
  }
  
  protected void POWER_OFF()
  {
    Object localObject = PWFILE;
    try
    {
      localObject = new FileWriter((File)localObject);
      ((FileWriter)localObject).write("1");
      ((FileWriter)localObject).close();
      return;
    }
    catch (Exception localException) {}
  }
  
  protected void POWER_ON()
  {
    Object localObject = PWFILE;
    try
    {
      localObject = new FileReader((File)localObject);
      ((FileReader)localObject).read();
      ((FileReader)localObject).close();
      return;
    }
    catch (Exception localException) {}
  }
  
  public native long UnCompressFromWSQImage(int paramInt, byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2);
}
