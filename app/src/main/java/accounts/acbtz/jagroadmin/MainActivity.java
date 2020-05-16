package accounts.acbtz.jagroadmin;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ListView listView;

    CustomAdapter adapter;

    public static ArrayList<DataModel> dataModels;
    public static ArrayList<DataModel> dataModels2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Statics.theactivity = this;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if(haveNetworkConnection()){
            new GetFruits().execute();
            new GetVeges().execute();
        }else{
            Toast.makeText(this,"HAKUNA INTERNET!!!!",Toast.LENGTH_LONG).show();
        }

    }




    private class GetFruits extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://greenhut.jagro.co.tz/get_orders.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("orders");

                    dataModels = new ArrayList<>();
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        final String id = c.getString("id");
                        final String fruitName = c.getString("name");
                        final String fruitDescription = c.getString("fruitDescription");
                        final String pricePerOne = c.getString("phone");
                        final String image = c.getString("quantity");


                        dataModels.add(new DataModel(id, fruitName,fruitDescription,pricePerOne,image,"none"));

                    }
                    //dataModelsx = dataModels;
                } catch (final JSONException e) {
                    Log.e("RESULT FROM SERVER", "Json parsing error: " + e.getMessage());


                }

            } else {
                Log.e("RESULT FROM SERVER", "Couldn't get json from server.");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


        }
    }






    private class GetVeges extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://greenhut.jagro.co.tz/get_veges.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("vegetables");

                    dataModels2 = new ArrayList<>();
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        final String id = c.getString("id");
                        final String fruitName = c.getString("name");
                        final String fruitDescription = c.getString("description");
                        final String pricePerOne = c.getString("pricePerOne");
                        final String image = c.getString("image");
                        final String type = c.getString("type");


                        dataModels2.add(new DataModel(id, fruitName,fruitDescription,pricePerOne,image,type));

                    }

                } catch (final JSONException e) {
                    Log.e("RESULT FROM SERVER", "Json parsing error: " + e.getMessage());


                }

            } else {
                Log.e("RESULT FROM SERVER", "Couldn't get json from server.");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


        }
    }




    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }





}
