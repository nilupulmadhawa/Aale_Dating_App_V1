package com.example.aale.repo;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aale.activities.RegisterActivity;
import com.example.aale.model.Admin;
import com.example.aale.model.Customer;
import com.example.aale.model.Email;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class AdminRepository {
    private static final DatabaseReference emailRef = DBConnectionRepository.getEmailReference();
    private static final DatabaseReference adminRef = DBConnectionRepository.getAdminReference();
    private FirebaseAuth mAuth;
    private OnAdminTaskTaskComplete onAdminTaskTaskComplete;

    public AdminRepository(OnAdminTaskTaskComplete onAdminTaskTaskComplete) {
        this.onAdminTaskTaskComplete=onAdminTaskTaskComplete;

    }
    public void sendEmail(Email email) {
        emailRef.push().setValue(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                   onAdminTaskTaskComplete.onMessageSendSuccess(1);

            } else {
                   onAdminTaskTaskComplete.onMessageSendUnSuccess(-1);
            }
        });

    }
    public void addAdmin(Admin admin){
        mAuth=FirebaseAuth.getInstance();

        if(!admin.equals(null)) {
                mAuth.createUserWithEmailAndPassword(admin.getEmail(),admin.getPassword()).addOnSuccessListener(authResult -> {
                        final String userId = authResult.getUser().getUid();
                        admin.setUserID(userId);
                        //get user inputs and assigning them into stdObject
                        //pass child value as the key
                        adminRef.child(userId).setValue(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                onAdminTaskTaskComplete.onAdminCreationSuccess(1);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                onAdminTaskTaskComplete.onAdminDetailsStoreUnSuccess(0);
                                //  Toast.makeText(getApplicationContext()," Data was not saved Successfully",Toast.LENGTH_SHORT).show();
                            }
                        });
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("dailure message", "onFailure: "+e.getMessage());
                        onAdminTaskTaskComplete.onAdminCreationUnSuccess(-1);
                    }
                });

         }else{
            onAdminTaskTaskComplete.onAdminCreationUnSuccess(-2);
        }

    }
    public void updateUser(Customer customer){
        emailRef.child(customer.getUserID()).setValue(customer).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
               // onAdminTaskTaskComplete.onMessageSendSuccess(1);

            } else {
              //  onAdminTaskTaskComplete.onMessageSendUnSuccess(-1);
            }
        });

    }
    public interface  OnAdminTaskTaskComplete{

        void onMessageSendSuccess(Integer sentState);
        void onMessageSendUnSuccess(Integer sentState);
        void onAdminCreationSuccess(Integer creationSuccessState);
        void onAdminCreationUnSuccess(Integer creationUnSuccessState);
        void onAdminDetailsStoreUnSuccess(Integer storeUnSuccessState);

    }
}
