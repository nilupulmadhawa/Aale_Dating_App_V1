package com.example.aale.ui.users;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aale.model.Customer;
import com.example.aale.repo.CommonRepository;

import java.util.List;

public class UsersViewModel extends ViewModel implements CommonRepository.OnFirestoreTaskComplete{
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Customer>> customersLiveData = new MutableLiveData<>();

    private CommonRepository commonRepository=new CommonRepository(this);
    public UsersViewModel() {
        mText = new MutableLiveData<>();
        commonRepository.getCustomers();

    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<Customer>> getUsers(){
        return  customersLiveData;
    }

    @Override
    public void customerListDataAdded(List<Customer> customers) {
        customersLiveData.setValue(customers);
    }

    @Override
    public void onRetrievalError(Exception e) {

    }
}