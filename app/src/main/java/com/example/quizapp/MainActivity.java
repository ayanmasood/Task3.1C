package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variable Names
EditText mText;
Button mButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ids
        mText = findViewById(R.id.editTextTextPersonName);
        mButton= findViewById(R.id.button);





        //Onclick function
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mText.getText().toString();


                //Error message check to see if empty
                if (TextUtils.isEmpty(mText.getText().toString())){

                    Toast.makeText(MainActivity.this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                }
                else{
                    // condition not empty then go to new page
                    //2nd activity page
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("USER_NAME", userName);
                    startActivity(intent);
                }


            }
        });








    }



}