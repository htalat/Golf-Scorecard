package com.buffapps.hassan.golfscorecard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_FILE = "com.buffapps.golfscoreboard.pref";
    private static final String KEY_SCORES = "KEY_SCORES";
    int[] mScores;
    ListView mListView;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    ScoreCardAdapter mScoreCardAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        mEditor  = mSharedPreferences.edit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mScores = new int[18];
        for(int i=0;i<mScores.length;i++)
        {
            mScores[i] = mSharedPreferences.getInt(KEY_SCORES + i,0);
        }

        mListView = (ListView) findViewById(R.id.listView);
        mScoreCardAdapter = new ScoreCardAdapter(this,mScores);
        mListView.setAdapter(mScoreCardAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_scores)
        {

            mEditor.clear();
            mEditor.apply();
            for(int i=0;i<mScores.length;i++)
                mScores[i] = 0;

            mScoreCardAdapter.notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(int i=0; i< mScores.length;i++)
        {
            mEditor.putInt(KEY_SCORES + i,mScores[i]);
        }
        mEditor.apply();
    }
}
