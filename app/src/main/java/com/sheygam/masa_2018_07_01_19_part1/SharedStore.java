package com.sheygam.masa_2018_07_01_19_part1;

import android.content.Context;

public class SharedStore implements Store{
    private Context context;

    public SharedStore(Context context) {
        this.context = context;
    }

    @Override
    public void token(String token) {
        context.getSharedPreferences("AUTH",Context.MODE_PRIVATE)
                .edit()
                .putString("TOKEN",token)
                .apply();
    }

    @Override
    public String token() {
        return context.getSharedPreferences("AUTH",Context.MODE_PRIVATE)
                .getString("TOKEN",null);
    }
}
