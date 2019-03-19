package com.charanajayworks.iplplayers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CardView teamCardView;
    CardView fixturesCardView;
    CardView ticketsCardView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);

        teamCardView = findViewById(R.id.teams_CardView);
        teamCardView.setBackgroundResource(R.drawable.teamcardviewbackground);

        fixturesCardView = findViewById(R.id.fixtures_CardView);
        fixturesCardView.setBackgroundResource(R.drawable.schedulephoto);

        ticketsCardView = findViewById(R.id.tickets_CardView);
        ticketsCardView.setBackgroundResource(R.drawable.tickets);

        teamCardView.setOnClickListener(this);
        fixturesCardView.setOnClickListener(this);
        ticketsCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.teams_CardView :
                Intent intent = new Intent(MainActivity.this,TeamSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.fixtures_CardView:
                Intent intent1 = new Intent(MainActivity.this,ScheduleActivity.class);
                startActivity(intent1);
                break;
            case R.id.tickets_CardView:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iplt20.com/schedule")));
                break;
        }
    }
}
