package com.example.aplikasiuntukuts_v2.Model;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CheeseDao {


    @Insert
    long insert(Cheese cheese);

    @Update
    int update(Cheese cheese);

    @Delete
    void delete(Cheese cheese);

    @Query("DELETE FROM cheese_table")
    void deleteAllCheeses();

    @Query("SELECT * FROM cheese_table ORDER BY name ASC")
    LiveData<List<Cheese>> getAllTCheeses();

    @Query("SELECT * FROM cheese_table ORDER BY name ASC")
    Cursor selectAll();

    @Query("SELECT * FROM cheese_table WHERE " + Cheese.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    @Query("DELETE FROM cheese_table WHERE " + Cheese.COLUMN_ID + " = :id")
    int deleteById(long id);

    @Query("SELECT COUNT(*) FROM " + "cheese_table")
    int count();
}
