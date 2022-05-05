package com.example.aale.ui.send_email;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class SendEmailViewModel extends ViewModel {

        private final MutableLiveData<String> mText;

        public SendEmailViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is advertisements fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
}