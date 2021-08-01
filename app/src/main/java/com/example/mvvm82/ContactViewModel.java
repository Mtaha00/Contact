package com.example.mvvm82;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Contact>> contacts;



    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        contacts=repository.getContacts();

    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }
    public void insertContact(Contact contact){
        repository.insertContact(contact);
    }
    public void updateContact(Contact contact){
        repository.updateContact(contact);
    }
    public void deleteContact(Contact contact){
        repository.deleteContact(contact);
    }
}
