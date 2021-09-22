package com.example.hcp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Importer;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.Reader.Priority;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import com.example.hcp.R;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.FormatHelper;
import com.example.hcp.utils.Globals;
import com.example.hcp.utils.ImageUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004;

public class VerificationActivity extends Activity {


	private String m_sn = "";
	private String m_deviceName = "";
	private String m_enginError;
	Importer importer = UareUGlobal.GetImporter();
	private Reader m_reader = null;
	private Bitmap m_bitmap = null;
	private Bitmap m_bitmap64 = null;
	private ImageView m_imgView;
	private boolean m_reset = false;
	private CountDownTimer m_timer = null;
	private TextView m_text;
	private TextView m_text_conclusion;
	private Engine m_engine = null;
	private Fmd m_fmd = null;
	private Fmd getfmd, fmd1;
	String ba;
	private int m_score = -1;
	private int m_Fscore = -1;
	private boolean m_first = true;
	private boolean m_resultAvailableToDisplay = false;
	private Reader.CaptureResult cap_result = null;
	ArrayList<String> Finger_co  ;
	public SharedPreferences sp;
	public ProgressDialog progressDialog;

	static AlertDialog.Builder alertbox;
	static AlertDialog alertDialog;

	private void initializeActivity()
	{
		m_enginError = "";
		m_sn = getIntent().getExtras().getString("serial_number");
		m_deviceName = getIntent().getExtras().getString("device_name");
		m_imgView = (ImageView) findViewById(R.id.bitmap_image);
		m_bitmap = Globals.GetLastBitmap();
		if (m_bitmap == null) m_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.finger);
		m_imgView.setImageBitmap(m_bitmap);
		m_text_conclusion = (TextView) findViewById(R.id.text_conclusion);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_engine);
		sp = getSharedPreferences("session", Activity.MODE_PRIVATE);
		progressDialog = new ProgressDialog(VerificationActivity.this);

		initializeActivity();

		// initiliaze dp sdk
		try {
			Context applContext = getApplicationContext();
			m_reader = Globals.getInstance().getReader(m_deviceName, applContext);
			m_reader.Open(Priority.EXCLUSIVE);
			m_engine = UareUGlobal.GetEngine();
		} catch (Exception e) {
			Log.w("dummyMenu", "error during init of reader");
			m_sn = "";
			m_deviceName = "";
			onBackPressed();
			return;
		}

		// updates UI continuously
		m_timer = new CountDownTimer(250, 250) {
			public void onTick(long millisUntilFinished) {
			}

			public void onFinish()
			{

				m_imgView.setImageBitmap(m_bitmap);
				m_imgView.invalidate();

				if (cap_result != null && cap_result.quality != null) {
					switch (cap_result.quality) {
						case FAKE_FINGER:
							m_text_conclusion.setText("Fake finger");
							m_bitmap = null;
							break;
						case NO_FINGER:
							m_text_conclusion.setText("No finger");
							m_bitmap = null;
							break;
						case CANCELED:
							m_text_conclusion.setText("Capture cancelled");
							break;
						case TIMED_OUT:
							m_text_conclusion.setText("Capture timed out");
							break;
						case FINGER_TOO_LEFT:
							m_text_conclusion.setText("Finger too left");
							break;
						case FINGER_TOO_RIGHT:
							m_text_conclusion.setText("Finger too right");
							break;
						case FINGER_TOO_HIGH:
							m_text_conclusion.setText("Finger too high");
							break;
						case FINGER_TOO_LOW:
							m_text_conclusion.setText("Finger too low");
							break;
						case FINGER_OFF_CENTER:
							m_text_conclusion.setText("Finger off center");
							break;
						case SCAN_SKEWED:
							m_text_conclusion.setText("Scan skewed");
							break;
						case SCAN_TOO_SHORT:
							m_text_conclusion.setText("Scan too short");
							break;
						case SCAN_TOO_LONG:
							m_text_conclusion.setText("Scan too long");
							break;
						case SCAN_TOO_SLOW:
							m_text_conclusion.setText("Scan too slow");
							break;
						case SCAN_TOO_FAST:
							m_text_conclusion.setText("Scan too fast");
							break;
						case SCAN_WRONG_DIRECTION:
							m_text_conclusion.setText("Wrong direction");
							break;
						case READER_DIRTY:
							m_text_conclusion.setText("Reader dirty");
							break;
						case GOOD:
							m_text_conclusion.setText("");
							break;
						default:
							if (cap_result.image == null) {
								m_text_conclusion.setText("An error occurred");
							}
					}
				}

				if (!m_enginError.isEmpty())

				{
					m_text_conclusion.setText("Engine: " + m_enginError);
				}

				else if (m_fmd == null)

				{
					if (m_resultAvailableToDisplay) {
						if (m_text_conclusion.getText().length() == 0) {
							DecimalFormat formatting = new DecimalFormat("##.######");
							m_text_conclusion.setText("Result: " + (m_score < (0x7FFFFFFF / 100000) ? "match" : "no match") + "");

							if (m_score < (0x7FFFFFFF / 100000))

							{
								m_text_conclusion.setText("Processing...");

								String base64String = ImageUtil.convert(m_bitmap64);

								Constants.FmdTest = getfmd;
								Constants.FmdBase64 = base64String;
								Constants.width = getfmd.getWidth();
								Constants.height = getfmd.getHeight();
								Constants.cbeff_id = getfmd.getCbeffId();
								Constants.quality = getfmd.getResolution();
								Log.d("------------------",Constants.Fmd + "");
								SaveImage();

							}

							else

							{
								m_text_conclusion.setText("Finger Mismatched");
							}

						}
					} else {
						m_text_conclusion.setText("Place any finger on the reader");
					}
				}

				else

				{
					m_text_conclusion.setText("Place the same finger again!");
				}

				if (!m_reset)
					m_timer.start();
			}
		}.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
