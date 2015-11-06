package com.buffapps.hassan.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hassan on 11/5/15.
 */
public class ScoreCardAdapter extends BaseAdapter{

    private Context mContext;
    private int[] mScores;

    public ScoreCardAdapter(Context context, int[] scores)
    {
        mScores = scores;
        mContext = context;
    }

    @Override
    public int getCount()
    {
        return mScores.length;
    }

    @Override
    public Object getItem(int position)
    {
        return mScores[position];
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
       final ViewHolder viewHolder;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.scoreTextView = (TextView) convertView.findViewById(R.id.scoreTextView);
            viewHolder.addButton = (Button) convertView.findViewById(R.id.addButton);
            viewHolder.subButton = (Button) convertView.findViewById(R.id.subButton);

            convertView.setTag(viewHolder);

        }else
           viewHolder = (ViewHolder) convertView.getTag();

        final int score = mScores[position];
        viewHolder.scoreTextView.setText("Score: " + score);
        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScores[position]++;
                viewHolder.scoreTextView.setText("Score: " + mScores[position]);
            }
        });

        viewHolder.subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScores[position]--;
                if(mScores[position] < 0)
                    mScores[position] = 0;
                viewHolder.scoreTextView.setText("Score: " + mScores[position]);

            }
        });
        return convertView;
    }


    public static class ViewHolder
    {
        TextView scoreTextView;
        Button   addButton;
        Button subButton;


    }


}
