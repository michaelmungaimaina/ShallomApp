package com.mich.gwan.shallom.fragment;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.home.MainActivity;
import com.mich.gwan.shallom.adapter.EventAdapter;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.BottomSheetActionBinding;
import com.mich.gwan.shallom.databinding.BottomSheetEventBinding;
import com.mich.gwan.shallom.databinding.FragmentAnnouncementsBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.helper.RecyclerTouchListener;
import com.mich.gwan.shallom.model.Event;
import com.mich.gwan.shallom.service.AsyncTaskExecutorService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AnnouncementsFragment extends Fragment {
    private FragmentAnnouncementsBinding binding;

    private ViewFlipper viewFlipper;

    private RecyclerView eventsRecycler;

    private TextView textViewEmptyEvents;
    private TextView textViewUpcomingEvents;
    private TextView textViewPastEvents;
    private TextView textViewVertLine1;
    private TextView textViewVertLine2;

    private FloatingActionButton fabEvent;

    private LinearLayout pastEvents;
    private LinearLayout upcomingEvents;

    private InputValidation inputValidation;

    private Context context;

    private DatabaseHelper databaseHelper;

    private RecyclerView.LayoutManager myLayoutManager;

    private EventAdapter adapter;

    private DatePickerDialog datePickerDialog;
    private LocalDateTime localStartDate;
    private LocalDateTime localEndDate;



    private List<Event> list;
    private List<Event> controllist;
    private int[] images = {R.drawable.ic_ministry_green, R.drawable.ic_group_green, R.drawable.ic_sermon_green}; // Add your image resources here
    private String[] urls = {"https://www.example1.com", "https://www.example2.com", "https://www.example3.com"}; // URLs corresponding to each image
    private int currentPosition = 0;

    private NotificationCompat.Builder mBuilder;


    /** Accessing context **/
    @Override
    public void onAttach(@NonNull Context mContext){
        super.onAttach(mContext);
        context = mContext;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentAnnouncementsBinding.inflate(inflater, container, false);

        // Initializes views, listeners, and objects using helper methods
        initViews();
        initObjects();

        return binding.getRoot();
    }

    /**
     * Initializes views and sets up recyclerview event listeners.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initViews() {
        viewFlipper = binding.viewFlipper;
        eventsRecycler = binding.recyclerViewAnnouncements;
        textViewEmptyEvents = binding.textViewEmptyEvents;
        textViewUpcomingEvents = binding.textViewUpcomingEvents;
        textViewPastEvents = binding.textViewPastEvent;
        textViewVertLine1 = binding.verticalLine1;
        textViewVertLine2 = binding.verticalLine2;
        pastEvents = binding.layoutPastEvents;
        upcomingEvents = binding.layoutUpcomingEvents;
        fabEvent = binding.fabEvent;

        // show flipper images
        for (int image : images) {
            setFlipperImage(image);
        }

        handleOnClick();
    }

    private void setFlipperImage(int res){
        Log.i("Set Flipper called ", res + " ");
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(res);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
    }

    private void initObjects() {
        inputValidation = new InputValidation(context);
        databaseHelper = new DatabaseHelper(context);
        list = new ArrayList<>();
        controllist = new ArrayList<>();
        adapter = new EventAdapter(list);

        myLayoutManager = new LinearLayoutManager(context);
        eventsRecycler.setLayoutManager(myLayoutManager);
        eventsRecycler.setItemAnimator(new DefaultItemAnimator());
        eventsRecycler.setHasFixedSize(true);
        eventsRecycler.setAdapter(adapter);

        controllist = databaseHelper.getEvent();
        //getDataFromSQLite();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            showUpcomingEvents();
            //adapter.notifyDataSetChanged();
        }

        toggleEmptyList();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void handleOnClick() {
        upcomingEvents.setOnClickListener(view -> {
            showUpcomingEvents();
            textViewVertLine1.setBackgroundColor(ContextCompat.getColor(context, R.color.green_bright));
            textViewVertLine2.setBackgroundColor(ContextCompat.getColor(context, R.color.green_700));
            textViewUpcomingEvents.setTextColor(ContextCompat.getColor(context, R.color.white));
            textViewPastEvents.setTextColor(ContextCompat.getColor(context, R.color.green_700));
            System.out.println("Element clicked");
            toggleEmptyList();
        });
        pastEvents.setOnClickListener(view -> {
            showPastEvents();
            textViewVertLine1.setBackgroundColor(ContextCompat.getColor(context, R.color.green_700));
            textViewVertLine2.setBackgroundColor(ContextCompat.getColor(context, R.color.green_bright));
            textViewUpcomingEvents.setTextColor(ContextCompat.getColor(context, R.color.green_700));
            textViewPastEvents.setTextColor(ContextCompat.getColor(context, R.color.white));
            System.out.println("Element clicked");
            toggleEmptyList();
        });

        // recyclerview click events
        eventsRecycler.addOnItemTouchListener(new RecyclerTouchListener(context, eventsRecycler,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        System.out.println(controllist.get(position).getEventEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + "  " +
                                "  " + System.currentTimeMillis());
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        showActionsBottomSheet(position);
                    }
                }));

        // Add new event
        fabEvent.setOnClickListener(view -> {
            showUpdateBottomSheet(false, null, -1);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showPastEvents() {
        for (int i = 0; i < controllist.size(); i++){
            list.clear();
            if (controllist.get(i).getEventEndDate().atZone(ZoneId.of("Africa/Nairobi")).toInstant().toEpochMilli() < System.currentTimeMillis())
                list.add(controllist.get(i));
        }
        adapter.updateList(list);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showUpcomingEvents() {
        list.clear();
        for (int i = 0; i < controllist.size(); i++){
            if (controllist.get(i).getEventStartDate().atZone(ZoneId.of("Africa/Nairobi")).toInstant().toEpochMilli() > System.currentTimeMillis())
                list.add(controllist.get(i));
        }
        adapter.updateList(list);
    }

    /**
     * Retrieves data from SQLite database using AsyncTask.
     * This method clears the list and populates it with data from the database.
     */
    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite(){
        // AsyncTask is used so that SQLite operation does not block the UI Thread.
        new AsyncTaskExecutorService<Void, Void, Void>(){
            /**
             * Performs database operation in the background.
             *
             * @param unused No parameters are passed to this method.
             * @return null
             */
            @Override
            protected Void doInBackground(Void unused) {
                controllist.clear();
                controllist.addAll(databaseHelper.getEvent());
                return null;
            }
            /**
             * Updates UI after database operation is completed.
             *
             * @param unused No parameters are passed to this method.
             */
            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(Void unused) {
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    /**
     * Displays a bottom sheet with actions for the selected item.
     *
     * @param position The position of the selected item in the list.
     */
    private void showActionsBottomSheet(final int position) {
                @SuppressLint("PrivateResource") final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context,
                        com.google.android.material.R.style.Base_V14_ThemeOverlay_MaterialComponents_BottomSheetDialog);
                BottomSheetActionBinding actionBinding = BottomSheetActionBinding.inflate(getLayoutInflater());
                bottomSheetDialog.setContentView(actionBinding.getRoot());

                final TextView update = actionBinding.textViewUpdate;
                final TextView delete = actionBinding.textViewDelete;

                update.setOnClickListener(view -> {
                    // Edit action selected
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        showUpdateBottomSheet(true, list.get(position), position);
                    }
                });

                delete.setOnClickListener(view -> {
                    // Delete action selected
                    deleteValue(position);
                });

                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.setDismissWithAnimation(true);
                bottomSheetDialog.show();
    }


    /**
     * Deleting values from SQLite and removing the
     * item from the list by its position
     */
    private void deleteValue(int position) {
        // deleting the value from db
        databaseHelper.deleteEvent(String.valueOf(list.get(position).getEventId()));
        // removing value from the list
        list.remove(position);
        adapter.notifyItemRemoved(position);

        toggleEmptyList();
        getDataFromSQLite();
    }

    /**
     * Shows alert dialog with EditText options to enter / edit
     * a note.
     * when shouldUpdate=true, it automatically displays old note and changes the
     * button text to UPDATE
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showUpdateBottomSheet(final boolean shouldUpdate, final Event event, final int position){
        @SuppressLint("PrivateResource") final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context,
                com.google.android.material.R.style.Base_V14_ThemeOverlay_MaterialComponents_BottomSheetDialog);
        BottomSheetEventBinding eventBinding = BottomSheetEventBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(eventBinding.getRoot());

        final EditText eventTitle = eventBinding.textInputEditTextEventTitle;
        final EditText eventLocation = eventBinding.textInputEditTextEventLocation;
        final EditText eventDescription = eventBinding.textInputEditTextEventDescription;
        final TextView startDate = eventBinding.textViewStartDate;
        final TextView endDate = eventBinding.textViewEndDate;
        final TextView headerText = eventBinding.textViewTitle;
        final AppCompatButton submitButton = eventBinding.submitButton;

        headerText.setText(!shouldUpdate ? getString(R.string.event_registration) : getString(R.string.event_update));

        if (shouldUpdate && event != null) {
            // display text to views
            eventTitle.setText(event.getEventTitle());
            eventLocation.setText(event.getEventLocation());
            eventDescription.setText(event.getEventDescription());
            startDate.setText(String.valueOf(event.getEventStartDate()));
            endDate.setText(String.valueOf(event.getEventEndDate()));
        }

        submitButton.setText(shouldUpdate ? getString(R.string.update) : getString(R.string.submit));
        startDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(context, R.style.CustomDatePickerDialogTheme, (view, year, month, dayOfMonth) -> {
                localStartDate = LocalDateTime.of(year,month,dayOfMonth - 1,18, 0);
                if (dayOfMonth < 10 && month < 9 ){
                    String strDate = year + "-0" + (month + 1) + "-0" + dayOfMonth;
                    startDate.setText(strDate);
                }
                if (dayOfMonth < 10 && month >= 9){
                    String strDate = year + "-" + (month + 1) + "-0" + dayOfMonth;
                    startDate.setText(strDate);
                }
                if (dayOfMonth >= 10 && month < 9){
                    String strDate = year + "-0" + (month + 1) + "-" + dayOfMonth;
                    startDate.setText(strDate);
                }
                if (dayOfMonth >= 10 && month >= 9){
                    String strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                    startDate.setText(strDate);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000));
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        endDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(context, R.style.CustomDatePickerDialogTheme, (view, year, month, dayOfMonth) -> {
                localEndDate = LocalDateTime.of(year,month,dayOfMonth,18, 0);
                if (dayOfMonth < 10 && month < 9 ){
                    String strDate = year + "-0" + (month + 1) + "-0" + dayOfMonth;
                    endDate.setText(strDate);
                }
                if (dayOfMonth < 10 && month >= 9){
                    String strDate = year + "-" + (month + 1) + "-0" + dayOfMonth;
                    endDate.setText(strDate);
                }
                if (dayOfMonth >= 10 && month < 9){
                    String strDate = year + "-0" + (month + 1) + "-" + dayOfMonth;
                    endDate.setText(strDate);
                }
                if (dayOfMonth >= 10 && month >= 9){
                    String strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                    endDate.setText(strDate);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000));
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        submitButton.setOnClickListener(v -> {
            // Show error message when no text is entered
            if (!inputValidation.isEditTextOccupied(eventTitle, getString(R.string.enter_event_title)))
                return;
            if (!inputValidation.isEditTextOccupied(eventLocation, getString(R.string.enter_event_location)))
                return;
            if (!inputValidation.isEditTextOccupied(eventDescription, getString(R.string.enter_event_description)))
                return;
            if (startDate.getText().toString().isEmpty())
                return;
            if (endDate.getText().toString().isEmpty())
                return;

            // check if user is updating values
            if (shouldUpdate && event != null) {
                // update values by it's position
                updateValue(eventLocation.getText().toString().toUpperCase(), eventTitle.getText().toString().toUpperCase(),
                        eventDescription.getText().toString().toUpperCase(), String.valueOf(localStartDate),
                        String.valueOf(localEndDate), position);
                Toast.makeText(context,getString(R.string.event_update_success),Toast.LENGTH_SHORT).show();
            } else {
                // create new note
                insertValue(eventTitle.getText().toString().toUpperCase(), eventLocation.getText().toString().toUpperCase(),
                        eventDescription.getText().toString().toUpperCase(), String.valueOf(localStartDate),
                        String.valueOf(localEndDate));
                Toast.makeText(context,getString(R.string.event_registration_success),Toast.LENGTH_SHORT).show();
            }
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.setDismissWithAnimation(true);
        bottomSheetDialog.show();
    }

    /**
     * Updating values in db and updating
     * item in the list by its position
     */
    private void updateValue(String eventLocation, String eventTitle, String eventDescription,
                             String eventStartDate, String eventEndDate, int position) {
        Event par = list.get(position);
        // updating values
        par.setEventLocation(eventLocation);
        par.setEventTitle(eventTitle);
        par.setEventDescription(eventDescription);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            par.setEventStartDate(LocalDateTime.parse(eventStartDate));
            par.setEventEndDate(LocalDateTime.parse(eventEndDate));
        }
        // updating values in db
        databaseHelper.updateEvent(par);
        // refreshing the list
        list.set(position, par);
        adapter.notifyItemChanged(position);

        toggleEmptyList();
        getDataFromSQLite();
    }

    /**
     * Inserting new value in db
     * and refreshing the list
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    private void insertValue(String eventTitle, String eventLocation, String eventDescription, String startDate, String endDate) {
        Event par = new Event();
        par.setEventTitle(eventTitle);
        par.setEventLocation(eventLocation);
        par.setEventDescription(eventDescription);
        par.setRegisteredBy(getString(R.string.name_surname));
        par.setRegistrationDate(LocalDateTime.now());
        par.setEventStartDate(LocalDateTime.parse(startDate));
        par.setEventEndDate(LocalDateTime.parse(endDate));
        databaseHelper.addEvent(par);

        // adding new values to array list at 0 position
        list.add(0, par);
        // refreshing the list
        adapter.notifyDataSetChanged();
        toggleEmptyList();
        getDataFromSQLite();
    }

    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyList() {
        // check list.size() > 0
        if (!databaseHelper.getEventCount())
            textViewEmptyEvents.setVisibility(View.GONE);
        else
            textViewEmptyEvents.setVisibility(View.VISIBLE);
    }

}
