package com.example.tp.SharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tp.Model.Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




    public class SharedPreference {
        public static final String Name = "Myitems";
        public static final String Items = "Items";

        public SharedPreference() {
            super();
        }

        // This four methods are used for maintaining favorites.
        public void saveItem(Context context, List<Item> items) {
            SharedPreferences settings;
            SharedPreferences.Editor editor;

            settings = context.getSharedPreferences(Name,
                    Context.MODE_PRIVATE);
            editor = settings.edit();

            Gson gson = new Gson();
            String jsonItems = gson.toJson(items);

            editor.putString(Items, jsonItems);

            editor.commit();
        }

        public void addItem(Context context, Item item) {
            List<Item> items = getItems(context);
            if (items== null)
                items = new ArrayList<Item>();
            items.add(item);
            saveItem(context, items);
        }

        public void removeItem(Context context,int position) {
            ArrayList<Item> items = getItems(context);
            if (items != null) {
                items.remove(position);
                saveItem(context, items);
            }
        }

        public ArrayList<Item> getItems(Context context) {
            SharedPreferences settings;
            List<Item> items;

            settings = context.getSharedPreferences(Name,
                    Context.MODE_PRIVATE);

            if (settings.contains(Items)) {
                String jsonFavorites = settings.getString(Items, null);
                Gson gson = new Gson();
                Item[] allitems = gson.fromJson(jsonFavorites,
                        Item[].class);

                items = Arrays.asList(allitems);
                items = new ArrayList<Item>(items);
            } else
                return null;

            return (ArrayList<Item>) items;
        }
    }


