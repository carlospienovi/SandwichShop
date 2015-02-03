package com.carlospienovi.sandwichshop;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class ConfirmationActivity extends ActionBarActivity {

    TextView mTextConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        mTextConfirmation = (TextView) findViewById(R.id.text_confirmation);
        ArrayList<Sandwich> sandwiches = getIntent().getExtras()
                .getParcelableArrayList(OrderFormActivity.SANDWICH_LIST);
        setOrderSummary(sandwiches);
    }

    private void setOrderSummary(ArrayList<Sandwich> sandwiches) {
        if (sandwiches.isEmpty()) {
            mTextConfirmation.setText(getString(R.string.no_sandwiches));
        } else {
            String text = "";
            for (Sandwich s : sandwiches) {
                String breadType = s.getBread();
                ArrayList<String> toppings = s.toppingsAsString(this);
                text = text + getResources().getString(R.string.text_sumary_first)
                        + " " + breadType + " " + getResources().getString(R.string.bread);
                if (!toppings.isEmpty()) {
                    for (int i = 0; i < toppings.size(); i++) {
                        text = text + ", " + toppings.get(i);
                    }
                }
                text = text + " " + getResources().getString(R.string.prepared) + ".\n\n";
            }
            mTextConfirmation.setText(text);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirmation, menu);
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
