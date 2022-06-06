package com.example.aale.ui.add_user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aale.model.Admin;
import com.example.aale.model.Email;
import com.example.aale.repo.AdminRepository;


public class AddUserViewModel extends ViewModel implements  AdminRepository.OnAdminTaskTaskComplete{


        private  final MutableLiveData<Integer>  admin_added;

    private AdminRepository adminnRepository=new AdminRepository(this);

        public AddUserViewModel() {
          admin_added=new MutableLiveData<>();

        }
    public LiveData<Integer> addAdmin(Admin admin){
        adminnRepository.addAdmin(admin);
        return  admin_added;
    }


    @Override
    public void onMessageSendSuccess(Integer sentState) {

    }

    @Override
    public void onMessageSendUnSuccess(Integer sentState) {

    }

    @Override
    public void onAdminCreationSuccess(Integer creationSuccessState) {
        admin_added.setValue(creationSuccessState);
    }

    @Override
    public void onAdminCreationUnSuccess(Integer creationUnSuccessState) {
        admin_added.setValue(creationUnSuccessState);
    }

    @Override
    public void onAdminDetailsStoreUnSuccess(Integer storeUnSuccessState) {
        admin_added.setValue(storeUnSuccessState);
    }

    @Override
    public void onCustomerUpdateSuccess(Integer updateSuccessState) {

    }

    @Override
    public void onCustomerUpdateUnSuccess(Integer updateUnSuccessState) {

    }
}