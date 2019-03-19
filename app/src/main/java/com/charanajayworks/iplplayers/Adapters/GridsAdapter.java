package com.charanajayworks.iplplayers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.charanajayworks.iplplayers.Models.GridLayoutModel;
import com.charanajayworks.iplplayers.R;
import com.charanajayworks.iplplayers.TeamDisplayActivity;

import java.util.List;

public class GridsAdapter extends RecyclerView.Adapter<GridsAdapter.ViewHolder> {

    private Context mContext;
    private List<GridLayoutModel> gridLayoutModelList;

    public GridsAdapter(Context mContext, List<GridLayoutModel> gridLayoutModelList) {
        this.mContext = mContext;
        this.gridLayoutModelList = gridLayoutModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final GridLayoutModel gridLayoutModel = gridLayoutModelList.get(position);

        viewHolder.gridLayoutText.setText(gridLayoutModel.getGridName());
        viewHolder.gridLayoutImage.setBackgroundResource(gridLayoutModel.getImageLink());

        viewHolder.gridCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView gridName = view.findViewById(R.id.gridTextView);
                Log.d("gridName : ",gridName.getText().toString());

                Intent intent = null;

                switch (gridName.getText().toString()){
                    case "Chennai Super Kings" :
                        Log.d("debug","in csk");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","csk");
                        mContext.startActivity(intent);
                        break;
                    case "Royal Challengers Bangalore":
                        Log.d("debug","in rcb");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","royalchallengers");
                        mContext.startActivity(intent);
                        break;
                    case "Delhi Capitals":
                        Log.d("debug","in delhi");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","delhicapitals");
                        mContext.startActivity(intent);
                        break;
                    case "Kings XI Punjab":
                        Log.d("debug","in kxip");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","kingsxipunjab");
                        mContext.startActivity(intent);
                        break;
                    case "Kolkata Knight Riders":
                        Log.d("debug","in kkr");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","kolkataknightriders");
                        mContext.startActivity(intent);
                        break;
                    case "Mumbai Indians":
                        Log.d("debug","in mumbai");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","mumbaiindians");
                        mContext.startActivity(intent);
                        break;
                    case "Rajastan Royals":
                        Log.d("debug","in rajastan");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","rajastanroyals");
                        mContext.startActivity(intent);
                        break;
                    case "Sunrisers Hyderabad":
                        Log.d("debug","in srh");
                        intent = new Intent(mContext, TeamDisplayActivity.class);
                        intent.putExtra("teamname","sunrisers");
                        mContext.startActivity(intent);
                        break;
                    default:
                        Log.d("debug","in default");
                        Intent intent1 = new Intent(mContext, TeamDisplayActivity.class);
                        mContext.startActivity(intent1);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gridLayoutModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView gridLayoutText;
        private ImageView gridLayoutImage;
        private CardView gridCardView;

        public ViewHolder(View itemView) {
            super(itemView);

            gridLayoutText = itemView.findViewById(R.id.gridTextView);
            gridLayoutImage = itemView.findViewById(R.id.gridImageView);
            gridCardView = itemView.findViewById(R.id.gridCardLayout);
        }
    }
}
