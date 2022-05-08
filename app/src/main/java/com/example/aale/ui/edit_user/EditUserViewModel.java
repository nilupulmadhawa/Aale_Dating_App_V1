package com.example.aale.ui.edit_user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class EditUserViewModel extends ViewModel {

        private final MutableLiveData<String> mText;

        public EditUserViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is advertisements fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
}