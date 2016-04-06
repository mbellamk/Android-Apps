/*
 * InClass 04
 * Group 7b
 * Madhavi Bhat
 * Prem
 * Raghu
 * 
 */

package com.example.inclass04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class SlideShowActivity extends Activity {
	
	ProgressDialog progDialog;
	int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		progDialog = new ProgressDialog(SlideShowActivity.this);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_show);

		Bundle extras = getIntent().getExtras();
		String viewUrl = (String) extras.get(MainActivity.KEY_URL);

		if (viewUrl != null && !viewUrl.trim().equals("")) {
			PhotoAsync aTask = new PhotoAsync();
			aTask.execute(viewUrl);
		}
	}

	class PhotoAsync extends AsyncTask<String, Integer, List<Bitmap>> {

		@Override
		protected List<Bitmap> doInBackground(String... params) {
			
			Bitmap bitmap = null;
			List<Bitmap> urlList = null ;
			try {
				URL url = new URL(params[0]);
				
				
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setRequestMethod("GET");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String line = "";
				urlList = new ArrayList<Bitmap>();
				while ((line = reader.readLine()) != null) {

					

					URL imgUrl = new URL(line);
					HttpURLConnection imgCon = (HttpURLConnection) imgUrl.openConnection();
					bitmap = BitmapFactory.decodeStream(imgCon.getInputStream());
					
					urlList.add(bitmap);
					
					/*ImageView iv = (ImageView) findViewById(R.id.id_photo);
					if (bitmap != null) {
						iv.setImageBitmap(bitmap);
					}*/

				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return urlList;

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			progDialog.setMessage("Loading Image Links");
			progDialog.show();
		}

		@Override
		protected void onPostExecute(final List<Bitmap> result) {// TODO Auto-generated method stub
			super.onPostExecute(result);
			progDialog.dismiss();
			progDialog.setMessage("Loading Image");
			
				progDialog.show();
				
				ImageView iv = (ImageView) findViewById(R.id.id_photo);
					iv.setImageBitmap(result.get(0));
				/*try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				iv.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						ImageView iv = (ImageView) findViewById(R.id.id_photo);
						index++;
						if(index < result.size()){
							
							iv.setImageBitmap(result.get(index));
						}
						
						
					}
				}, 5000);
				progDialog.dismiss();
			
			
			
			
			
			
			/*Intent mainInt = new Intent(SlideShowActivity.this, MainActivity.class);

			startActivity(mainInt);*/
		}

		

	}
}
