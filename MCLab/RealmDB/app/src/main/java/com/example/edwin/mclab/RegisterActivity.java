package com.example.edwin.mclab;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String Appid = "myapplication-ipmzh";
        final App app = new App(new AppConfiguration.Builder(Appid).build());
        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent( RegisterActivity.this, MainActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                RegisterActivity.this.startActivity(activityChangeIntent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.editText);
                EditText text1 = (EditText) findViewById(R.id.editText1);
                EditText text2 = (EditText) findViewById(R.id.editText2);
                final String name = text.getText().toString();
                final String email = text1.getText().toString();
                final String password = text2.getText().toString();
                Credentials credentials = Credentials.emailPassword(email, password);

                app.getEmailPassword().registerUserAsync(email, password, it ->{
                    if (it.isSuccess()) {
                        Toast.makeText(getApplicationContext(), name + " has registered successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), " Registration failed :{", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
