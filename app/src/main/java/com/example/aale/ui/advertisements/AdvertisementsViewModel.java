package com.example.aale.ui.advertisements;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class AdvertisementsViewModel extends ViewModel {

        private final MutableLiveData<String> mText;

        public AdvertisementsViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is advertisements fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
}