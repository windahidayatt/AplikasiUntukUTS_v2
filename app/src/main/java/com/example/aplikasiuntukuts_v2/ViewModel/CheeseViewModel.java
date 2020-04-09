package com.example.aplikasiuntukuts_v2.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplikasiuntukuts_v2.Model.Cheese;
import com.example.aplikasiuntukuts_v2.Model.CheeseRepository;

import java.util.List;

public class CheeseViewModel extends AndroidViewModel {
    private CheeseRepository repository;
    private LiveData<List<Cheese>> allCheeses;

    public CheeseViewModel(@NonNull Application application) {
        super(application);
        repository = new CheeseRepository(application);
        allCheeses = repository.getAllTransactions();
    }

    public void insert(Cheese cheese){
        repository.insert(cheese);
    }

    public void update(Cheese cheese){
        repository.update(cheese);
    }

    public void delete(Cheese cheese){
        repository.delete(cheese);
    }

    public void deleteAllTransactions(){
        repository.deleteAllCheeses();
    }

    public LiveData<List<Cheese>> getAllCheeses(){
        return allCheeses;
    }
}
