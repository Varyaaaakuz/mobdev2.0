package ru.mirea.kuzmina.resultapifragmentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private TextView textViewReceivedData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        textViewReceivedData = view.findViewById(R.id.textViewReceivedData);
        getParentFragmentManager().setFragmentResultListener("request_key",
                this, (requestKey, bundle) -> {
                    String receivedData = bundle.getString("data_key", "");
                    if (receivedData != null && !receivedData.isEmpty()) {
                        textViewReceivedData.setText(receivedData);
                    }
                });

        return view;
    }
}