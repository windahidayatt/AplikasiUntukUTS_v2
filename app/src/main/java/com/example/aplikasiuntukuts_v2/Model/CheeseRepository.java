package com.example.aplikasiuntukuts_v2.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CheeseRepository {
    private CheeseDao cheeseDao;
    private LiveData<List<Cheese>> allCheeses;

    public CheeseRepository(Application application){
        CheeseDatabase database = CheeseDatabase.getInstance(application);
        cheeseDao = database.cheeseDao();
        allCheeses = cheeseDao.getAllTCheeses();
    }

    public void insert(Cheese cheese){
        new InsertCheeseAsyncTask(cheeseDao).execute(cheese);
    }

    public void update(Cheese cheese){
        new UpdateCheeseAsyncTask(cheeseDao).execute(cheese);
    }

    public void delete(Cheese cheese){
        new DeleteCheeseAsyncTask(cheeseDao).execute(cheese);
    }

    public void deleteAllCheeses(){
        new DeleteAllCheesesAsyncTask(cheeseDao).execute();
    }

    public LiveData<List<Cheese>> getAllTransactions(){
        return allCheeses;
    }

    public static class InsertCheeseAsyncTask extends AsyncTask<Cheese, Void, Void> {
        private CheeseDao cheeseDao;

        private InsertCheeseAsyncTask(CheeseDao cheeseDao){
            this.cheeseDao=cheeseDao;
        }

        @Override
        protected Void doInBackground(Cheese... cheeses){
            cheeseDao.insert(cheeses[0]);
            return null;
        }
    }

    public static class UpdateCheeseAsyncTask extends AsyncTask<Cheese, Void, Void>{
        private CheeseDao cheeseDao;

        private UpdateCheeseAsyncTask(CheeseDao cheeseDao){
            this.cheeseDao=cheeseDao;
        }

        @Override
        protected Void doInBackground(Cheese... cheeses){
            cheeseDao.update(cheeses[0]);
            return null;
        }
    }

    public static class DeleteCheeseAsyncTask extends AsyncTask<Cheese, Void, Void>{
        private CheeseDao cheeseDao;

        private DeleteCheeseAsyncTask(CheeseDao cheeseDao){
            this.cheeseDao=cheeseDao;
        }

        @Override
        protected Void doInBackground(Cheese... cheeses){
            cheeseDao.delete(cheeses[0]);
            return null;
        }
    }

    public static class DeleteAllCheesesAsyncTask extends AsyncTask<Void, Void, Void>{
        private CheeseDao cheeseDao;

        private DeleteAllCheesesAsyncTask(CheeseDao cheeseDao){
            this.cheeseDao=cheeseDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            cheeseDao.deleteAllCheeses();
            return null;
        }
    }
}
