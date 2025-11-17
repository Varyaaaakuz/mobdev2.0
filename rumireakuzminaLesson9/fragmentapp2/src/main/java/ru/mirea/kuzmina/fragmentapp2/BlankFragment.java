package ru.mirea.kuzmina.fragmentapp2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            int studentNumber = getArguments().getInt("my_number_student", 0);

            Log.d("BlankFragment", "Номер студента по списку: " + studentNumber);
            TextView textView = view.findViewById(R.id.textViewStudentNumber);
            textView.setText("Мой номер по списку: " + studentNumber);
        }
    }
}