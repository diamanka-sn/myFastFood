package com.univ.myfastfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class DbRestaurant extends SQLiteOpenHelper {


    public DbRestaurant(@Nullable Context context) {
        super(context, "db_restaurants.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, prenom VARCHAR(50) NOT NULL, nom VARCHAR(50) NOT NULL, telephone INTEGER NOT NULL, login VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, isAdmin boolean default(0));");
        db.execSQL("CREATE TABLE menus(id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER NOT NULl, nom VARCHAR(50) NOT NULL, description VARCHAR(250) NOT NULL, prix INTEGER NOT NULL, CONSTRAINT fk_user foreign key (user_id) REFERENCES users(id));");
        db.execSQL("CREATE TABLE commandes(id INTEGER PRIMARY KEY AUTOINCREMENT, menu_id INTEGER NOT NULL, paiement VARCHAR(50) NOT NULL, dateCommande Date NOT NULL, asPayes boolean default(0), CONSTRAINT fk_menu foreign key (menu_id) REFERENCES menus(id));");
    }

    public boolean loginUser(String login, String password) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor c = db.query("users", null, null, null, null, null, null);
            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                do {
                    String l = c.getString(4);
                    String p = c.getString(5);

                    if (login.equals(l) && password.equals(p)) {
                        return true;
                    }
                    c.moveToNext();
                } while (!c.isAfterLast());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public boolean addUser(String prenom, String nom, int telephone, String login, String password, boolean isAdmin) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("prenom", prenom);
            contentValues.put("nom", nom);
            contentValues.put("telephone", telephone);
            contentValues.put("login", login);
            contentValues.put("password", password);
            contentValues.put("isAdmin", isAdmin);

            db.insert("users", null, contentValues);
            db.close();

            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users;");
        onCreate(db);
    }
}