//	            try {
				m_reset = false;
				while (!m_reset) {
					try {
						cap_result = m_reader.Capture(Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT, 500, -1);
					} catch (Exception e) {
						Log.w("dummyMenu", "error during capture: " + e.toString());
						m_sn = "";
						m_deviceName = "";
						onBackPressed();
					}

					m_resultAvailableToDisplay = false;

					// an error occurred
					if (cap_result == null || cap_result.image == null) continue;

					try {
						m_enginError = "";

						// save bitmap image locally
						m_bitmap64 = Globals.GetBitmapFromRaw(cap_result.image.getViews()[0].getImageData(), cap_result.image.getViews()[0].getWidth(), cap_result.image.getViews()[0].getHeight());
						m_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fingersave);
						if (m_fmd == null)
							m_fmd = m_engine.CreateFmd(cap_result.image, ANSI_378_2004);
						else {
							m_score = m_engine.Compare(m_fmd, 0, m_engine.CreateFmd(cap_result.image, ANSI_378_2004), 0);
							Constants.cap_result = cap_result.image;
							getfmd = m_fmd;
							m_fmd = null;
							m_resultAvailableToDisplay = true;
						}

					} catch (Exception e) {
						m_enginError = e.toString();
						Log.w("dummyMenu", "Engine error: " + e.toString());
					}


				}

			}
		}).start();
	}

	private void SaveImage() {

		if(m_first)

		{
			try

			{
				m_text_conclusion.setText("Successfully Captured.");
				m_first = false;

				Intent i = new Intent(this, VerificationActivity.class);
				//i.putExtra("baseCode" , baseCode);
				setResult(RESULT_OK, i);
				finish();
				m_reader.Close();
				//m_reset = true;

			}

			catch (Exception e)

			{
				e.printStackTrace();
			}
		}
	}

	//-------------------------//

//	public void end() throws UareUException
//
//	{
//		final Dialog dialog = new Dialog(VerificationActivity.this);
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		dialog.setCancelable(false);
//		dialog.setContentView(R.layout.customdialog_layout);
//		TextView heading = (TextView) dialog.findViewById(R.id.textviewx);
//		TextView detail = (TextView) dialog.findViewById(R.id.text_dialog);
//		heading.setText("Alert!");
//		detail.setText("Record Already Exist.");
//		Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
//		dialogButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//				onBackPressed();
//			}
//		});
//
//		dialog.show();
//
//	}
//
//	public void FingerCode()
//
//	{
//		Finger_co = null;
//		//new GetFingerBarber().execute();
//
//	}

	//-------------------------//

	@Override
	public void onBackPressed()

	{
		try

		{
			m_reset = true;
			m_reader.CancelCapture();
			m_reader.Close();
		}

		catch (Exception e)

		{
			Log.w("dummyMenu", "error during reader shutdown");
		}

		Intent i = new Intent();
		i.putExtra("serial_number", m_sn);
		i.putExtra("device_name", m_deviceName);
		setResult(Activity.RESULT_CANCELED, i);
		finish();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)

	{
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.activity_engine);
		initializeActivity();
	}

}