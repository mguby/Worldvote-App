package com.markgubatan.worldvote;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private PeopleListAdapter peopleListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeListFragment())
                    .commit();
        }
        ArrayList<Person> arrayPeople = new ArrayList<Person>();
        Person p1 = new Person("Stupid Scrub", 50, R.drawable.default_profile_picture);
        Person p2 = new Person("Stupid Faggot", 50, R.drawable.default_profile_picture);
        Person p3 = new Person("Faggot Scrub", 50, R.drawable.default_profile_picture);
        Person p4 = new Person("Faggot Faggot", 50, R.drawable.default_profile_picture);
        Person p5 = new Person("Henry Lin", -500, R.drawable.default_profile_picture);
        Person p6 = new Person("BOP EMPEROR DLOW", 5000, R.drawable.default_profile_picture);
        Person p7 = new Person("Stupid Scrub", 50, R.drawable.default_profile_picture);
        Person p8 = new Person("Stupid Scrub", 50, R.drawable.default_profile_picture);
        Person p9 = new Person("Stupid Scrub", 50, R.drawable.default_profile_picture);
        Person p10 = new Person("Stupid Scrub", 50, R.drawable.default_profile_picture);


        arrayPeople.add(p1);
        arrayPeople.add(p2);
        arrayPeople.add(p3);
        arrayPeople.add(p4);
        arrayPeople.add(p5);
        arrayPeople.add(p6);
        arrayPeople.add(p7);
        arrayPeople.add(p8);
        arrayPeople.add(p9);
        arrayPeople.add(p10);

        peopleListAdapter = new PeopleListAdapter(this, arrayPeople);
    }
    public PeopleListAdapter getPeopleListAdapter() {
        return peopleListAdapter;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_log_in) {
            startActivity(new Intent(this, LogInActivity.class));
            //overridePendingTransition(0,0);
            return true;
        }
        else if (id == R.id.action_home) {
            return true;
        }
        else if (id == R.id.action_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            //overridePendingTransition(0,0);
            return true;
        }
        else if (id == R.id.action_search) {
            startActivity(new Intent(this, SearchActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

}
