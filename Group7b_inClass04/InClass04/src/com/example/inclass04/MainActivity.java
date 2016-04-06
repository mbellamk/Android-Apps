/*
 * InClass 04
 * Group 7b
 * Madhavi Bhat
 * Prem
 * Raghu
 * 
 */
package com.example.inclass04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private static final int POOL_SIZE = 2;

	public static final String KEY_URL = "slideShowUrl";

	private int selectedCompl;

	ProgressDialog progDialog;

	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler = new Handler(new Handler.Callback() {

			public boolean handleMessage(Message msg) {

				switch (msg.what) {

				case DoRunnableWork.OPER_START:
					progDialog.show();
					break;

				case DoRunnableWork.OPER_PROGRESS:
					progDialog.setProgress((Integer)msg.obj);
					break;

				case DoRunnableWork.OPER_FINISH:

					
					TextView averageView = (TextView) findViewById(R.id.id_avg_number);

					String result =Double.toString( msg.getData().getDouble(DoRunnableWork.RESULT_AVG));
					averageView.setText(result);

					progDialog.dismiss();
					break;

				}

				return false;
			}
		});

		SeekBar complexityBar = (SeekBar) findViewById(R.id.id_compl_seek);

		complexityBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

				TextView complexityView = (TextView) findViewById(R.id.id_complexity_display);

				complexityView.setText(seekBar.getProgress() + " Times");
				selectedCompl = seekBar.getProgress();

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});

		Button appBtn = (Button) findViewById(R.id.id_btn_gen_async);

		appBtn.setOnClickListener(this);

		appBtn = (Button) findViewById(R.id.id_btn_photo);

		appBtn.setOnClickListener(this);

		appBtn = (Button) findViewById(R.id.id_btn_gen_num);

		appBtn.setOnClickListener(this);

	}

	

	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.id_btn_gen_async:

			TextView averageView = (TextView) findViewById(R.id.id_avg_number);

			averageView.setText("");

			resetrogress();

			if (selectedCompl > 0) {
				DoWorkAsync aTask = new DoWorkAsync();
				aTask.execute(selectedCompl);
			}

			break;

		case R.id.id_btn_gen_num:

			resetrogress();

			averageView = (TextView) findViewById(R.id.id_avg_number);

			averageView.setText("");

			ExecutorService excServicePool;

			excServicePool = Executors.newFixedThreadPool(POOL_SIZE);

			excServicePool.execute(new DoRunnableWork(selectedCompl));

			break;

		case R.id.id_btn_photo:
			
			//new PhotoAsync().execute(" http://dev.theappsdr.com/apis/photos.php");
			Intent photoIntent = new Intent(this, SlideShowActivity.class);

			photoIntent.putExtra(KEY_URL, "http://dev.theappsdr.com/apis/photos.php");
			
			startActivity(photoIntent);

		}

	}

	public void resetrogress() {

		progDialog = new ProgressDialog(MainActivity.this);
		progDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		progDialog.setMessage("Retrieving The Number");

		progDialog.setCancelable(false);

		progDialog.setProgress(0);
		progDialog.setMax(selectedCompl);

	}
	
	class DoWorkAsync extends AsyncTask<Integer, Integer, Double> {

		@Override
		protected void onPostExecute(Double result) {
			super.onPostExecute(result);

			TextView averageView = (TextView) findViewById(R.id.id_avg_number);

			averageView.setText(result.toString());

			progDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progDialog.show();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			progDialog.setProgress(values[0]);
		}

		@Override
		protected Double doInBackground(Integer... params) {

			int count = params[0];

			Double finalAvg = 0.0;
			for (int i = 0; i < count; i++) {

				finalAvg = finalAvg + HeavyWork.getNumber();
				publishProgress(i + 1);
			}

			return finalAvg / count;
		}

	}
	
	
	class DoRunnableWork implements Runnable {



		private static final int OPER_START = 0;
		private static final int OPER_PROGRESS = 1;
		private static final int OPER_FINISH = 3;

		private static final String RESULT_AVG = "result";

		private int loopCount; 

		public DoRunnableWork(int loopCnt) {

			loopCount = loopCnt;
		}

		@Override
		public void run() {

			Double finalAvg = 0.0;
			if (loopCount > 0) {

				sendMsg(OPER_START,0,0);

				for (int i = 0; i < loopCount; i++) {

					
					finalAvg = finalAvg + HeavyWork.getNumber();
					sendMsg(OPER_PROGRESS, i+1, 0);
				}

				sendMsg(OPER_FINISH, 0, finalAvg/ loopCount);


			}

		}

		private void sendMsg(int msgStatus, int msgObj, double resultVal) {

			Message message = new Message();
			message.what = msgStatus;

			if(msgObj != 0){
			message.obj = msgObj;
			}

			if(resultVal != 0){

				Bundle bb = new Bundle();
				bb.putDouble(RESULT_AVG, resultVal);
				message.getData().putAll(bb);
			}


			handler.sendMessage(message);
		}


	}
	
	
	
}
