package com.carlospienovi.sandwichshop;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by carlos.pienovi on 02/02/2015.
 */
public class Sandwich implements Parcelable {

    private String bread;
    private boolean ketchup;
    private boolean mustard;
    private boolean mayonnaise;
    private boolean tomato;
    private boolean lettuce;
    private boolean cheese;
    private boolean egg;
    private boolean bacon;
    private boolean olives;
    private boolean onion;

    public Sandwich() {
    }

    public Sandwich(String bread, Boolean ketchup, Boolean mustard, Boolean mayonnaise,
                    Boolean tomato, Boolean lettuce, Boolean cheese, Boolean egg, Boolean bacon,
                    Boolean olives, Boolean onion) {
        this.setBread(bread);
        this.setKetchup(ketchup);
        this.setMustard(mustard);
        this.setMayonnaise(mayonnaise);
        this.setTomato(tomato);
        this.setLettuce(lettuce);
        this.setCheese(cheese);
        this.setEgg(egg);
        this.setBacon(bacon);
        this.setOlives(olives);
        this.setOnion(onion);
    }

    public Sandwich(Parcel in) {
        boolean[] data = new boolean[10];

        this.setBread(in.readString());
        in.readBooleanArray(data);
        this.setKetchup(data[0]);
        this.setMustard(data[1]);
        this.setMayonnaise(data[2]);
        this.setTomato(data[3]);
        this.setLettuce(data[4]);
        this.setCheese(data[5]);
        this.setEgg(data[6]);
        this.setBacon(data[7]);
        this.setOlives(data[8]);
        this.setOnion(data[9]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeString(this.getBread());
        destination.writeBooleanArray(
                new boolean[]{
                        this.getKetchup(),
                        this.getMustard(),
                        this.getMayonnaise(),
                        this.getTomato(),
                        this.getLettuce(),
                        this.getCheese(),
                        this.getEgg(),
                        this.getBacon(),
                        this.getOlives(),
                        this.getOnion()
                });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Sandwich createFromParcel(Parcel in) {
            return new Sandwich(in);
        }

        public Sandwich[] newArray(int size) {
            return new Sandwich[size];
        }
    };

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public Boolean getKetchup() {
        return ketchup;
    }

    public void setKetchup(Boolean ketchup) {
        this.ketchup = ketchup;
    }

    public Boolean getMustard() {
        return mustard;
    }

    public void setMustard(Boolean mustard) {
        this.mustard = mustard;
    }

    public Boolean getMayonnaise() {
        return mayonnaise;
    }

    public void setMayonnaise(Boolean mayonnaise) {
        this.mayonnaise = mayonnaise;
    }

    public Boolean getTomato() {
        return tomato;
    }

    public void setTomato(Boolean tomato) {
        this.tomato = tomato;
    }

    public Boolean getLettuce() {
        return lettuce;
    }

    public void setLettuce(Boolean lettuce) {
        this.lettuce = lettuce;
    }

    public Boolean getCheese() {
        return cheese;
    }

    public void setCheese(Boolean cheese) {
        this.cheese = cheese;
    }

    public Boolean getEgg() {
        return egg;
    }

    public void setEgg(Boolean egg) {
        this.egg = egg;
    }

    public Boolean getBacon() {
        return bacon;
    }

    public void setBacon(Boolean bacon) {
        this.bacon = bacon;
    }

    public Boolean getOlives() {
        return olives;
    }

    public void setOlives(Boolean olives) {
        this.olives = olives;
    }

    public Boolean getOnion() {
        return onion;
    }

    public void setOnion(Boolean onion) {
        this.onion = onion;
    }

    public ArrayList<String> toppingsAsString(Context context) {
        ArrayList<String> toppings = new ArrayList<String>();
        if (ketchup) {
            toppings.add(context.getResources().getString(R.string.checkbox_ketchup));
        }
        if (mustard) {
            toppings.add(context.getResources().getString(R.string.checkbox_mustad));
        }
        if (mayonnaise) {
            toppings.add(context.getResources().getString(R.string.checkbox_mayonnaise));
        }
        if (tomato) {
            toppings.add(context.getResources().getString(R.string.checkbox_tomato));
        }
        if (lettuce) {
            toppings.add(context.getResources().getString(R.string.checkbox_lettuce));
        }
        if (cheese) {
            toppings.add(context.getResources().getString(R.string.checkbox_cheese));
        }
        if (egg) {
            toppings.add(context.getResources().getString(R.string.checkbox_egg));
        }
        if (bacon) {
            toppings.add(context.getResources().getString(R.string.checkbox_bacon));
        }
        if (olives) {
            toppings.add(context.getResources().getString(R.string.checkbox_olives));
        }
        if (onion) {
            toppings.add(context.getResources().getString(R.string.checkbox_onion));
        }
        return toppings;
    }
}
