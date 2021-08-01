package com.example.mvvm82;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ContactViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public ContactViewModelFactory(Application application){
        this.application=application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ContactViewModel(application);
    }
}
