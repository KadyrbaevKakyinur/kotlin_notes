package com.example.kotlin_notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter adapter;

    Button add;
    Button sort;
    EditText editText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       add = view.findViewById(R.id.add);
       recyclerView = view.findViewById(R.id.recycler);

        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        List<Note> list= new ArrayList<>();
        list.add(new Note("","title2","description2",""));
        list.add(new Note("","title3","",""));
        list.add(new Note("","title4","descrip",""));
        list.add(new Note("","title5","description",""));
        adapter.setList(list);


       add.setOnClickListener(v->{
           requireActivity().getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.main_container,new AddFragment())
                   .commit();
       });

       sort= view.findViewById(R.id.sort);
       sort.setOnClickListener(v->{

           String title= editText.getText().toString();
           AddFragment addFragment = new AddFragment();
           Bundle bundle = new Bundle();
           bundle.putString("text",title);
           addFragment.setArguments(bundle);
           requireActivity().getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.main_container,addFragment)
                   .commit();
       });



    }


}