/**
 * Homework No: Homework 01 
 * File Name: MainActivity.java 
 * 
 * Team Members: 
 * 1. Prem Kumar Murugesan
 * 2. Manju Raghavendra Bellamkonda
 * 3. Madhavi Bhat
 */

package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_main);

		//Initialization
		SeekBar sb = (SeekBar) findViewById(R.id.sbCustom);
		final TextView tvCustomVal = (TextView) findViewById(R.id.tvCustomVal);
		final TextView tvTipVal = (TextView) findViewById(R.id.tvTipVal);
		final TextView tvTotalVal = (TextView) findViewById(R.id.tvTotalVal);
		final EditText etBillAmt = (EditText) findViewById(R.id.editText1);
		final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		Button btnExit = (Button) findViewById(R.id.btnExit);
		
		sb.setMax(50);
		sb.setProgress(25);
		tvCustomVal.setText(String.valueOf(sb.getProgress()));
		tvTotalVal.setText("0.00");
		tvTipVal.setText("0.00");

		//Edit Text Listener
		etBillAmt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().isEmpty()) {
					etBillAmt.setError("Enter Bill Total");
					tvTotalVal.setText("0.00");
					tvTipVal.setText("0.00");
				} else {
					double billAmt = Double.parseDouble(s.toString());
					int tipPerc = 0;
					if (rg.getCheckedRadioButtonId() == R.id.radio0) {
						tipPerc = 10;
					} else if (rg.getCheckedRadioButtonId() == R.id.radio1) {
						tipPerc = 15;
					} else if (rg.getCheckedRadioButtonId() == R.id.radio2) {
						tipPerc = 18;
					} else if (rg.getCheckedRadioButtonId() == R.id.radio3) {
						tipPerc = Integer.parseInt(tvCustomVal.getText()
								.toString());
					}

					tvTipVal.setText(String.valueOf((billAmt / 100.00)
							* tipPerc));
					tvTotalVal.setText(String
							.valueOf((billAmt + (billAmt / 100.00) * tipPerc)));
				}

			}
		});

		//Seek Bar Listener
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				tvCustomVal.setText(String.valueOf(progress));

				if (etBillAmt.getText().toString().isEmpty()) {
					etBillAmt.setError("Enter Bill Total");
					tvTotalVal.setText("0.00");
					tvTipVal.setText("0.00");
				} else if(rg.getCheckedRadioButtonId() == R.id.radio3){
					double billAmt = Double.parseDouble(etBillAmt.getText()
							.toString());
					int tipPerc = progress;
					tvTipVal.setText(String.valueOf((billAmt / 100.00)
							* tipPerc));
					tvTotalVal.setText(String
							.valueOf((billAmt + (billAmt / 100.00) * tipPerc)));
				}

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}
		});
		
		//Radio Group Listener
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				
				if (etBillAmt.getText().toString().isEmpty()) {
					tvTotalVal.setText("0.00");
					tvTipVal.setText("0.00");
					etBillAmt.setError("Enter Bill Total");
					
				} else {
					
					double billAmt = Double.parseDouble(etBillAmt.getText().toString());
					int tipPerc = 0;
					if (checkedId == R.id.radio0) {
						tipPerc = 10;
					} else if (checkedId == R.id.radio1) {
						tipPerc = 15;
					} else if (checkedId == R.id.radio2) {
						tipPerc = 18;
					} else if (checkedId == R.id.radio3) {
						tipPerc = Integer.parseInt(tvCustomVal.getText()
								.toString());
					}
					tvTipVal.setText(String.valueOf((billAmt / 100.00)
							* tipPerc));
					tvTotalVal.setText(String
							.valueOf((billAmt + (billAmt / 100.00) * tipPerc)));
				}
				
			}
		});
		
		
		//Button Listener
		btnExit.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
