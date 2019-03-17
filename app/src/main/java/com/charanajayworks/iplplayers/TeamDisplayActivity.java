package com.charanajayworks.iplplayers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.charanajayworks.iplplayers.cards.SliderAdapter;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

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

    private SliderAdapter sliderAdapter;

    private String[] imageUrls = {"https://iplstatic.s3.amazonaws.com/players/284/1.png",
    "https://iplstatic.s3.amazonaws.com/players/284/4944.png",
    "https://iplstatic.s3.amazonaws.com/players/284/2756.png",
    "https://iplstatic.s3.amazonaws.com/players/284/4954.png",
    "https://iplstatic.s3.amazonaws.com/players/284/25.png",
    "https://iplstatic.s3.amazonaws.com/players/284/140.png",
    "https://iplstatic.s3.amazonaws.com/players/284/24.png"};

    private String[] playerNames = {
            "MS Dhoni",
            "KM Asif",
            "Sam Billings",
            "Chaitanya Bishnoi",
            "Dwayne Bravo",
            "Deepak Chahar",
            "Faf du Plessis"
    };

    private String[] role = {"Wicketkeeper batsman",
            "All-rounder",
            "",
            "",
            "All-rounder",
            "Bowler",
            "All-rounder"
    };

    private String[] battingStyle = {"Right-handed",
            "Right-handed",
            "Right-handed",
            "Left-handed",
            "Right-handed",
            "Right-handed",
            "Right-handed"
    };

    private String[] bowlingStyle = {"Right-arm medium",
            "",
            "",
            "",
            "Right-arm medium fast",
            "Right-arm medium",
            "Right-arm leg spin"
    };

    private String[] nationality = {
            "Indian",
            "Indian",
            "English",
            "Indian",
            "West Indian",
            "Indian",
            "South African"
    };

    private String[] dob = {
            "07 July 1981",
            "24 July 1993",
            "15 June 1991",
            "25 August 1994",
            "07 October 1983",
            "07 August 1992",
            "13 July 1984"
    };

    private String[] iplDebut = {
            "2008",
            "",
            "",
            "",
            "2008",
            "2012",
            "2012"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());

        initRecyclerView();
        initSwitchers();
        initPlayerTitle();
    }

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
        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setPlayerTitle(playerNames[pos],left2right);

        roleTextSwitcher.setInAnimation(this,animV[0]);
        roleTextSwitcher.setOutAnimation(this,animV[1]);
        roleTextSwitcher.setText(role[pos]);

        battingStyleTextSwitcher.setInAnimation(this,animV[0]);
        battingStyleTextSwitcher.setOutAnimation(this,animV[1]);
        battingStyleTextSwitcher.setText(battingStyle[pos]);

        bowlingStyleTextSwitcher.setInAnimation(this,animV[0]);
        bowlingStyleTextSwitcher.setOutAnimation(this,animV[1]);
        bowlingStyleTextSwitcher.setText(bowlingStyle[pos]);

        nationalityTextSwitcher.setInAnimation(this,animV[0]);
        nationalityTextSwitcher.setOutAnimation(this,animV[1]);
        nationalityTextSwitcher.setText(nationality[pos]);

        dobTextSwitcher.setInAnimation(this,animV[0]);
        dobTextSwitcher.setOutAnimation(this,animV[1]);
        dobTextSwitcher.setText(dob[pos]);

        iplDebutTextSwitcher.setInAnimation(this,animV[0]);
        iplDebutTextSwitcher.setOutAnimation(this,animV[1]);
        iplDebutTextSwitcher.setText(iplDebut[pos]);

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

        roleTextSwitcher = findViewById(R.id.rolevalue);
        roleTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.roleSwitcher,false));
        roleTextSwitcher.setCurrentText(role[0]);

        battingStyleTextSwitcher = findViewById(R.id.batting_style_value);
        battingStyleTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.battingSwitcher,false));
        battingStyleTextSwitcher.setCurrentText(battingStyle[0]);

        bowlingStyleTextSwitcher = findViewById(R.id.bowling_style_value);
        bowlingStyleTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.bowlingSwitcher,false));
        bowlingStyleTextSwitcher.setCurrentText(bowlingStyle[0]);


        nationalityTextSwitcher = findViewById(R.id.nationality_value);
        nationalityTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.nationalitySwitcher,false));
        nationalityTextSwitcher.setCurrentText(nationality[0]);

        dobTextSwitcher = findViewById(R.id.dob_value);
        dobTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.dobSwitcher,false));
        dobTextSwitcher.setCurrentText(dob[0]);

        iplDebutTextSwitcher = findViewById(R.id.ipl_debut_value);
        iplDebutTextSwitcher.setFactory(new TeamDisplayActivity.TextViewFactory(R.style.debutSwitcher,false));
        iplDebutTextSwitcher.setCurrentText(iplDebut[0]);
    }

    private void initPlayerTitle(){
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView) findViewById(R.id.tv_weapon_1);
        weapon2TextView = (TextView) findViewById(R.id.tv_weapon_2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(playerNames[0]);
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
                final Intent intent = new Intent(TeamDisplayActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

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
