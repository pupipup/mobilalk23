package com.example.mobilalk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CompareItemAdapter extends RecyclerView.Adapter<CompareItemAdapter.ViewHolder> implements Filterable {
    private ArrayList<CompareItem> mCompareItemData ;
    private ArrayList<CompareItem> mCompareItemDataAll ;
    private Context mContext;
    private int lastPosition = -1;

    CompareItemAdapter(Context context, ArrayList<CompareItem> itemsData) {
        this.mCompareItemData = itemsData;
        this.mCompareItemDataAll = itemsData;
        this.mContext = context;
    }

    @Override
    public CompareItemAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list, parent, false));
    }

    @Override
    public void onBindViewHolder(CompareItemAdapter.ViewHolder holder, int position) {
        // Get current sport.
        CompareItem currentItem = mCompareItemData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentItem);


        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mCompareItemData.size();
    }


    /**
     * RecycleView filter
     * **/
    @Override
    public Filter getFilter() {
        return shopingFilter;
    }

    private Filter shopingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<CompareItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mCompareItemDataAll.size();
                results.values = mCompareItemDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(CompareItem item : mCompareItemDataAll) {
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mCompareItemData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mItemImage;
        private RatingBar mRatingBar;

        ViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.itemTitle);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mRatingBar = itemView.findViewById(R.id.ratingBar);


        }

        void bindTo(CompareItem currentItem){

            mTitleText.setText(currentItem.getName());
            mInfoText.setText(currentItem.getInfo());
            mRatingBar.setRating(currentItem.getRatedInfo());
            Glide.with(mContext).load(currentItem.getImageResource()).into(mItemImage);

            itemView.findViewById(R.id.delete).setOnClickListener(view -> ((MainPageActivity)mContext).deleteItem(currentItem));
    }
}
    }
