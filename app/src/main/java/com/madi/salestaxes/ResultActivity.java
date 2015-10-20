package com.madi.salestaxes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.madi.salestaxes.receipt.Receipt;
import com.madi.salestaxes.receipt.ReceiptItems;

import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView txtResult = (TextView) findViewById(R.id.txtResult);
        Intent intent = getIntent();
        Bundle bundle;
        if (intent.hasExtra(MainActivity.RECEIPT)) {
            bundle = getIntent().getExtras();
            if (null != bundle.get(MainActivity.RECEIPT)) {
                String receipt = (String) bundle.get(MainActivity.RECEIPT);
                txtResult.setText(receipt);

            }
        }
    }

}
