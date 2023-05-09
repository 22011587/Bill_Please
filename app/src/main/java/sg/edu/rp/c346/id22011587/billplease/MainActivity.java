package sg.edu.rp.c346.id22011587.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    //Declare the variables
    EditText amount;
    EditText no_of_pax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView each_has_to_pay;
    Button split;
    Button reset;
    EditText discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link the field variables to UI components in layout
        amount = findViewById(R.id.amount_input);
        no_of_pax = findViewById(R.id.no_of_pax_input);
        svs = findViewById(R.id.svs_input);
        gst = findViewById(R.id.gst_input);
        totalBill = findViewById(R.id.total_bill_input);
        each_has_to_pay = findViewById(R.id.pay_input);
        split = findViewById(R.id.split_input);
        reset = findViewById(R.id.reset_input);
        discount = findViewById(R.id.discount_input);

        split.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
            if (amount.getText().toString().length() != 0 && no_of_pax.getText().toString().trim().length() != 0) {
                double updatedAmount = 0.0;
                if (!svs.isChecked() && !gst.isChecked()) {
                    updatedAmount = Double.parseDouble(amount.getText().toString());
                } else if (svs.isChecked() && gst.isChecked()) {
                    updatedAmount = Double.parseDouble(amount.getText().toString()) * 1.1;
                } else if (!svs.isChecked() && gst.isChecked()) {
                    updatedAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                } else {
                    updatedAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                }

                //Calculating the Discount
                if (discount.getText().toString().trim().length() != 0) {
                    updatedAmount *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                }

                totalBill.setText("Total Bill: $" + String.format("%.2f", updatedAmount));
                int no_of_people = Integer.parseInt(no_of_pax.getText().toString());
                if (no_of_people != 1) {
                    each_has_to_pay.setText("Each person has to pay: $" + String.format("%.2f", updatedAmount / no_of_people));
                } else
                    each_has_to_pay.setText("Each person has to pay: $" + updatedAmount);
            }
        }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                amount.setText("");
                no_of_pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        })
    ;};

    }