package com.charanajayworks.iplplayers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenActivity extends AppCompatActivity {

    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference adRef = mRootReference.child("isadenabled");
    Context mContext;
    SharedPreferences sharedPreferences;
    TextView loadingText;
    Thread splashTread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen_layout);

        mContext = getApplicationContext();

        sharedPreferences = getSharedPreferences("IPL_PLAYERS", MODE_PRIVATE);


        Thread loading = new Thread() {
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreenActivity.this.finish();
                }
            }
        };

        loading.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("FirebaseDB","reached here");
        adRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean isAdEnabled = dataSnapshot.getValue(Boolean.class);
                Log.d("FirebaseDB", "isadenabled:" + isAdEnabled);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isadenabled", isAdEnabled);
                editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
