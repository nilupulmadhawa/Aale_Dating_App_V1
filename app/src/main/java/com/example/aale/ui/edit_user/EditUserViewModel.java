package com.example.aale.ui.edit_user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aale.model.Customer;
import com.example.aale.repo.AdminRepository;


public class EditUserViewModel extends ViewModel implements AdminRepository.OnAdminTaskTaskComplete{

        private final MutableLiveData<Integer> updateState;

        private AdminRepository adminnRepository=new AdminRepository(this);

        public EditUserViewModel() {
           updateState = new MutableLiveData<>();

        }

        public LiveData<Integer> updateCustomer(Customer customer) {
            adminnRepository.updateUser(customer);
            return updateState;
        }

        @Override
        public void onMessageSendSuccess(Integer sentState) {

        }

        @Override
        public void onMessageSendUnSuccess(Integer sentState) {

        }

        @Override
        public void onAdminCreationSuccess(Integer creationSuccessState) {

        }

        @Override
        public void onAdminCreationUnSuccess(Integer creationUnSuccessState) {

        }

        @Override
        public void onAdminDetailsStoreUnSuccess(Integer storeUnSuccessState) {

        }

        @Override
        public void onCustomerUpdateSuccess(Integer updateSuccessState) {
              updateState.setValue(updateSuccessState);
        }

        @Override
        public void onCustomerUpdateUnSuccess(Integer updateUnSuccessState) {
            updateState.setValue(updateUnSuccessState);
        }
}