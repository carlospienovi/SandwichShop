package com.carlospienovi.sandwichshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class SandwichCountActivity extends ActionBarActivity {

    EditText mSandwichCount;
    Button mToOrderActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich_count);
        editTextSandwichCount();
        toOrderButton();
    }

    private void toOrderButton() {
        mToOrderActivity = (Button) findViewById(R.id.button_to_order);
        mToOrderActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sandwichAmount = Integer.parseInt(mSandwichCount.getText().toString());
                if (sandwichAmount <= 0) {
                    toConfirmationScreen();
                } else {
                    toNextSandwich();
                }
            }
        });
    }

    private void toNextSandwich() {
        Intent i = new Intent(this, OrderFormActivity.class);
        i.putExtra(OrderFormActivity.SANDWICH_COUNT, Integer.parseInt(mSandwichCount.getText().toString()));
        i.putExtra(OrderFormActivity.SANDWICH_LIST, new ArrayList<Sandwich>());
        startActivity(i);
    }

    private void toConfirmationScreen() {
        Intent i = new Intent(this, ConfirmationActivity.class);
        i.putExtra(OrderFormActivity.SANDWICH_LIST, new ArrayList<Sandwich>());
        startActivity(i);
    }

    private void editTextSandwichCount() {
        mSandwichCount = (EditText) findViewById(R.id.sandwich_count);
        mSandwichCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mToOrderActivity.setEnabled(!TextUtils.isEmpty(s));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sandwich_count, menu);
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
}
