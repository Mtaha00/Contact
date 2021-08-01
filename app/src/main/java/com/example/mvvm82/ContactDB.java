package com.example.mvvm82;

import android.app.PendingIntent;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactDB extends RoomDatabase {

    public abstract DAO getDao();

    private static ContactDB instance;

    public static synchronized ContactDB getInstance(Context context){
        if (instance==null) {
            instance= Room.databaseBuilder(context.getApplicationContext(),ContactDB.class,"contact")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new InsertFirstValue(instance).execute(new Contact(0,"taha","gouyande","09124366419"));
            super.onCreate(db);

        }
    };
    private static class InsertFirstValue extends AsyncTask<Contact,Void,Void>{
        private DAO dao;

        public InsertFirstValue(ContactDB contactDB){
            dao=contactDB.getDao();
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.insertContact(contacts[0]);
            return null;
        }
    }


}
