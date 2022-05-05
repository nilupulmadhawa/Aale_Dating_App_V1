package com.example.aale.repo;

import com.example.aale.model.Email;
import com.google.firebase.database.DatabaseReference;


public class AdminRepository {
    private static final DatabaseReference emailRef = DBConnectionRepository.getEmailReference();
    private OnAdminTaskTaskComplete onAdminTaskTaskComplete;

    public AdminRepository(OnAdminTaskTaskComplete onAdminTaskTaskComplete) {
        this.onAdminTaskTaskComplete=onAdminTaskTaskComplete;

    }
    public void sendEmail(Email email) {
        emailRef.push().setValue(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                   onAdminTaskTaskComplete.onMessageSendSuccess(1);

            } else {
                   onAdminTaskTaskComplete.onMessageSendUnsuccess(-1);
            }
        });

    }
    public interface  OnAdminTaskTaskComplete{
        void onMessageSendSuccess(Integer sentState);
        void onMessageSendUnsuccess(Integer sentState);


    }
}
