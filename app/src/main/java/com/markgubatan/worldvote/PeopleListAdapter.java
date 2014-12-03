package com.markgubatan.worldvote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mark Gubatan on 11/24/2014.
 */
public class PeopleListAdapter extends BaseAdapter {
    Context context;
    protected List<Person> listPeople;
    LayoutInflater inflater;
    public PeopleListAdapter(Context context, List<Person> listPeople) {
        this.context = context;
        this.listPeople = listPeople;
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return listPeople.size();
    }
    public Person getItem(int position) {
        return listPeople.get(position);
    }
    public long getItemId(int position) {
        return listPeople.get(position).getDrawableId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_profile, parent, false);

            holder.txtName = (TextView) convertView.findViewById(R.id.list_item_name);
            holder.txtScore = (TextView) convertView.findViewById(R.id.list_item_score);
            holder.imgProfile = (ImageView) convertView.findViewById(R.id.list_item_picture);
            holder.butUpvote = (Button) convertView.findViewById(R.id.list_item_upvote);
            holder.butDownvote = (Button) convertView.findViewById(R.id.list_item_downvote);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        final ViewHolder holder2 = holder;
        final Person person = listPeople.get(position);

        holder.txtName.setText(person.getName());
        holder.txtScore.setText("Score: " + String.valueOf(person.getScore()));
        holder.imgProfile.setImageResource(person.getDrawableId());

        //TODO: Account for last time vote has occurred
        Button upvote = holder.butUpvote;
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addScore(person);
                holder2.txtScore.setText("Score: " + String.valueOf(person.getScore()));
            }
        });

        Button downvote = holder.butDownvote;
        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subScore(person);
                holder2.txtScore.setText("Score: " + String.valueOf(person.getScore()));
            }
        });

        return convertView;
    }

    public void addScore(Person person) {
        int score = person.getScore();
        score++;
        person.setScore(score);
    }
    public void subScore(Person person) {
        int score = person.getScore();
        score--;
        person.setScore(score);
    }

    private class ViewHolder {
        TextView txtName;
        TextView txtScore;
        ImageView imgProfile;
        Button butUpvote;
        Button butDownvote;

    }

}
