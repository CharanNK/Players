package com.charanajayworks.iplplayers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.charanajayworks.iplplayers.Models.PlayerDataModel;
import com.charanajayworks.iplplayers.cards.SliderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;

public class TeamDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardSliderLayoutManager layoutManger;

    private TextView weapon1TextView;
    private TextView weapon2TextView;
    private int weaponOffset1;
    private int weaponOffset2;
    private long weaponAnimDuration;
    private int currentPosition;

    private TextSwitcher roleTextSwitcher;
    private TextSwitcher battingStyleTextSwitcher;
    private TextSwitcher bowlingStyleTextSwitcher;
    private TextSwitcher nationalityTextSwitcher;
    private TextSwitcher dobTextSwitcher;
    private TextSwitcher iplDebutTextSwitcher;
    private TextSwitcher playerDescriptionSwitcher;

    private SliderAdapter sliderAdapter;

    private String teamName;

    ArrayList<PlayerDataModel> playersList;

    private String[] imageUrls;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myDatabaseRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_players_layout);

        teamName = getIntent().getExtras().getString("teamname");

        Log.d("TeamDisplay",teamName);

        playersList = new ArrayList<>();

        myDatabaseRef.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            try {
                DataSnapshot teamPlayers = dataSnapshot.child(teamName);
                for (DataSnapshot player : teamPlayers.getChildren()) {
                    String playerName = player.child("playername").getValue(String.class);
                    String badge1 = player.child("badge1").getValue(String.class);
                    String badge2 = player.child("badge2").getValue(String.class);
                    String imageurl = player.child("imageurl").getValue(String.class);
                    String playerRole = player.child("role").getValue(String.class);
                    String battingStyle = player.child("battingstyle").getValue(String.class);
                    String bowlingStyle = player.child("bowlingstyle").getValue(String.class);
                    String nationality = player.child("nationality").getValue(String.class);
                    String dateOfBirth = player.child("dob").getValue(String.class);
                    String iplDebut = player.child("ipldebut").getValue(String.class);
                    String playerDesc = player.child("playerdesc").getValue(String.class);

                    PlayerDataModel currentPlayer = new PlayerDataModel(playerName, imageurl, badge1, badge2, playerRole, battingStyle, bowlingStyle, nationality, dateOfBirth, iplDebut, playerDesc);
                    playersList.add(currentPlayer);
                }

                imageUrls = new String[playersList.size()];

                for (int i = 0; i < playersList.size(); i++)
                    imageUrls[i] = playersList.get(i).getImageUrl();

                sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());

                initRecyclerView();
                initSwitchers();
                initPlayerTitle();
            }catch (Exception e){
                Log.e("ERROR",e.getMessage());
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(sliderAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();


        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos){

        PlayerDataModel currentPlayer = playersList.get(pos%playersList.size());

        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setPlayerTitle(currentPlayer.getPlayerName(),left2right);

        roleTextSwitcher.setInAnimation(this,animV[0]);
        roleTextSwitcher.setOutAnimation(this,animV[1]);
        roleTextSwitcher.setText(currentPlayer.getPlayerRole());

        battingStyleTextSwitcher.setInAnimation(this,animV[0]);
        battingStyleTextSwitcher.setOutAnimation(this,animV[1]);
        battingStyleTextSwitcher.setText(currentPlayer.getBattingStyle());

        bowlingStyleTextSwitcher.setInAnimation(this,animV[0]);
        bowlingStyleTextSwitcher.setOutAnimation(this,animV[1]);
        bowlingStyleTextSwitcher.setText(currentPlayer.getBowlingStyle());

        nationalityTextSwitcher.setInAnimation(this,animV[0]);
        nationalityTextSwitcher.setOutAnimation(this,animV[1]);
        nationalityTextSwitcher.setText(currentPlayer.getNationality());

        dobTextSwitcher.setInAnimation(this,animV[0]);
        dobTextSwitcher.setOutAnimation(this,animV[1]);
        dobTextSwitcher.setText(currentPlayer.getDateOfBirth());

        iplDebutTextSwitcher.setInAnimation(this,animV[0]);
        iplDebutTextSwitcher.setOutAnimation(this,animV[1]);
        iplDebutTextSwitcher.setText(currentPlayer.getIplDebut());

        playerDescriptionSwitcher.setText(currentPlayer.getPlayerDescription());

        currentPosition = pos;
    }

    private void setPlayerTitle(String playerName, boolean left2right) {
        final TextView invisibleText;
        final TextView visibleText;
        if (weapon1TextView.getAlpha() > weapon2TextView.getAlpha()) {
            visibleText = weapon1TextView;
            invisibleText = weapon2TextView;
        } else {
            visibleText = weapon2TextView;
            invisibleText = weapon1TextView;
        }

        final int vOffset;
        if (left2right) {
            invisibleText.setX(0);
            vOffset = weaponOffset2;
        } else {
            invisibleText.setX(weaponOffset2);
            vOffset = 0;
        }

        invisibleText.setText(playerName);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", weaponOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.setDuration(weaponAnimDuration);
        animSet.start();
    }

    private void initSwitchers(){

        PlayerDataModel currentPlayer = playersList.get(0);

        roleTextSwitcher = findViewById(R.id.rolevalue);
        roleTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.roleSwitcher,false));
        roleTextSwitcher.setCurrentText(currentPlayer.getPlayerRole());

        battingStyleTextSwitcher = findViewById(R.id.batting_style_value);
        battingStyleTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.battingSwitcher,false));
        battingStyleTextSwitcher.setCurrentText(currentPlayer.getBattingStyle());

        bowlingStyleTextSwitcher = findViewById(R.id.bowling_style_value);
        bowlingStyleTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.bowlingSwitcher,false));
        bowlingStyleTextSwitcher.setCurrentText(currentPlayer.getBowlingStyle());


        nationalityTextSwitcher = findViewById(R.id.nationality_value);
        nationalityTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.nationalitySwitcher,false));
        nationalityTextSwitcher.setCurrentText(currentPlayer.getNationality());

        dobTextSwitcher = findViewById(R.id.dob_value);
        dobTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.dobSwitcher,false));
        dobTextSwitcher.setCurrentText(currentPlayer.getDateOfBirth());

        iplDebutTextSwitcher = findViewById(R.id.ipl_debut_value);
        iplDebutTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.debutSwitcher,false));
        iplDebutTextSwitcher.setCurrentText(currentPlayer.getIplDebut());

        playerDescriptionSwitcher = findViewById(R.id.player_description);
        playerDescriptionSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.playerDescSwitcher,false));
        playerDescriptionSwitcher.setInAnimation(this, R.anim.fade_in);
        playerDescriptionSwitcher.setOutAnimation(this, R.anim.fade_out);
        playerDescriptionSwitcher.setCurrentText(currentPlayer.getPlayerDescription());
    }

    private void initPlayerTitle(){
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView) findViewById(R.id.tv_player1);
        weapon2TextView = (TextView) findViewById(R.id.tv_player2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(playersList.get(0).getPlayerName());
        weapon2TextView.setAlpha(0f);

        weapon1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
        weapon2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }

    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm = (CardSliderLayoutManager) recyclerView.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }

            final int clickedPosition = recyclerView.getChildAdapterPosition(view);
            if (clickedPosition == activeCardPosition) {
                final Intent intent = new Intent(TeamDisplayActivity.this, ImageDetailsActivity.class);
                intent.putExtra(ImageDetailsActivity.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(TeamDisplayActivity.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }

    private class TextViewFactory implements ViewSwitcher.ViewFactory {

        @StyleRes
        final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(TeamDisplayActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(TeamDisplayActivity.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
        }

    }
}
