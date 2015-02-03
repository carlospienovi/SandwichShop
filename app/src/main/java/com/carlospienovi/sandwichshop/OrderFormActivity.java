package com.carlospienovi.sandwichshop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class OrderFormActivity extends ActionBarActivity {

    public static final String SANDWICH_COUNT = "SANDWICH_COUNT";
    public static final String SANDWICH_LIST = "SANDWICH_LIST";

    Button mButtonPlaceOrder;
    RadioGroup mBreadOptions;
    CheckBox mMustard, mMayonnaise, mTomato, mLettuce, mCheese, mEgg, mBacon, mOlives, mOnion;
    ArrayList<CheckBox> extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        prepareButtonPlaceOrder();
    }

    private void initializeComponents() {
        mBreadOptions = (RadioGroup) findViewById(R.id.radio_group_bread_options);
        extras = new ArrayList<CheckBox>();
        mButtonPlaceOrder = (Button) findViewById(R.id.button_place_order);
        mMustard = (CheckBox) findViewById(R.id.checkbox_mustard);
        mMayonnaise = (CheckBox) findViewById(R.id.checkbox_mayonnaise);
        mTomato = (CheckBox) findViewById(R.id.checkbox_tomato);
        mLettuce = (CheckBox) findViewById(R.id.checkbox_lettuce);
        mCheese = (CheckBox) findViewById(R.id.checkbox_cheese);
        mEgg = (CheckBox) findViewById(R.id.checkbox_egg);
        mBacon = (CheckBox) findViewById(R.id.checkbox_bacon);
        mOlives = (CheckBox) findViewById(R.id.checkbox_olives);
        mOnion = (CheckBox) findViewById(R.id.checkbox_onion);
        extras.add(mMustard);
        extras.add(mMayonnaise);
        extras.add(mTomato);
        extras.add(mLettuce);
        extras.add(mCheese);
        extras.add(mEgg);
        extras.add(mBacon);
        extras.add(mOlives);
        extras.add(mOnion);
    }

    private void prepareButtonPlaceOrder() {
        mButtonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sandwichesLeft = getIntent().getExtras().getInt(SANDWICH_COUNT) - 1;
                if (sandwichesLeft <= 0) {
                    toConfirmationScreen();
                } else {
                    toNextSandwich(sandwichesLeft);
                }
            }
        });
    }

    private void toNextSandwich(int sandwichesLeft) {
        Intent i = new Intent(this, OrderFormActivity.class);
        i.putExtra(SANDWICH_COUNT, sandwichesLeft);
        i.putExtra(SANDWICH_LIST, addSandwichToList(getSandwichFromOptions()));
        startActivity(i);
    }

    private void toConfirmationScreen() {
        Intent i = new Intent(this, ConfirmationActivity.class);
        i.putExtra(SANDWICH_LIST, addSandwichToList(getSandwichFromOptions()));
        startActivity(i);
    }

    private String getBreadType(int radioButtonId) {
        switch (radioButtonId) {
            case R.id.button_bread_rye:
                return getResources().getString(R.string.button_bread_rye);
            case R.id.button_bread_wheat:
                return getResources().getString(R.string.button_bread_wheat);
            case R.id.button_bread_white:
                return getResources().getString(R.string.button_bread_white);
        }
        return getResources().getString(R.string.no_bread);
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

    private Sandwich getSandwichFromOptions() {
        Sandwich sandwich = new Sandwich();
        sandwich.setBread(getBreadType(mBreadOptions.getCheckedRadioButtonId()));
        for (CheckBox c : extras) {
            if (c.isChecked()) {
                switch (c.getId()) {
                    case R.id.checkbox_bacon:
                        sandwich.setBacon(true);
                        break;
                    case R.id.checkbox_cheese:
                        sandwich.setCheese(true);
                        break;
                    case R.id.checkbox_egg:
                        sandwich.setEgg(true);
                        break;
                    case R.id.checkbox_ketchup:
                        sandwich.setKetchup(true);
                        break;
                    case R.id.checkbox_lettuce:
                        sandwich.setLettuce(true);
                        break;
                    case R.id.checkbox_mayonnaise:
                        sandwich.setMayonnaise(true);
                        break;
                    case R.id.checkbox_mustard:
                        sandwich.setMustard(true);
                        break;
                    case R.id.checkbox_olives:
                        sandwich.setOlives(true);
                        break;
                    case R.id.checkbox_onion:
                        sandwich.setOnion(true);
                        break;
                    case R.id.checkbox_tomato:
                        sandwich.setTomato(true);
                        break;
                }
            }
        }
        return sandwich;
    }

    private ArrayList<Sandwich> addSandwichToList(Sandwich sandwich) {
        ArrayList<Sandwich> actual = getIntent().getExtras().getParcelableArrayList(SANDWICH_LIST);
        actual.add(sandwich);
        return actual;
    }
}
