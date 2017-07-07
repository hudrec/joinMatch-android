package pe.edu.upc.joinmatch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.joinmatch.R;
import pe.edu.upc.joinmatch.models.Event;

/**
 * Created by profesores on 5/26/17.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    List<Event> events;

    public EventsAdapter() {

    }

    public EventsAdapter(List<Event> events) {
        this.events = events;
    }

    public EventsAdapter setEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.event_item, parent, false));
    }

    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder holder, int position) {
        holder.nameTextView.setText(events.get(position).getName());
        holder.sportTextView.setText(events.get(position).getSport());


    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView logoImageView;
        TextView nameTextView;
        TextView sportTextView;
        TextView categoryTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            //logoImageView = (ANImageView) itemView.findViewById(R.id.logoImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            sportTextView = (TextView) itemView.findViewById(R.id.sportTextView);
            //categoryTextView = (TextView) itemView.findViewById(R.id.categoryTextView);
        }
    }
}
