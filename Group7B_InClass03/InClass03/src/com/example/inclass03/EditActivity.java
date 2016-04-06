package com.example.inclass03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Assignment No: InClass Assignment03  
 * File Name: EditActivity.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class EditActivity extends Activity {

	LinearLayout LL;
	String editType;
	EditText et1;
	SeekBar sb;
	RadioGroup rg;
	RadioButton rb1, rb2, rb3;
	Switch switch1;
	Boolean status = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		LL = (LinearLayout) findViewById(R.id.LinearLayout1);
		editType = getIntent().getExtras().getString(
				DisplayActivity.EDIT_TYPE_KEY);
		if (editType.equals(MainActivity.NAME_KEY)) {
			et1 = new EditText(this);
			LL.addView(et1);
			et1.setText(getIntent().getExtras()
					.getString(MainActivity.NAME_KEY));
		} else if (editType.equals(MainActivity.EMAIL_KEY)) {
			et1 = new EditText(this);
			LL.addView(et1);
			et1.setText(getIntent().getExtras().getString(
					MainActivity.EMAIL_KEY));
		} else if (editType.equals(MainActivity.PL_KEY)) {
			TextView tv = new TextView(this);
			tv.setText("Favorite Programming Language:");
			tv.setPadding(R.dimen.dp, R.dimen.dp, R.dimen.dp, R.dimen.dp);
			LL.addView(tv);
			rg = new RadioGroup(this);
			rb1 = new RadioButton(this);
			rb1.setId(R.id.radio0);
			rb1.setText(R.string.java);
			rb2 = new RadioButton(this);
			rb2.setId(R.id.radio1);
			rb2.setText(R.string.c);
			rb3 = new RadioButton(this);
			rb3.setId(R.id.radio2);
			rb3.setText(R.string.chash);

			String selectedPL = getIntent().getExtras().getString(
					MainActivity.PL_KEY);
			
			rg.addView(rb1);
			rg.addView(rb2);
			rg.addView(rb3);
			LL.addView(rg);
			if (rg.getCheckedRadioButtonId() == -1) {
				if (selectedPL.equals("Java")) {
					rg.check(rb1.getId());
				}
				 else if (selectedPL.equals("C")) {
						rg.check(rb2.getId());
					}
				else if (selectedPL.equals("C#")) {
						rg.check(rb3.getId());
					}
				
			
				}
		} else if (editType.equals(MainActivity.ACCOUNT_KEY)) {
			switch1 = new Switch(this);
			switch1.setText(R.string.displayaccount);
			Log.d("InClass03",
					Boolean.toString(getIntent().getExtras().getBoolean(
							MainActivity.ACCOUNT_KEY)));
			switch1.setChecked(getIntent().getExtras().getBoolean(
					MainActivity.ACCOUNT_KEY));
			LL.addView(switch1);
		} else if (editType.equals(MainActivity.MOOD_KEY)) {
			sb = new SeekBar(this);
			sb.setProgress(getIntent().getExtras()
					.getInt(MainActivity.MOOD_KEY));
			LL.addView(sb);
		}

		Button save = new Button(this);
		save.setText(R.string.save);
		LL.addView(save);
		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				status = true;
				Intent intent = new Intent();
				intent.putExtra(DisplayActivity.EDIT_TYPE_KEY, editType);
				if (editType.equals(MainActivity.NAME_KEY)) {
					if (!returnNul(et1.getText().toString())) {
						status = false;
						et1.setError("Enter Name");
					}

					intent.putExtra(editType, et1.getText().toString());
				} else if (editType.equals(MainActivity.EMAIL_KEY)) {
					if (!returnNul(et1.getText().toString())) {
						status = false;
						et1.setError("Enter Email");
					}
					if (!et1.getText().toString().matches(MainActivity.regexp)) {
						status = false;
						et1.setError("Enter Valid Email Address");
					}

					intent.putExtra(editType, et1.getText().toString());
				} else if (editType.equals(MainActivity.PL_KEY)) {
					int checkedID = rg.getCheckedRadioButtonId();
					if (checkedID == rb1.getId()) {
						intent.putExtra(editType, "Java");
					} else if (checkedID == rb2.getId()) {
						intent.putExtra(editType, "C");
					} else if (checkedID == rb3.getId()) {
						intent.putExtra(editType, "C#");
					}
				} else if (editType.equals(MainActivity.ACCOUNT_KEY)) {
					intent.putExtra(editType, switch1.isChecked());
				} else if (editType.equals(MainActivity.MOOD_KEY)) {
					intent.putExtra(editType, sb.getProgress());
				}
				if (status) {
					setResult(RESULT_OK, intent);
					finish();
				}

			}
		});
	}

	boolean returnNul(String msg) {
		if (msg.trim().equals(""))
			return false;
		else
			return true;

	}
}
