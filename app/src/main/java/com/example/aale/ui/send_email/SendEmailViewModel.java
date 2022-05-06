package com.example.aale.ui.send_email;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aale.model.Admin;
import com.example.aale.model.Email;
import com.example.aale.repo.AdminRepository;
import com.example.aale.repo.CommonRepository;


public class SendEmailViewModel extends ViewModel implements  AdminRepository.OnAdminTaskTaskComplete{


        private  final MutableLiveData<Integer> sent_State;
        private  final MutableLiveData<Integer>  admin_added;
        private  final  MutableLiveData<String> subject;



        private AdminRepository adminnRepository=new AdminRepository(this);
        public SendEmailViewModel() {
            subject=new MutableLiveData<>();
            admin_added=new MutableLiveData<>();
            sent_State=new MutableLiveData<>();

        }

        public  LiveData<Integer> sendEmail(Email email){
            adminnRepository.sendEmail(email);
            return sent_State;
        }

        @Override
        public void onMessageSendSuccess(Integer sentState) {
           sent_State.setValue(sentState);
        }

        @Override
        public void onMessageSendUnSuccess(Integer sentState) {
            sent_State.setValue(sentState);
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
}