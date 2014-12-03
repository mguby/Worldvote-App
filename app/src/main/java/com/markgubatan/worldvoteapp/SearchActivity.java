package com.markgubatan.worldvote;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {
    private EditText personEmail;
    private Button searchButton;
    SearchActivity rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        personEmail = (EditText)this.findViewById(R.id.editText);
        searchButton = (Button)this.findViewById(R.id.button);
        rootView = this;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getEmail();
                if(email != null) {
                    SearchTask searchTask = new SearchTask(onSearchCompleteListener, email);
                    searchTask.execute();
                }
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

    }

    private String getEmail() {
        return personEmail.getText().toString();
    }

    OnSearchCompleteListener onSearchCompleteListener = new OnSearchCompleteListener() {
        @Override
        public void onCompleteListener(ArrayList<String> searchPerson) {
            ImageView profileImage = (ImageView)rootView.findViewById(R.id.search_activity_picture);
            String encodedImage = searchPerson.get(4);
            if(encodedImage != null) {
                byte[] b = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(b, 0, b.length);
                profileImage.setImageBitmap(image);

            }
            else {
                profileImage.setImageResource(R.drawable.person_not_found);
            }
            TextView profileName = (TextView)rootView.findViewById(R.id.search_activity_name);
            profileName.setText("Name: " + searchPerson.get(0));
            TextView profileEmail = (TextView)rootView.findViewById(R.id.search_activity_email);
            profileEmail.setText("E-mail: " + searchPerson.get(1));
            TextView profileScore = (TextView)rootView.findViewById(R.id.search_activity_score);
            profileScore.setText("Score: " + searchPerson.get(2));

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
