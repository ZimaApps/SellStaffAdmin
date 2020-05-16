package accounts.acbtz.jagroadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.internal.http.multipart.MultipartEntity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static accounts.acbtz.jagroadmin.Statics.DESCRIPTIONOFPRODUCT;
import static accounts.acbtz.jagroadmin.Statics.PRODUCTIMAGE;


public class order extends AppCompatActivity {
    String jina;
    String simu;
    String kiasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView image = findViewById(R.id.prodimage);
        TextView descriptionx = findViewById(R.id.descriptionx);
        descriptionx.setText(DESCRIPTIONOFPRODUCT);

        Glide
                .with(this)
                .load(PRODUCTIMAGE)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(image);
    }



    public void prepareUpload(View v){
        TextView jinax = findViewById(R.id.jina);
        jina = jinax.getText().toString();
        TextView simux = findViewById(R.id.simu);
        simu = simux.getText().toString();
        TextView kiasix = findViewById(R.id.kiasi);
        kiasi = kiasix.getText().toString();

        if(jina.equalsIgnoreCase("") || simu.equalsIgnoreCase("")){
            Toast.makeText(this, "Tafadhali, jaza jina na namba yako ya simu", Toast.LENGTH_LONG).show();
        }else{
            new doFileUpload().execute();
        }



    }



    public class doFileUpload extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(order.this);
            pd.setMessage("loading");
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... String) {

                String URL = "http://greenhut.jagro.co.tz/save_order.php";

            Log.e("RESULT FROM SERVER", "TUPO BACKGROUND");


            try{


                String link=URL;
                String data  = URLEncoder.encode("jina", "UTF-8") + "=" + URLEncoder.encode(jina, "UTF-8");
                data += "&" + URLEncoder.encode("simu", "UTF-8") + "=" + URLEncoder.encode(simu, "UTF-8");
                data += "&" + URLEncoder.encode("kiasi", "UTF-8") + "=" + URLEncoder.encode(kiasi, "UTF-8");
                data += "&" + URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(DESCRIPTIONOFPRODUCT, "UTF-8");

                Log.e("RESULT FROM SERVER", "DATA "+data);

                java.net.URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                //return sb.toString();
                Log.e("RESULT FROM SERVER", "Response from url: " + sb.toString());
            } catch(Exception e){
                //return new String("Exception: " + e.getMessage());
                Log.e("RESULT FROM SERVER", "Exception: " + e.getMessage());
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pd != null)
            {
                pd.dismiss();
            }

            Log.e("RESULT FROM SERVER", "MWISHO ");
            Intent myIntent = new Intent(order.this, MainActivity.class);

            order.this.startActivity(myIntent);



        }
    }



}
