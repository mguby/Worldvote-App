package com.markgubatan.worldvote;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mark Gubatan on 11/24/2014.
 */
public class HomeListFragment extends Fragment {
    /**
     * A placeholder fragment containing a simple view.
     */


    public HomeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



        ListView mListView = (ListView) rootView.findViewById(R.id.worldvote_list);
        PeopleListAdapter adapter = ((MainActivity)getActivity()).getPeopleListAdapter();
        mListView.setAdapter(adapter);



        return rootView;
    }

    OnSearchCompleteListener onSearchCompleteListener = new OnSearchCompleteListener() {
        @Override
        public void onCompleteListener(ArrayList<String> searchPerson) {

        }
    }



}



