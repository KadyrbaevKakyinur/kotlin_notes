package com.example.kotlin_notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {

    private String imageUri;
    EditText title;
    EditText date;
    EditText desc;

    Button add;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add = view.findViewById(R.id.add);
        title = view.findViewById(R.id.edit_title);
        date = view.findViewById(R.id.edit_date);
        desc = view.findViewById(R.id.edit_description);


        add.setOnClickListener(v -> {
            MainFragment mainFragment = new MainFragment();
            Note note = new Note(imageUri,
                    title.getText().toString(),
                    desc.getText().toString(),
                    date.getText().toString());


            Bundle bundle = new Bundle();
            bundle.putSerializable("model", note);

            mainFragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, mainFragment)
                    .commit();
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data.getDataString();
    }
}