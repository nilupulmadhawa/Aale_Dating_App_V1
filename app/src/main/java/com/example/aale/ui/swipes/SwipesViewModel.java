package com.example.aale.ui.swipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SwipesViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public SwipesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is swipe fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}