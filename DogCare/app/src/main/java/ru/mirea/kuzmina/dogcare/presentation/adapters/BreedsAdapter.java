package ru.mirea.kuzmina.dogcare.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.R;

import java.util.ArrayList;
import java.util.List;

public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.BreedViewHolder> {

    private List<Dog> breeds;
    private final OnBreedClickListener listener;

    public interface OnBreedClickListener {
        void onBreedClick(Dog breed);
    }

    public BreedsAdapter(List<Dog> breeds, OnBreedClickListener listener) {
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
        Dog breed = breeds.get(position);
        holder.bind(breed);
        holder.itemView.setOnClickListener(v -> listener.onBreedClick(breed));
    }

    @Override
    public int getItemCount() {
        return breeds != null ? breeds.size() : 0;
    }

    public void updateBreeds(List<Dog> newBreeds) {
        this.breeds = newBreeds;
        notifyDataSetChanged();
    }

    public void submitList(List<Dog> newBreeds) {
        this.breeds = newBreeds != null ? newBreeds : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class BreedViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView sizeActivityTextView;
        private ImageView breedImageView;

        public BreedViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.breedName);
            descriptionTextView = itemView.findViewById(R.id.breedDescription);
            sizeActivityTextView = itemView.findViewById(R.id.breedSizeActivity);
            breedImageView = itemView.findViewById(R.id.breedImage);
        }

        public void bind(Dog breed) {
            nameTextView.setText(breed.getName());
            descriptionTextView.setText(breed.getDescription());

            String sizeActivity = breed.getSize() + " | " + breed.getActivityLevel();
            sizeActivityTextView.setText(sizeActivity);

            if (breed.getImageUrl() != null && !breed.getImageUrl().isEmpty()) {
                Picasso.get()
                        .load(breed.getImageUrl())
                        .placeholder(R.drawable.placeholder_dog)
                        .error(R.drawable.error_dog)
                        .resize(120, 120)
                        .centerCrop()
                        .into(breedImageView);
            } else {
                breedImageView.setImageResource(R.drawable.placeholder_dog);
            }
        }
    }
}