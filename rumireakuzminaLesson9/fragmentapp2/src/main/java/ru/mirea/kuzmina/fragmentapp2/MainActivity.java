package ru.mirea.kuzmina.fragmentapp2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            Bundle bundle = new Bundle();
            bundle.putInt("my_number_student", 16);

            BlankFragment studentFragment = new BlankFragment();
            studentFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, studentFragment)
                    .commit();

        }
    }
}