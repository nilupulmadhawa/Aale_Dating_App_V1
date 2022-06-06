package com.example.aale.ui.advertisements;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aale.model.Advertisement;
import com.example.aale.model.Customer;
import com.example.aale.repo.CommonRepository;

import java.util.List;


public class AdvertisementsViewModel extends ViewModel implements CommonRepository.OnFirestoreTaskComplete {

    private final MutableLiveData <List<Advertisement>> adsLiveData = new MutableLiveData<>();
    private  final MutableLiveData <Integer> deleteState;

    private CommonRepository commonRepository=new CommonRepository(this);

    //private final MutableLiveData<String> mText;

    public AdvertisementsViewModel() {
        commonRepository.getAds();
        deleteState=new MutableLiveData<>();
    }

    public MutableLiveData<List<Advertisement>> getAds() {
        return adsLiveData;
    }


    @Override
    public void customerListDataAdded(List<Customer> customers) {

    }

    @Override
    public void advertisementListDataAdded(List<Advertisement> advertisements) {
        adsLiveData.setValue(advertisements);
    }

    @Override
    public void onRetrievalError(Exception e) {

    }

    @Override
    public void onUserDeleteTaskComplete(Integer deleteState) {

    }

    @Override
    public void onUserDeleteError(Integer deleteState) {

    }

    @Override
    public void onAdDeleteTaskComplete(Integer deleteStates) {

        deleteState.setValue(deleteStates);
    }

    @Override
    public void onAdvertisementSuccess(Integer addSuccess) {

    }

    @Override
    public void onAdvertisementUnSuccess(Integer addSuccess) {

    }

    @Override
    public void onAdDeleteError(Integer deleteStates) {
        deleteState.setValue(deleteStates);
    }

    public MutableLiveData<Integer> deleteAd(String adID) {
        commonRepository.deleteAd(adID);
        return deleteState;
    }
}