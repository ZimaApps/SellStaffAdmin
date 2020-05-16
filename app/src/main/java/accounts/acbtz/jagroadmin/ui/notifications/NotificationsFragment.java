package accounts.acbtz.jagroadmin.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import accounts.acbtz.jagroadmin.Camera;
import accounts.acbtz.jagroadmin.CustomAdapter;
import accounts.acbtz.jagroadmin.CustomAdapterTwo;
import accounts.acbtz.jagroadmin.R;

import static accounts.acbtz.jagroadmin.MainActivity.dataModels;
import static accounts.acbtz.jagroadmin.MainActivity.dataModels2;
import static accounts.acbtz.jagroadmin.Statics.theactivity;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    ListView listView;
    CustomAdapterTwo adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) root.findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click.
                Intent i = new Intent(theactivity, Camera.class);
                theactivity.startActivity(i);
            }
        });

        listView = root.findViewById(R.id.mbogalistview);


        if(haveNetworkConnection()) {
            if(dataModels2 !=null) {
                adapter = new CustomAdapterTwo(dataModels2, theactivity);
                listView.setAdapter(adapter);

                ///////////////MUHIMU SAAAANA///////////////////////////
                if (adapter != null) {
                    int totalHeight = 0;
                    for (int i = 0; i < adapter.getCount(); i++) {
                        View listItem = adapter.getView(i, null, listView);
                        listItem.measure(0, 0);
                        totalHeight += listItem.getMeasuredHeight();
                    }
                    ViewGroup.LayoutParams params = listView.getLayoutParams();
                    params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1)) + 120;
                    listView.setLayoutParams(params);
                    listView.requestLayout();

                }

            }

        }else{
            Toast.makeText(theactivity,"HAKUNA INTERNET!!!!",Toast.LENGTH_LONG).show();
        }

        return root;
    }




    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) theactivity.getSystemService(Context.CONNECTIVITY_SERVICE);
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