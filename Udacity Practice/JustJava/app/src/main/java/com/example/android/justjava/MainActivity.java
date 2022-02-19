package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    static  int quantity=0;
    static int price=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        CheckBox check = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        EditText inpText = (EditText) findViewById(R.id.description_view);
        String name = inpText.getText().toString();
        boolean checkBox = check.isChecked();
        int cost=0;
        if(checkBox)
            cost = quantity*(price+5);
        else
            cost = quantity*price;
        String output = "Name : "+name +"\nQuantity : "+quantity+"\nCost : "+cost;
        priceTextView.setText(output);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "edwinvincent.evt@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mail sent from edu's app.");
        intent.putExtra(Intent.EXTRA_TEXT, output);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        Log.i("MainActivity.java", (String) priceTextView.getText());
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
        displayPrice(quantity*price);
    }

    public void decrement(View view) {
        if(quantity>0) {
            quantity--;
            display(quantity);
            displayPrice(quantity * price);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}