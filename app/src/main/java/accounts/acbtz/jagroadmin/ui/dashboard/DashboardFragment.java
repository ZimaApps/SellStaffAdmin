package accounts.acbtz.jagroadmin.ui.dashboard;

import android.content.Context;
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
import androidx.recyclerview.widget.ListAdapter;

import accounts.acbtz.jagroadmin.CustomAdapter;
import accounts.acbtz.jagroadmin.R;

import static accounts.acbtz.jagroadmin.MainActivity.dataModels;
import static accounts.acbtz.jagroadmin.Statics.theactivity;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    ListView listView;
    CustomAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        listView = root.findViewById(R.id.matundalistview);


        if(haveNetworkConnection()) {
            if(dataModels !=null) {
                adapter = new CustomAdapter(dataModels, theactivity, adapter);
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