package com.example.medic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder> {
    private List<OnboardingItem> onboardingItems;

    public OnBoardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @Override
    public OnboardingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_onboard,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name,subscription;

         OnboardingViewHolder( View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageOnOnboard);
            name = itemView.findViewById(R.id.nameOnBoard);
            subscription = itemView.findViewById(R.id.subscription_onboard);

        }
        void setOnboardingData(OnboardingItem onboardingItem){
             imageView.setImageResource(onboardingItem.getImage());
             name.setText(onboardingItem.getName());
             subscription.setText(onboardingItem.getSubscription());
        }
    }
}
