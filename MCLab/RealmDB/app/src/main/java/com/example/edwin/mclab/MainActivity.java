package com.example.edwin.mclab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {
    public static String Appid = "myapplication-ipmzh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(MainActivity.this, RegisterActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(activityChangeIntent);
            }
        });

        Realm.init(this);
        final App app = new App(new AppConfiguration.Builder(Appid).build());

//        Anonymous Login
//        app.loginAsync(Credentials.anonymous(), new App.Callback<User>(){
//            @Override
//            public void onResult(App.Result<User> result){
//                if(result.isSuccess()){
//                    Log.v("User","Logged in anonymously");
//                }
//                else {
//                    Log.v("User","Failed to login");
//                }
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.editText);
                EditText text1 = (EditText) findViewById(R.id.editText1);
                EditText text2 = (EditText) findViewById(R.id.editText2);
                final String name = text.getText().toString();
                String email = text1.getText().toString();
                String password = text2.getText().toString();
                Credentials credentials = Credentials.emailPassword(email, password);

                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if (result.isSuccess()) {
                            Log.v("User", "Logged in");
                            setContentView(R.layout.activity_success);
                            Toast.makeText(getApplicationContext(), name + " has successfully logged in!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.v("User", "Failed to login");
                            Toast.makeText(getApplicationContext(), " Failed to log in :{", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}


