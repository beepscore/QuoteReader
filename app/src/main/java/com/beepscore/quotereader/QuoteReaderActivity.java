package com.beepscore.quotereader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class QuoteReaderActivity extends Activity {

    public class QuoteAdapter extends BaseAdapter {

        private Context mContext;
        // instantiates layout xml into View object
        private LayoutInflater mInflater;
        private DataSource mDataSource;

        public QuoteAdapter(Context c) {
            mContext = c;
            mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mDataSource = new DataSource();
        }

        /** return number of items in data set */
        @Override
        public int getCount() {
            return mDataSource.getDataSourceLength();
        }

        /** return item at position */
        @Override
        public Object getItem(int position) {
            return position;
        }

        /** return row id at position */
        @Override
        public long getItemId(int position) {
            return position;
        }

        // called for every item in the list
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView thumbnail;
            TextView quote;

            // if we don't have a convertView to reuse, lazily instantiate one.
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.list_item_layout, parent, false);
            }

            thumbnail = (ImageView) convertView.findViewById(R.id.list_thumb);
            thumbnail.setImageResource(mDataSource.getmPhotoPool().get(position));

            quote = (TextView) convertView.findViewById(R.id.list_text);
            quote.setText(mDataSource.getmQuotePool().get(position));

            return convertView;
        }
    }

    private ListView mListView;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_reader);
        mListView = (ListView) findViewById(R.id.quotes_list);
        mListView.setAdapter(new QuoteAdapter(this));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {
                Intent i = new Intent(QuoteReaderActivity.this, QuoteDetail.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quote_reader, menu);
        return true;
    }

}
