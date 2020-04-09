package com.example.aplikasiuntukuts_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasiuntukuts_v2.Model.Cheese;
import com.example.aplikasiuntukuts_v2.View.AddCheeseActivity;
import com.example.aplikasiuntukuts_v2.View.CheeseAdapter;
import com.example.aplikasiuntukuts_v2.ViewModel.CheeseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CHEESE_REQUEST = 1;

    private CheeseViewModel cheeseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddCheese = findViewById(R.id.button_add_name);
        buttonAddCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCheeseActivity.class);
                startActivityForResult(intent, ADD_CHEESE_REQUEST);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CheeseAdapter adapter = new CheeseAdapter();
        recyclerView.setAdapter(adapter);

        cheeseViewModel= ViewModelProviders.of(this).get(CheeseViewModel.class);
        cheeseViewModel.getAllCheeses().observe(this, new Observer<List<Cheese>>() {
            @Override
            public void onChanged(List<Cheese> cheeses) {
                //Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT);
                adapter.setCheeses(cheeses);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_CHEESE_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddCheeseActivity.EXTRA_NAME);

            Cheese cheese = new Cheese(name);
            cheeseViewModel.insert(cheese);

            Toast.makeText(this, "Cheese Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Cheese Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
