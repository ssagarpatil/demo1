package com.spark.fees;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class fees extends AppCompatActivity {

    TextView textViewSelectFee;
    AutoCompleteTextView editTextFee;
    EditText editTextAmount;
    Button buttonAdd;
    RecyclerView recyclerView;

    List<FeesModel> allFees = new ArrayList<>();
    List<FeesModel> selectedFees = new ArrayList<>();
    FeesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);

        textViewSelectFee = findViewById(R.id.textViewSelectFee);
        editTextFee = findViewById(R.id.editTextFee);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerView = findViewById(R.id.feesRecyclerView);

        // Fetch the fee names from strings.xml
        String[] feeNamesArray = getResources().getStringArray(R.array.fee_names);

//        // Add sample fees with amounts from string resources
//        allFees.add(new FeesModel(feeNamesArray[0], 2000));
//        allFees.add(new FeesModel(feeNamesArray[1], 300));
//        allFees.add(new FeesModel(feeNamesArray[2], 800));
//        allFees.add(new FeesModel(feeNamesArray[3], 400));

        adapter = new FeesAdapter(selectedFees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Fee names list for AutoComplete
        ArrayAdapter<String> feeNameAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                feeNamesArray
        );
        editTextFee.setAdapter(feeNameAdapter);

        // Auto-fill amount when a known fee is selected
        editTextFee.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = parent.getItemAtPosition(position).toString();
            // Reset the amount field when a fee is selected
            editTextAmount.setText(""); // Remove any pre-existing amount
        });

        // Add fee to list
        buttonAdd.setOnClickListener(v -> {
            String feeName = editTextFee.getText().toString().trim();
            String amountStr = editTextAmount.getText().toString().trim();

            if (feeName.isEmpty() || amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter both fee name and amount", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int amount = Integer.parseInt(amountStr);
                selectedFees.add(new FeesModel(feeName, amount));
                adapter.notifyItemInserted(selectedFees.size() - 1);
                editTextFee.setText("");
                editTextAmount.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
            }
        });

        textViewSelectFee.setOnClickListener(v -> showFeeDialog());
    }

    private void showFeeDialog() {
        String[] feeNames = getResources().getStringArray(R.array.fee_names);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a Fee");
        builder.setItems(feeNames, (dialog, which) -> {
            editTextFee.setText(feeNames[which]);
            // No need to set the amount here, it's done manually by the user
        });
        builder.show();
    }
}
