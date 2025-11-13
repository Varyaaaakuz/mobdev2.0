package ru.mirea.kuzmina.recyclerviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<HistoricalEvent> events;
    private Context context;
    public EventRecyclerViewAdapter(List<HistoricalEvent> events) {
        this.events = events;
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        context = parent.getContext();
        View recyclerViewItem = LayoutInflater.from(context)
                .inflate(R.layout.event_item_view, parent, false);
        return new EventViewHolder(recyclerViewItem);
    }
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        HistoricalEvent event = this.events.get(position);
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(event.getImageName(), "drawable", pkgName);
        if (resID == 0) {
            resID = R.drawable.ic_history;
        }
        holder.getFlagView().setImageResource(resID);
        holder.getEventNameView().setText(event.getEventName());
        holder.getDescriptionView().setText(event.getDescription());
        holder.getYearView().setText(String.valueOf(event.getYear()));
    }
    @Override
    public int getItemCount() {
        return this.events.size();
    }
}
