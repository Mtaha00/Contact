package com.example.mvvm82;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private DAO dao;
    private LiveData<List<Contact>> contacts;

    public Repository(Application application) {
        dao = ContactDB.getInstance(application).getDao();
        contacts = dao.getAllContact();
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public void insertContact(Contact contact){
        new InsertContact(dao).execute(contact);

    }
    public void updateContact(Contact contact){
        new UpdateContact(dao).execute(contact);
    }
    public void deleteContact(Contact contact){
        new DeleteContact(dao).execute(contact);
    }

    private static class InsertContact extends AsyncTask<Contact, Void, Void> {
        private DAO dao;

        public InsertContact(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.insertContact(contacts[0]);
            return null;
        }
    }

    private static class UpdateContact extends AsyncTask<Contact,Void,Void>{
        private DAO dao;
        public UpdateContact(DAO dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.updateContact(contacts[0]);
            return null;
        }
    }

    private static class DeleteContact extends AsyncTask<Contact,Void,Void>{
        private DAO dao;
        public DeleteContact(DAO dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.deleteContact(contacts[0]);
            return null;
        }
    }


}
