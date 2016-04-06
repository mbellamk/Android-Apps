package com.example.midtermexam;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsActivity extends Activity {

	ArrayList<NewsItem> newsItems;
	ListView listView;
	ArrayAdapter<NewsItem> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		loadAllViews();
		new loadNewsAsyncTask().execute(getIntent().getExtras().getString(
				MainActivity.KEY_NEWS_URL));

	}

	private void loadAllViews() {
		listView = (ListView) findViewById(R.id.listView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_sort_title_a2z) {
			Collections.sort(newsItems, new Comparator<NewsItem>() {

				@Override
				public int compare(NewsItem item1, NewsItem item2) {
					return item1.getTitle().compareTo(item2.getTitle());
				}

			});
			return true;
		} else if (id == R.id.action_sort_title_z2a) {
			Collections.sort(newsItems, new Comparator<NewsItem>() {

				@Override
				public int compare(NewsItem item1, NewsItem item2) {
					return item2.getTitle().compareTo(item1.getTitle());
				}

			});
			return true;
		} else if (id == R.id.action_sort_pubdate_ascending) {
			Collections.sort(newsItems, new Comparator<NewsItem>() {

				@Override
				public int compare(NewsItem lhs, NewsItem rhs) {
					// TODO Auto-generated method stub
					return 0;
				}

			});
			return true;
		} else if (id == R.id.action_sort_pubdate_descending) {
			Collections.sort(newsItems, new Comparator<NewsItem>() {

				@Override
				public int compare(NewsItem lhs, NewsItem rhs) {
					// TODO Auto-generated method stub
					return 0;
				}

			});
			return true;
		}
		adapter = new ArrayAdapter<>(NewsActivity.this,
				android.R.layout.simple_list_item_1, newsItems);
		listView.setAdapter(adapter);
		return super.onOptionsItemSelected(item);
	}

	public class loadNewsAsyncTask extends
			AsyncTask<String, Void, ArrayList<NewsItem>> {

		@Override
		protected ArrayList<NewsItem> doInBackground(String... params) {
			URL url;
			try {
				url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.connect();
				return MyXmlPullParser.MyBBCXmlPullParser.parseFlickrData(con
						.getInputStream());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<NewsItem> result) {
			super.onPostExecute(result);
			newsItems = result;
			adapter = new ArrayAdapter<>(NewsActivity.this,
					android.R.layout.simple_list_item_1, newsItems);
			adapter.setNotifyOnChange(true);
			listView.setAdapter(adapter);

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}
	}
}
