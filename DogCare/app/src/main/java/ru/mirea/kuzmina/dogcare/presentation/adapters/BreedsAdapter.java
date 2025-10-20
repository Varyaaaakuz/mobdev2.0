package ru.mirea.kuzmina.dogcare.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.kuzmina.domain.models.DogBreed;
import ru.mirea.kuzmina.dogcare.R;

import java.util.List;

public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.BreedViewHolder> {

    private List<DogBreed> breeds;
    private OnBreedClickListener listener;

    public interface OnBreedClickListener {
        void onBreedClick(DogBreed breed);
    }

    public BreedsAdapter(List<DogBreed> breeds, OnBreedClickListener listener) {
        this.breeds = breeds;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_breed, parent, false);
        return new BreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        DogBreed breed = breeds.get(position);
        holder.bind(breed);
        holder.itemView.setOnClickListener(v -> listener.onBreedClick(breed));
    }

    @Override
    public int getItemCount() {
        return breeds.size();
    }

    public void updateBreeds(List<DogBreed> newBreeds) {
        this.breeds = newBreeds;
        notifyDataSetChanged();
    }

    static class BreedViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView sizeTextView;
        private TextView activityTextView;

        public BreedViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.breedName);
            descriptionTextView = itemView.findViewById(R.id.breedDescription);
            sizeTextView = itemView.findViewById(R.id.breedSize);
            activityTextView = itemView.findViewById(R.id.breedActivity);
        }

        public void bind(DogBreed breed) {
            nameTextView.setText(breed.getName());
            descriptionTextView.setText(breed.getDescription());
            sizeTextView.setText(breed.getSize());
            activityTextView.setText(breed.getActivityLevel());
        }
    }
}