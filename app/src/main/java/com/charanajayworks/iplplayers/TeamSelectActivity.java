package com.charanajayworks.iplplayers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.iplplayers.Adapters.GridsAdapter;
import com.charanajayworks.iplplayers.R;
import com.charanajayworks.iplplayers.Models.GridLayoutModel;

import java.util.ArrayList;

public class TeamSelectActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    ArrayList<GridLayoutModel> teamsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamselectlayout);

        recyclerView = findViewById(R.id.gridsRecycler);

        teamsList = new ArrayList<GridLayoutModel>();

        teamsList.add(new GridLayoutModel("Chennai Super Kings",R.drawable.chennai_superkings));
        teamsList.add(new GridLayoutModel("Royal Challengers Bangalore",R.drawable.royal_challengers_bangalore));
        teamsList.add(new GridLayoutModel("Delhi Capitals",R.drawable.delhi_capitals));
        teamsList.add(new GridLayoutModel("Kings XI Punjab",R.drawable.kings_xi_punjab));
        teamsList.add(new GridLayoutModel("Kolkata Knight Riders",R.drawable.kolkata_knight_riders));
        teamsList.add(new GridLayoutModel("Mumbai Indians",R.drawable.mumbai_indians));
        teamsList.add(new GridLayoutModel("Rajastan Royals",R.drawable.rajastan_royals));
        teamsList.add(new GridLayoutModel("Sunrisers Hyderabad",R.drawable.sunrisers_hyderabad));

        GridsAdapter gridsAdapter = new GridsAdapter(TeamSelectActivity.this,teamsList);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridsAdapter);
    }
}
