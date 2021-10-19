package com.example.mortgagecalculator_one;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Spinner rateSpinner, periodSpinner, frequencySpinner;
    Button calculate;
    int yearLength, frequencyLength;
    private EditText amountMortgage;
    private TextView total;
    double inputRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set(initialize) variables for spinners in form in code
        rateSpinner = findViewById(R.id.rate_choices);
        // Create a list of items for the spinners
        String[] rates = new String[]{"3 Year Fixed Rate 2.24%", "6 Month Convertible 3.04%", "10 Year Closed 5.6%"};
        // Adapter
        ArrayAdapter<String> rate_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rates);
        rateSpinner.setAdapter(rate_adapter);

        // Program spinner to respond to user selections
        rateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                String selectedRate = adapterView.getItemAtPosition(i).toString();
                switch (selectedRate){
                    case "3 Year Fixed Rate 2.24%":
                        inputRate = 0.0224;
                        break;

                    case "6 Month Convertible 3.04%":
                        inputRate = 0.0304;
                        break;

                    case "10 Year Closed 5.6%":
                        inputRate = 0.056;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // first choice is default
                inputRate = 0.0224;
            }
        });

        periodSpinner = findViewById(R.id.period_choices);
        String[] period = new String[]{"5 Years", "10 Years", "15 Years", "20 Years"};
        ArrayAdapter<String> period_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, period);
        periodSpinner.setAdapter(period_adapter);
        periodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                String selectedRate = adapterView.getItemAtPosition(i).toString();
                switch (selectedRate){
                    case "5 Years":
                        yearLength = 5;
                        break;
                    case "10 Years":
                        yearLength = 10;
                        break;
                    case "15 Years":
                        yearLength = 15;
                        break;
                    case "20 Years":
                        yearLength = 20;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                yearLength = 5;
            }
        });

        frequencySpinner = findViewById(R.id.frequency_choices);
        String[] frequency = new String[]{"Monthly"};
        ArrayAdapter<String> frequency_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, frequency);
        frequencySpinner.setAdapter(frequency_adapter);
        frequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                String selectedRate = adapterView.getItemAtPosition(i).toString();
                switch (selectedRate){
                    case "Monthly":
                        frequencyLength = 12;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                frequencyLength = 12;
            }
        });

        calculate = findViewById(R.id.button_calculate);
        calculate.setOnClickListener(this);

    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_calculate:
                int i = frequencyLength * yearLength;
                double j = inputRate / 12;

                // find input amount
                amountMortgage = findViewById(R.id.inputText_amount);
                String strAmount = amountMortgage.getText().toString();
                // convert value to integer for successful calculation
                int amount = Integer.parseInt(amountMortgage.getText().toString());

                double totalMonthly = amount * j *Math.pow(1 + j, i)/(Math.pow(1 + j, i) - 1);
                String totalToPrint = new DecimalFormat("#.0#").format(totalMonthly);

                total = findViewById(R.id.output);
                total.setText("Quoted value for monthly payment: $" + (totalToPrint));
                break;
        }
    }
}