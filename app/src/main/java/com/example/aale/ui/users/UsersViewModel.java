package com.example.aale.ui.users;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aale.model.Advertisement;
import com.example.aale.model.Customer;
import com.example.aale.repo.CommonRepository;

import java.util.List;

public class UsersViewModel extends ViewModel implements CommonRepository.OnFirestoreTaskComplete{
    // TODO: Implement the ViewModel

    private final MutableLiveData<List<Customer>> customersLiveData = new MutableLiveData<>();
    private  final MutableLiveData<Integer> deleteState;

    private CommonRepository commonRepository=new CommonRepository(this);

    public UsersViewModel() {

        commonRepository.getCustomers();
        deleteState=new MutableLiveData<>();


    }


    public LiveData<List<Customer>> getUsers(){
        return  customersLiveData;
    }

    public LiveData<Integer> deleteUser(String userID){
        commonRepository.deleteUser(userID);
        return deleteState;

    }

    @Override
    public void customerListDataAdded(List<Customer> customers) {
        customersLiveData.setValue(customers);
    }

    @Override
    public void advertisementListDataAdded(List<Advertisement> advertisements) {

    }

    @Override
    public void onRetrievalError(Exception e) {

    }



    @Override
    public void onUserDeleteTaskComplete(Integer deleteStates) {
        deleteState.setValue(deleteStates);
    }

    @Override
    public void onUserDeleteError(Integer deleteStates) {
        deleteState.setValue(deleteStates);
    }

    @Override
    public void onAdDeleteTaskComplete(Integer deleteStates) {

    }

    @Override
    public void onAdvertisementSuccess(Integer addSuccess) {

    }

    @Override
    public void onAdvertisementUnSuccess(Integer addSuccess) {

    }

    @Override
    public void onAdDeleteError(Integer deleteStates) {

    }


}