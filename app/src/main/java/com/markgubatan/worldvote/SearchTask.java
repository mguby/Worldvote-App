package com.markgubatan.worldvote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Mark Gubatan on 12/2/2014.
 */
public class SearchTask extends AsyncTask<Void, Void, ArrayList<String>> {

    private String personName;
    private int personScore;
    private String personEmail;
    private String encodedImage;
    private String personPassword;

    private OnSearchCompleteListener searchComplete;
    public ArrayList<String> searchPerson = new ArrayList<String>();

    public SearchTask(OnSearchCompleteListener onSearchCompleteListener, String email) {
        super();
        personEmail = email;
        searchComplete = onSearchCompleteListener;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        // Client used to grab data from a provided URL
        DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

        // Provide the URL for the post request
        //HttpPost httpPost = new HttpPost("http://worldvote.herokuapp.com/markSearchName?query=miyoo2@illinois.edu");
        personEmail = personEmail.replace(" ", "+");
        String query = "http://worldvote.herokuapp.com/markSearchName?query=" + personEmail;
        HttpGet httpGet = new HttpGet(query);
        // Define that the data expected is in JSON format
        httpGet.setHeader("Content-type", "application/json");

        // Allows you to input a stream of bytes from the URL
        InputStream inputStream = null;

        try {

            // The client calls for the post request to execute and sends the results back
            HttpResponse response = httpClient.execute(httpGet);

            // Holds the message sent by the response
            HttpEntity entity = response.getEntity();

            // Get the content sent
            inputStream = entity.getContent();

            // A BufferedReader is used because it is efficient
            // The InputStreamReader converts the bytes into characters
            // My JSON data is UTF-8 so I read that encoding
            // 8 defines the input buffer size
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 10000);

            // Storing each line of data in a StringBuilder
            StringBuilder sb = new StringBuilder();

            String line = null;

            // readLine reads all characters up to a \n and then stores them
            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");

            }

            // Save the results in a String
            String jsonString = sb.toString();
            System.out.println(jsonString);

            // Create a JSONObject by passing the JSON data
            JSONObject jObject = new JSONObject(jsonString);
            if(jObject.getString("id").equals("-1")) {
                searchPerson.add("Not found");
                searchPerson.add("Not found");
                searchPerson.add("0");
                searchPerson.add("nopassword");
                searchPerson.add(null);
                return searchPerson;
            }
            System.out.println(jObject.getString("name"));
            System.out.println(jObject.getString("id"));
            System.out.println(jObject.getString("password"));
            System.out.println(jObject.getString("email"));

            personName = jObject.getString("name");
            personEmail = jObject.getString("email");
            personScore = jObject.getInt("score");
            personPassword = jObject.getString("password");
            System.out.println("SearchTask: " + personName);
            String encodedImage = jObject.getString("image");
            encodedImage = encodedImage.substring(22);

            searchPerson.add(personName);
            searchPerson.add(personEmail);
            searchPerson.add(Integer.toString(personScore));
            searchPerson.add(personPassword);
            searchPerson.add(encodedImage);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return searchPerson;

    }


    @Override
    protected void onPostExecute(ArrayList<String> searchPerson) {
        searchComplete.onCompleteListener(searchPerson);

    }


}
