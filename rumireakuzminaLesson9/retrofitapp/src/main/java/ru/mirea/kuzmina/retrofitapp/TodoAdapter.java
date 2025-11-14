package ru.mirea.kuzmina.retrofitapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Todo> todos;
    private ApiService apiService;
    private Context context;
    private final String[] IMAGE_URLS = {
            "https://avatars.mds.yandex.net/i?id=5341d1c588443bdcd32bef8833960a55_l-9240324-images-thumbs&n=13",
            "https://i.pinimg.com/originals/79/71/17/7971179610ddb82ddfdf19ba213f3ef1.jpg",
            "https://i.pinimg.com/originals/3f/33/70/3f3370f07800a16092c40e3d7aae1ad3.jpg",
            "https://avatars.mds.yandex.net/i?id=b9331dc0217561500b94d4f7dada2357_l-9066790-images-thumbs&n=13",
            "https://avatars.mds.yandex.net/i?id=44d9118ddf0083d613c28e21b275abbc_l-9205558-images-thumbs&n=13",
            "https://i.pinimg.com/originals/60/e6/e3/60e6e3d42a4f9a711c56a63202d9840c.jpg"
    };

    public TodoAdapter(Context context, List<Todo> todoList, ApiService apiService) {
        this.layoutInflater = LayoutInflater.from(context);
        this.todos = todoList;
        this.apiService = apiService;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.textViewTitle.setText(todo.getTitle());
        holder.checkBoxCompleted.setChecked(Boolean.TRUE.equals(todo.getCompleted()));
        loadImageWithPicasso(holder.imageView, todo.getId());
        holder.checkBoxCompleted.setOnCheckedChangeListener(null);
        holder.checkBoxCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    todos.get(adapterPosition).setCompleted(isChecked);
                    updateTodoOnServer(todos.get(adapterPosition), adapterPosition);
                }
            }
        });
    }

    private void loadImageWithPicasso(ImageView imageView, Integer todoId) {
        String imageUrl = IMAGE_URLS[todoId % IMAGE_URLS.length];

        Log.d("Picasso", "Loading image: " + imageUrl);

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_image)
                .resize(100, 100)
                .centerCrop()
                .into(imageView);
    }

    private void updateTodoOnServer(Todo todo, int position) {
        Todo updateTodo = new Todo();
        updateTodo.setUserId(todo.getUserId());
        updateTodo.setId(todo.getId());
        updateTodo.setTitle(todo.getTitle());
        updateTodo.setCompleted(todo.getCompleted());

        Call<Todo> call = apiService.updateTodo(todo.getId(), updateTodo);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                if (response.isSuccessful()) {
                    Log.d("TodoAdapter", "Todo updated successfully: " + todo.getId());
                    Toast.makeText(context, "Todo updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("TodoAdapter", "Failed to update todo: " + response.code());
                    Toast.makeText(context, "Update failed: " + response.code(), Toast.LENGTH_SHORT).show();
                    restorePreviousState(position);
                }
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e("TodoAdapter", "Network error: " + t.getMessage());
                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();

                restorePreviousState(position);
            }
        });
    }

    private void restorePreviousState(int position) {
        if (position != RecyclerView.NO_POSITION) {
            notifyItemChanged(position);
        }
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}