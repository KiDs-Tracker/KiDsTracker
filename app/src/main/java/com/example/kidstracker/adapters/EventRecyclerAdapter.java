package com.example.kidstracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidstracker.R;
import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.EventDao;
import com.example.kidstracker.models.Events;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.EventRecyclerViewHolder> {
    private Context context;
    private ArrayList<Events> eventsArrayList;

    public EventRecyclerAdapter(Context context, ArrayList<Events> eventsArrayList) {
        this.context = context;
        this.eventsArrayList = eventsArrayList;
    }

    @NonNull
    @Override
    public EventRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_event_item,parent, false);
        return new EventRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRecyclerViewHolder holder, int position) {
        Events events = eventsArrayList.get(position);
        holder.eventTextView.setText(events.getEvent());
        holder.dateTextView.setText(events.getDate());
        holder.timeTextView.setText(events.getTime());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCalendarEvent(events);
                eventsArrayList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    private void deleteCalendarEvent(Events events) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(context);
        EventDao eventDao = kidsTrackingDatabase.eventDao();
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                eventDao.deleteEvent(events);
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventsArrayList.size();
    }

    public class EventRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTextView;
        private TextView eventTextView;
        private TextView timeTextView;
        private Button deleteButton;
        private ImageButton setAlarmImageButton;

        public EventRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.event_date);
            eventTextView = itemView.findViewById(R.id.event_name);
            timeTextView = itemView.findViewById(R.id.event_time);
            deleteButton = itemView.findViewById(R.id.delete);
            setAlarmImageButton = itemView.findViewById(R.id.ib_alarm);
        }
    }
}
