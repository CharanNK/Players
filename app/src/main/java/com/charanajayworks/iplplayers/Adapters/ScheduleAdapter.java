package com.charanajayworks.iplplayers.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.charanajayworks.iplplayers.Models.ScheduleDataModel;
import com.charanajayworks.iplplayers.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private Context mContext;
    private List<ScheduleDataModel> scheduleList;

    public ScheduleAdapter(Context mContext, List<ScheduleDataModel> scheduleList) {
        this.mContext = mContext;
        this.scheduleList = scheduleList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedulecardview, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final ScheduleDataModel currentSchedule = scheduleList.get(position);

        viewHolder.matchNumberTextView.setText(currentSchedule.getMatchNumberString());
        viewHolder.timeTextView.setText(currentSchedule.getTimingString());
        viewHolder.stadiumTextView.setText(currentSchedule.getVenueString());
        viewHolder.matchDateTextView.setText(currentSchedule.getMatchDate());

        switch (currentSchedule.getTeam1Name()) {
            case "CSK":
                viewHolder.team1ImageView.setImageResource(R.drawable.chennai_superkings);
                break;
            case "RCB":
                viewHolder.team1ImageView.setImageResource(R.drawable.royal_challengers_bangalore);
                break;
            case "DC":
                viewHolder.team1ImageView.setImageResource(R.drawable.delhi_capitals);
                break;
            case "SRH":
                viewHolder.team1ImageView.setImageResource(R.drawable.sunrisers_hyderabad);
                break;
            case "MI":
                viewHolder.team1ImageView.setImageResource(R.drawable.mumbai_indians);
                break;
            case "KXIP":
                viewHolder.team1ImageView.setImageResource(R.drawable.kings_xi_punjab);
                break;
            case "RR":
                viewHolder.team1ImageView.setImageResource(R.drawable.rajastan_royals);
                break;
            case "KKR":
                viewHolder.team1ImageView.setImageResource(R.drawable.kolkata_knight_riders);
                break;
            default:
                viewHolder.team1ImageView.setImageResource(R.drawable.ic_launcher_background);
                break;
        }

        switch (currentSchedule.getTeam2Name()){
            case "CSK":
                viewHolder.team2ImageView.setImageResource(R.drawable.chennai_superkings);
                break;
            case "RCB":
                viewHolder.team2ImageView.setImageResource(R.drawable.royal_challengers_bangalore);
                break;
            case "DC":
                viewHolder.team2ImageView.setImageResource(R.drawable.delhi_capitals);
                break;
            case "SRH":
                viewHolder.team2ImageView.setImageResource(R.drawable.sunrisers_hyderabad);
                break;
            case "MI":
                viewHolder.team2ImageView.setImageResource(R.drawable.mumbai_indians);
                break;
            case "KXIP":
                viewHolder.team2ImageView.setImageResource(R.drawable.kings_xi_punjab);
                break;
            case "RR":
                viewHolder.team2ImageView.setImageResource(R.drawable.rajastan_royals);
                break;
            case "KKR":
                viewHolder.team2ImageView.setImageResource(R.drawable.kolkata_knight_riders);
                break;
            default:
                viewHolder.team2ImageView.setImageResource(R.drawable.ic_launcher_background);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matchNumberTextView, stadiumTextView, timeTextView,matchDateTextView;
        ImageView team1ImageView, team2ImageView;
        CardView scheduleCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            matchNumberTextView = itemView.findViewById(R.id.matchNumberTextView);
            stadiumTextView = itemView.findViewById(R.id.stadiumTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);

            team1ImageView = itemView.findViewById(R.id.team1_imageview);
            team2ImageView = itemView.findViewById(R.id.team2_imageview);

            scheduleCardView = itemView.findViewById(R.id.schedule_card);

            matchDateTextView = itemView.findViewById(R.id.matchDateTextView);
        }
    }
}
