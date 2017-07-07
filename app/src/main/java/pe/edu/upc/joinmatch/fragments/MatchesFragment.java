package pe.edu.upc.joinmatch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.joinmatch.R;
import pe.edu.upc.joinmatch.adapters.EventsAdapter;
import pe.edu.upc.joinmatch.models.Event;
import pe.edu.upc.joinmatch.network.EventsApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment {

    RecyclerView eventsRecyclerView;
    EventsAdapter eventsAdapter;
    RecyclerView.LayoutManager eventsLayoutManager;
    List<Event> events;
    private static String TAG = "JoinMAtch";
    
    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        eventsRecyclerView = (RecyclerView) view.findViewById(R.id.eventsRecyclerView);
        events = new ArrayList<>();
        eventsAdapter = new EventsAdapter(events);
        eventsLayoutManager = new LinearLayoutManager(view.getContext());
        eventsRecyclerView.setAdapter(eventsAdapter);
        eventsRecyclerView.setLayoutManager(eventsLayoutManager);
        return view;
    }
    private void updateData() {

        AndroidNetworking.get(EventsApi.EVENTS_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /*if(response.getString("status").equalsIgnoreCase("error")) {
                            Log.d(TAG, response.getString("message"));
                            return;
                        }*/

                        events = Event.build(response);
                        eventsAdapter.setEvents(events).notifyDataSetChanged();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());
                    }
                });


    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

}
