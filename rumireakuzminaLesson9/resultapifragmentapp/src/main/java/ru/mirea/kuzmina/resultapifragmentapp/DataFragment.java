package ru.mirea.kuzmina.resultapifragmentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DataFragment extends Fragment {

    private EditText editTextInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextInfo = view.findViewById(R.id.editTextInfo);
        Button buttonSend = view.findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToBottomSheet();
            }
        });
    }
    private void sendDataToBottomSheet() {
        String text = editTextInfo.getText().toString().trim();
        if (!text.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putString("data_key", text);
            getParentFragmentManager().setFragmentResult("request_key", bundle);

            BottomSheetFragment bottomSheet = new BottomSheetFragment();
            bottomSheet.show(getParentFragmentManager(), "BottomSheetFragment");
            editTextInfo.setText("");
        } else {
            editTextInfo.setError("Введите текст");
        }
    }
}