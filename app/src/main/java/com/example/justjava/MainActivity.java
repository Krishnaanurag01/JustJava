package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int numberOfCoffees=1;
//    int price=5;
    public void Increment(View view){
        if(numberOfCoffees==100){
            Toast.makeText(this,"Can't buy more than 100 !",Toast.LENGTH_LONG).show();
            return;
        }
        numberOfCoffees+=1;
        display(numberOfCoffees);
    }
    public void Decrement(View view){
        if(numberOfCoffees==1)return;
        else if (numberOfCoffees>1){
        numberOfCoffees-=1;
            display(numberOfCoffees);
        }
    }

    public void submitOrder(View view) {
        CheckBox whipCream = (CheckBox) findViewById(R.id.whip_check);
        CheckBox  Chocolate =(CheckBox) findViewById(R.id.Chocolate);
        EditText inputname = (EditText) findViewById(R.id.name_id);
        String sum="Name :"+inputname.getText().toString()+"\n"+"Want Whip Cream: "+whipCream.isChecked()+"\n"+"Want Chocolate ?"+Chocolate.isChecked()+"\n"+SubmitORDERsummary();
        Intent intent=new Intent(Intent.ACTION_SENDTO);
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com"});
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order "+ inputname.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,sum);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public String SubmitORDERsummary(){
        int total=numberOfCoffees*5;
        CheckBox whipCream = (CheckBox) findViewById(R.id.whip_check);
        CheckBox  Chocolate =(CheckBox) findViewById(R.id.Chocolate);
        Boolean getcream=whipCream.isChecked();
        Boolean getchoco=Chocolate.isChecked();
        if(getchoco == true && getcream==true){
            total=numberOfCoffees*5 + numberOfCoffees*1 +numberOfCoffees*2;
        }
        else if (getchoco==true){
            total=numberOfCoffees*5 + numberOfCoffees*2;
        }
        else if (getcream==true){
            total=numberOfCoffees*5 + numberOfCoffees*1;
        }
            return "Name: Captain Kunal" + "\nQuantity: " + numberOfCoffees + "\nTotal :$" + total + "\nThank You :)";
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
//    private void displayPrice(int number) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        priceTextView.setText(message);
//    }
}