package com.example.aale.ui.push_notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PushNotificationsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public PushNotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is push notification fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}