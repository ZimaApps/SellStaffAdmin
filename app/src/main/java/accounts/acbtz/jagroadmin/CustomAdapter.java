package accounts.acbtz.jagroadmin;


import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static accounts.acbtz.jagroadmin.Statics.DESCRIPTIONOFPRODUCT;
import static accounts.acbtz.jagroadmin.Statics.PRODUCTIMAGE;
import static accounts.acbtz.jagroadmin.Statics.theactivity;


public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{


    Context mContext;
    ArrayList<DataModel> dataSet;
    String theid = "";
    CustomAdapter adapter;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView umri;
        TextView area;
        TextView maelezo;
        TextView pichasource;
        TextView aspectratio;
        TextView love;
        ImageView fire;
        ImageView send;

    }

    public CustomAdapter(ArrayList<DataModel> data, Context context, CustomAdapter adapter) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;
        this.adapter = adapter;



    }

    @Override
    public void onClick(final View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;



    }

    private int lastPosition = -1;

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        // Get the data item for this position
        final  DataModel dataModel = getItem(position);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            TextView name = convertView.findViewById(R.id.name);
            TextView description = convertView.findViewById(R.id.description);
            TextView price = convertView.findViewById(R.id.price);
            ImageView image = convertView.findViewById(R.id.my_image_view);
            Button button = convertView.findViewById(R.id.goorder);


            name.setText(dataModel.getfruitName());
            description.setText(dataModel.getfruitDescription()+" : Kiasi - "+dataModel.getimage());
            price.setText(dataModel.getpricePerOne());


        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    DESCRIPTIONOFPRODUCT = dataModel.getfruitName()+" - "+dataModel.getfruitDescription();
                    PRODUCTIMAGE = "http://greenhut.jagro.co.tz/"+dataModel.getimage();

                    theid = dataModel.getid();
                    new GetFruits().execute();

                    synchronized(theactivity){


                        dataSet.remove(position);
                        notifyDataSetChanged();
                        //CustomAdapter.this.notify();
                        //adapter.notifyDataSetChanged();

                        //notify();
                    }




                }
            });


        lastPosition = position;

        // Return the completed view to render on screen
        return convertView;
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
            String url = "http://greenhut.jagro.co.tz/mark_orders.php?id="+theid;
            String jsonStr = sh.makeServiceCall(url);

            Log.e("RESULT FROM SERVER", "Response from url: " + jsonStr);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


        }
    }







}
