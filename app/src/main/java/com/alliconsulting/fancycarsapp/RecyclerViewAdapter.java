package com.alliconsulting.fancycarsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alliconsulting.fancycarsapp.model.CarDetails;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mCarImages = new ArrayList<>();
    private ArrayList<CarDetails> mCarDetails = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> images, ArrayList<CarDetails> carDetails) {

        this.mCarImages = images;
        this.mCarDetails = carDetails;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_detail_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mCarImages.get(position))
                .into(holder.image);

        holder.imageName.setText( "Make: " + mCarDetails.get(position).getCarMake() + "\nModel: " + mCarDetails.get(position).getCarModel() + "\nAvail?: " + mCarDetails.get(position).getAvailability());
        holder.buyButton.setVisibility(View.INVISIBLE);
        if( mCarDetails.get(position).getAvailability().equals("In Dealership") )
            holder.buyButton.setVisibility(View.VISIBLE);
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG,"onClick: clicked on: " + mCarDetails.get(position).getCarMake());
                Toast.makeText(mContext,mCarDetails.get(position).getCarMake(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCarDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //CircleImageView image;
        ImageView image;
        TextView imageName;
        Button buyButton;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.car_image);
            imageName = itemView.findViewById(R.id.car_name);
            buyButton = itemView.findViewById(R.id.buybutton);
            parentLayout = itemView.findViewById(R.id.car_simple_layout);
        }
    }

    public void updateListWithImages(List<String> newImages, List<CarDetails> carDetails){
        mCarImages.addAll(newImages);
        mCarDetails.addAll(carDetails);
        notifyDataSetChanged();
    }


}
