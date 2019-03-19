package com.charanajayworks.iplplayers;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.charanajayworks.iplplayers.Adapters.ScheduleAdapter;
import com.charanajayworks.iplplayers.LayoutManagers.VegaLayoutManager;
import com.charanajayworks.iplplayers.Models.ScheduleDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    TextView matchNumberTextView, versusTextView, stadiumTextView, timeTextView;
    TextView headerTextView;

    public RecyclerView scheduleRecycler;
    public ArrayList<ScheduleDataModel> scheduleList;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myDatabaseRef = database.getReference();

    ScheduleAdapter scheduleAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        scheduleRecycler = findViewById(R.id.display_schedule);


        scheduleList = new ArrayList<>();

        myDatabaseRef.addValueEventListener(valueEventListener);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        scheduleRecycler.setLayoutManager(new VegaLayoutManager());

        scheduleAdapter = new ScheduleAdapter(this,scheduleList);

//        scheduleRecycler.setAdapter(scheduleAdapter);
    }

    ValueEventListener valueEventListener = new ValueEventListener(){

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            DataSnapshot scheduleSnapShot = dataSnapshot.child("schedule");
            for(DataSnapshot data : scheduleSnapShot.getChildren()){
                int matchNumber = data.child("matchnumber").getValue(Integer.class);
                String matchDate = data.child("matchdate").getValue(String.class);
                String matchTime = data.child("matchtime").getValue(String.class);
                String matchVenue = data.child("matchvenue").getValue(String.class);
                String team1 = data.child("team1").getValue(String.class);
                String team2 = data.child("team2").getValue(String.class);

                Log.d("SCHEDULEACTIVITY","matchDate:"+matchDate);

                ScheduleDataModel currentSchedule = new ScheduleDataModel("Match "+matchNumber,matchDate,team1,team2,matchTime,matchVenue);
                scheduleList.add(currentSchedule);

                scheduleAdapter.notifyDataSetChanged();

                scheduleRecycler.setAdapter(scheduleAdapter);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

    };

    @Override
    public void onClick(View view) {

    }
}
