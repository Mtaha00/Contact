package com.example.mvvm82;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class Contact extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name ="contact_name")
    private String name;

    @ColumnInfo(name ="contact_lastName")
    private String lastName;

    @ColumnInfo(name ="contact_number")
    private String number;

    public Contact(int id, String name, String lastName, String number) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.number = number;
    }

    @Ignore
    public Contact() {
    }

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;

        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;

        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;

        notifyPropertyChanged(BR.number);
    }

    public static Contact newObject(){
        return new Contact();
    }
}
