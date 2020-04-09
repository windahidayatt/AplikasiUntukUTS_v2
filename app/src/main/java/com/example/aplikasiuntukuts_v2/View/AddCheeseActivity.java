package com.example.aplikasiuntukuts_v2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aplikasiuntukuts_v2.R;

public class AddCheeseActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.example.aplikasiuntukuts_v2.EXTRA_NAME";

    private Button buttonsave;
    private EditText txt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cheese);

        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.3));

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        buttonsave = (Button) findViewById(R.id.buttonsave);
        txt_name = (EditText) findViewById(R.id.txt_name);


        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_name.getText().toString().matches("")) {
                    Toast.makeText(AddCheeseActivity.this.getApplicationContext(), "Enter a name", Toast.LENGTH_LONG).show();

                } else {
                    String name = txt_name.getText().toString();

                    Intent data = new Intent();
                    data.putExtra(EXTRA_NAME, name);

                    AddCheeseActivity.this.setResult(RESULT_OK, data);
                    AddCheeseActivity.this.finish();
                }

            }
        });
    }
}
