package com.example.midtermexam;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * @author Manju Raghavendra Bellamkonda
 *
 */
public class MainActivity extends Activity {

	List<Topics> topics;
	ListView listView;
	ArrayAdapter<Topics> adapter;
	static final String KEY_NEWS_URL="news_url";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadAllViews();
		populateList();
		adapter = new ArrayAdapter<Topics>(this,android.R.layout.simple_list_item_1, topics);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getBaseContext(), NewsActivity.class);
				intent.putExtra("KEY_NEWS_URL", topics.get(arg2).getUrl());
				startActivity(intent);

			}
		});
	}

	private void loadAllViews() {
		listView = (ListView) findViewById(R.id.listView1);
	}

	private void populateList() {
		topics = new ArrayList<Topics>();
		Topics topic=new Topics("Top Stories", "http://feeds.bbci.co.uk/news/rss.xml");
		topics.add(topic);
		topic=new Topics("World", "http://feeds.bbci.co.uk/news/world/rss.xml");
		topics.add(topic);
		topic=new Topics("UK", "http://feeds.bbci.co.uk/news/uk/rss.xml");
		topics.add(topic);
		topic=new Topics("Business", "http://feeds.bbci.co.uk/news/business/rss.xml");
		topics.add(topic);
		topic=new Topics("Politics", "http://feeds.bbci.co.uk/news/politics/rss.xml");
		topics.add(topic);
		topic=new Topics("Health", "http://feeds.bbci.co.uk/news/health/rss.xml");
		topics.add(topic);
		topic=new Topics("Education & Family", "http://feeds.bbci.co.uk/news/education/rss.xml");
		topics.add(topic);
		topic=new Topics("Science & Environment", "http://feeds.bbci.co.uk/news/science_and_environment/rss.xml");
		topics.add(topic);
		topic=new Topics("Technology", "http://feeds.bbci.co.uk/news/technology/rss.xml");
		topics.add(topic);
		topic=new Topics("Entertainment & Arts", "http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml");
		topics.add(topic);
	}
}
