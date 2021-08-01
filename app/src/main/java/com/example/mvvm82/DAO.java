package com.example.mvvm82;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    public void insertContact(Contact... contacts);

    @Update
    public void updateContact(Contact... contacts);

    @Delete
    public void deleteContact(Contact... contacts);

    @Query("SELECT * FROM contact")
    public LiveData<List<Contact>> getAllContact();



}
