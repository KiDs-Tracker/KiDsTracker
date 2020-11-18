package com.example.kidstracker.ui.calendar;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.databinding.adapters.TimePickerBindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidstracker.R;
import com.example.kidstracker.adapters.CalendarGridAdapter;
import com.example.kidstracker.adapters.EventRecyclerAdapter;
import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.EventDao;
import com.example.kidstracker.models.Events;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CustomCalendarView extends LinearLayout {
    ImageButton nextButton, previousButton;
    TextView currentDateTV;
    GridView gridView;
    Context context;


    private static final int MAX_CALENDAR_DAYS = 42;

    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

    AlertDialog alertDialog;
    CalendarGridAdapter gridAdapter;
    List<Date> dates = new ArrayList<>();
    List<Events> events = new ArrayList<>();

    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeLayout();
        setupCalendar();

        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                setupCalendar();

            }
        });
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                setupCalendar();

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_event_layout, null);
                EditText eventName = addView.findViewById(R.id.event_input);
                TextView eventTime = addView.findViewById(R.id.event_time);
                ImageButton setTime = addView.findViewById(R.id.set_event_time);
                Button addEvents = addView.findViewById(R.id.add_events);
                setTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hFormat = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
                                String event_time = hFormat.format(c.getTime());
                                eventTime.setText(event_time);

                            }

                        }, hours, minutes, false);
                        timePickerDialog.show();

                    }
                });
                final String date = eventDateFormat.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));
                addEvents.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(eventName != null && eventTime != null) {
                            saveEvent(eventName.getText().toString(),
                                    eventTime.getText().toString(),
                                    date,
                                    month,
                                    year);
                            setupCalendar();
                            alertDialog.dismiss();

                        }
                    }
                });
               builder.setView(addView);
               alertDialog = builder.create();
               alertDialog.show();

            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String Date = eventDateFormat.format(dates.get(position));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);
                RecyclerView recyclerView = showView.findViewById(R.id.rv_events);
                recyclerView.setLayoutManager(new LinearLayoutManager(showView.getContext()));
                recyclerView.setHasFixedSize(true);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(), collectEventByDate(Date));
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        setupCalendar();
                    }
                });
                return true;
            }
        });
    }
    private ArrayList<Events> collectEventByDate(String date) {
        ArrayList<Events> arrayList = new ArrayList<>();
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(context);
        EventDao eventDao = kidsTrackingDatabase.eventDao();
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Events> eventsList = eventDao.getEventsOnDate(date);
                arrayList.addAll(eventsList);

            }
        });
        return arrayList;
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
            super(context, attrs, defStyleAttr);

        }


    private void saveEvent(String event, String time, String date, String month, String year){
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(context);
        EventDao eventDao = kidsTrackingDatabase.eventDao();

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Events events = new Events(event, time, date, month, year);
                eventDao.insertEvent(events);
            }
        });

    }


    private void initializeLayout(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        nextButton = view.findViewById(R.id.next_button);
        previousButton = view.findViewById(R.id.previous_button);
        currentDateTV = view.findViewById(R.id.current_date);
        gridView = view.findViewById(R.id.grid_view);

    }
    private void setupCalendar(){
        String currentDate = dateFormat.format(calendar.getTime());
        currentDateTV.setText(currentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth);
        collectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));
        while(dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);

        }
        gridAdapter = new CalendarGridAdapter(context, dates, calendar, events);
        gridView.setAdapter(gridAdapter);
    }
    private void collectEventsPerMonth(String Month, String Year){
        events.clear();
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(context);
        EventDao eventDao = kidsTrackingDatabase.eventDao();
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Events> eventsList = eventDao.getEvents(Month, Year);
                events.addAll(eventsList);
            }
        });

    }
}
