package com.example.inclass03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Assignment No: InClass Assignment03  
 * File Name: MainActivity.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class MainActivity extends Activity {

	static final String NAME_KEY = "NAME";
	static final String EMAIL_KEY = "EMAIL";
	static final String PL_KEY = "PL";
	static final String ACCOUNT_KEY = "ACCOUNT_STATE";
	static final String MOOD_KEY = "MOOD";
	static final String STUDENT_KEY = "student";
	EditText nameET;
	EditText emailET;
	RadioGroup rg;
	Switch switch1;
	SeekBar sb;
	Boolean status=true;
	Student student;
	static final String regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nameET = (EditText) findViewById(R.id.editText1);
		emailET = (EditText) findViewById(R.id.editText2);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		switch1 = (Switch) findViewById(R.id.switch1);
		sb = (SeekBar) findViewById(R.id.seekBar1);
		

		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						student=new Student();
						status=true;
						Intent intent = new Intent(getBaseContext(),
								DisplayActivity.class);

						if (returnNul(nameET.getText().toString())) {
							student.setName(nameET.getText()
									.toString());
							intent.putExtra(NAME_KEY, nameET.getText()
									.toString());
						}
						else
						{
							status=false;
							nameET.setError("Enter Name");
						}
						if (returnNul(emailET.getText().toString())) {
							if(!emailET.getText().toString().matches(regexp))
							{
								status=false;
								
								emailET.setError("Enter Valid Email Address");
							}
							student.setEmailAddress(emailET.getText()
									.toString());
							intent.putExtra(EMAIL_KEY, emailET.getText()
									.toString());
						}
						else
						{
							status=false;
							emailET.setError("Enter Email");
						}
						Integer checkedID = rg.getCheckedRadioButtonId();
						if (checkedID == R.id.radio0) {
							student.setProgrammingLanguage("Java");
							intent.putExtra(PL_KEY, "Java");
						} else if (checkedID == R.id.radio1) {
							student.setProgrammingLanguage("C");
							intent.putExtra(PL_KEY, "C");
						} else if (checkedID == R.id.radio2) {
							student.setProgrammingLanguage("C#");
							intent.putExtra(PL_KEY, "C#");
						}

						intent.putExtra(ACCOUNT_KEY, switch1.isChecked());
						student.setAccountState(switch1.isChecked());
						intent.putExtra(MOOD_KEY, sb.getProgress());
						student.setMood(sb.getProgress());
						intent.putExtra(STUDENT_KEY, student);
						if(status)
						startActivity(intent);

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
