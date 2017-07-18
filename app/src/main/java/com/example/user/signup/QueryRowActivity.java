package com.example.user.signup;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class QueryRowActivity extends AsyncTask<String, Void, String> {

    private Context context;

    public QueryRowActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        String phoneNumber = arg0[0];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {


            data = "?phone=" + URLEncoder.encode(phoneNumber, "UTF-8");

            link = "http://140.130.33.152/QueryMessage.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {

        String jsonStr = result;
        ArrayList<String> stringArray = new ArrayList<String>();
        Log.d("result=",result);

        if (jsonStr != null) {
            try {
                JSONArray new_array = new JSONArray(result);

                for(int i = 0, count = new_array.length(); i< count; i++)
                {
                    try {
                        JSONObject jsonObject = new_array.getJSONObject(i);
                        stringArray.add(jsonObject.getString("msg").toString());
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
}