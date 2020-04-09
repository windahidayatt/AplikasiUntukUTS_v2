package com.example.aplikasiuntukuts_v2.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Cheese.class}, version = 1)
public abstract class CheeseDatabase extends RoomDatabase {
    private static CheeseDatabase instance;

    public abstract CheeseDao cheeseDao();

    public static synchronized CheeseDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CheeseDatabase.class, "cheese_table")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
//            instance.populateInitialData();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CheeseDao cheeseDao;

        private PopulateDbAsyncTask(CheeseDatabase db){
            cheeseDao = db.cheeseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cheeseDao.insert(new Cheese("Winda"));
            cheeseDao.insert(new Cheese("Hidayat"));
            return null;
        }
    }

    //new method
    private void populateInitialData() {
        if (cheeseDao().count() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    Cheese cheese = new Cheese();
                    for (int i = 0; i < Cheese.CHEESES.length; i++) {
                        cheese.name = Cheese.CHEESES[i];
                        cheeseDao().insert(cheese);
                    }
                }
            });
        }
    }
}
