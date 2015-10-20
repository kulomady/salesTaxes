package com.madi.salestaxes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.madi.salestaxes.receipt.ReceiptItems;
import com.madi.salestaxes.transactionHelper.Client;
import com.madi.salestaxes.transactionHelper.InputData;
import com.madi.salestaxes.transactionHelper.Receipt;
import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<EditText> editTextArrayList = new ArrayList<>();
    private boolean isBtnCreateAlreadyClicked = false;
    public static final String RECEIPT = "sales_taxes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initLayout();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCreate:

                EditText edtRowCount = (EditText) findViewById(R.id.edtRowCount);
                if (isBtnCreateAlreadyClicked) {
                    Snackbar.make(v, "You can only create once", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    int rowCount = Integer.parseInt(edtRowCount.getText().toString());
                    generateEditText(rowCount);
                    isBtnCreateAlreadyClicked = true;
                }
                break;
            case R.id.fab:
                ArrayList<String> usersInput = new ArrayList<>();
                for (EditText editText : editTextArrayList) {
                    usersInput.add(editText.getText().toString());
                }
                if (usersInput.size() > 0) {
                    doShopping(usersInput);
                } else {
                    Snackbar.make(v, "You have to input data", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }
                break;
        }
    }

    private void initLayout() {
        Button btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void doShopping(ArrayList<String> usersInput) {
        String customerName = "CustomerA";
        String cashierName = "CashierA";
        ArrayList<InputData> inputDatas = new ArrayList<>();
        int quantity;
        double cost;

        for (String strValue : usersInput) {
            strValue = strValue.replaceAll("\\s+$", "");
            String[] array = strValue.split(" ");

            // Assuming the line should start and end with a number
            if (isNumber(array[0]) && isNumber(array[array.length - 1])) {
                quantity = Integer.valueOf(array[0]).intValue();
                cost = new Double(array[array.length - 1]).doubleValue();
            } else {
                quantity = 0;
                cost = 0;

            }

            // create an array of keywords from the product description
            String[] product = Arrays.copyOfRange(array, 1, array.length - 2);
            String productDescription = "";
            for (int index = 0; index < product.length; index++) {
                productDescription += " " + product[index].toLowerCase();
            }
            boolean isImported = productDescription.contains("imported")
                    || productDescription.contains("Imported");
            String productName = productDescription.replaceAll("[i,I]mported", "");
            inputDatas.add(new InputData(quantity, productName, isImported, cost));
        }
        printReceipt(customerName, cashierName, inputDatas);
    }

    private void printReceipt(String customerName, String cashierName, ArrayList<InputData> inputDatas) {
        Client client;
        Receipt receipt;
        client = new Client(inputDatas);

        try {
            receipt = client.performTransaction(customerName, cashierName);

            StringBuilder strParamsValue = new StringBuilder();
            for (ReceiptItems item : receipt.getReceiptItemsList()) {
                System.out.print(item.getQuantity());
                strParamsValue.append(item.getQuantity());
                if (item.isImported())
                    strParamsValue.append(" Imported");
                strParamsValue.append(" " + item.getName() + " : " + item.getTotalCost() + "\n");

            }

            strParamsValue.append("\nSales Taxes:\t").append(receipt.getGrandSalesTaxesTotal()).append("\n");
            strParamsValue.append("Total:\t").append(receipt.getGrandOverallTotal());
            receipt.emptyCardOnReceipt();
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra(RECEIPT, strParamsValue.toString());
            MainActivity.this.startActivity(intent);

        } catch (Exception invalidInputException) {
            Log.e(TAG, "Please check your input for correct format");
        }
    }


    /**
     * Helper method to validate string
     *
     * @param inputString string that needs validation
     */
    private boolean isNumber(String inputString) {
        boolean isNumber;
        try {
            isNumber = (Double.parseDouble(inputString)) > 0;
        } catch (NumberFormatException e) {
            isNumber = false;
        }
        return isNumber;
    }

    /**
     * Helper method to generate EditText
     *
     * @param rowCount int of how many editText will be generate
     */
    private void generateEditText(int rowCount) {
        LinearLayout linWrapperEditText = (LinearLayout) findViewById(R.id.linWrapper);
        for (int index = 0; index < rowCount; index++) {
            linWrapperEditText.addView(createEditText(String.valueOf(index + 1)));
        }
    }

    /**
     * Helper method to Create EditText
     *
     * @param hint String hint for EditText
     */
    private EditText createEditText(String hint) {
        EditText editText = new EditText(this);
        editText.setId(Integer.valueOf(hint));

        editText.setHint("row " + hint);
        editTextArrayList.add(editText);
        return editText;
    }
}
