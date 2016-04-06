package com.example.inclass06;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Assignment No: InClass06 File Name: DetailsActivity.java
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan 
 * 3. Madhavi bhat
 */
public class DetailsActivity extends Activity {

	ImageView iv;
	TextView title, owner,by;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		loadAllViews();
		Photo photo = (Photo) getIntent().getExtras().getSerializable(
				GalleryActivity.KEY_PHOTO_OBJECT);
		Picasso.with(this).load(photo.getUrl())
				.error(R.drawable.photo_not_found).into(iv);
		title.setText(photo.getTitle());
		owner.setText(photo.getOwnerName());
		owner.setTypeface(null, Typeface.BOLD);
		title.setTypeface(null,Typeface.BOLD);
		by.setTypeface(null,Typeface.BOLD);
		

	}

	public void loadAllViews() {
		iv = (ImageView) findViewById(R.id.imageView1);
		title = (TextView) findViewById(R.id.textView1);
		owner = (TextView) findViewById(R.id.textView3);
		by = (TextView) findViewById(R.id.textView2);
	}

/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getBaseContext(), GalleryActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent.putExtra(MainActivity.KEY_SEARCH_TERM, getIntent()
					.getExtras().getString(MainActivity.KEY_SEARCH_TERM));
			startActivity(intent);
			return false;
		}
		return true;
	}*/

}
