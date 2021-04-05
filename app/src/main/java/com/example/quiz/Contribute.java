package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Contribute extends AppCompatActivity {


    DBHelper dbh;
    Button b;
    EditText ques,oa,ob,oc,ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);
        b = (Button)findViewById(R.id.contributton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques = findViewById(R.id.q);
                oa = findViewById(R.id.a);
                ob = findViewById(R.id.b);
                oc = findViewById(R.id.c);
                ans = findViewById(R.id.ans);
                Question q = new Question(ques.getText().toString(),oa.getText().toString(),ob.getText().toString(),oc.getText().toString(),ans.getText().toString(),"");
                dbh.addQuestionToDB(q);
                Toast.makeText(Contribute.this,"Added to DB", Toast.LENGTH_LONG).show();
            }
        });
    }
}