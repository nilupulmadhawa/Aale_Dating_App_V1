package com.example.aale.ui.e_mail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmailViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public EmailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is email fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
