package com.markgubatan.worldvote;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Mark Gubatan on 11/25/2014.
 */
public class ProfileFragment extends Fragment {

    View rootView;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        SearchTask searchTask = new SearchTask(onSearchCompleteListener, "miyoo2@illinois.edu");
        searchTask.execute();
        /*System.out.println("Hi my name is yolo");
        System.out.println("ProfileFragment: " + searchTask.personName);

        Bitmap image = searchTask.personImage;
        ImageView profile = (ImageView)rootView.findViewById(R.id.profile_activity_picture);

        if(image != null)
            profile.setImageBitmap(image);

        TextView profileName = (TextView)rootView.findViewById(R.id.profile_activity_name);

        profileName.setText(searchTask.personName);
        */

        return rootView;
    }
    OnSearchCompleteListener onSearchCompleteListener = new OnSearchCompleteListener() {
        @Override
        public void onCompleteListener(ArrayList<String> searchPerson) {
            TextView profileName = (TextView)rootView.findViewById(R.id.profile_activity_name);
            profileName.setText("Name: " + searchPerson.get(0));
            TextView profileEmail = (TextView)rootView.findViewById(R.id.profile_activity_email);
            profileEmail.setText("E-mail: " + searchPerson.get(1));
            TextView profileScore = (TextView)rootView.findViewById(R.id.profile_activity_score);
            profileScore.setText("Score: " + searchPerson.get(2));

            ImageView profileImage = (ImageView)rootView.findViewById(R.id.profile_activity_picture);
            String encodedImage = searchPerson.get(4);


            byte[] b = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap image = BitmapFactory.decodeByteArray(b, 0, b.length);
            profileImage.setImageBitmap(image);

        }
    };




}
