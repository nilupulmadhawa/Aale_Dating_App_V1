package com.example.aale.repo;

import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.aale.model.Customer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommonRepository {
    private static final DatabaseReference customerDBConneciton = DBConnectionRepository.getCustomerReference();
    private OnFirestoreTaskComplete onFirestoreTaskComplete;

    public CommonRepository (OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete=onFirestoreTaskComplete;

    }

    public  void createUser() {

    }

    public void getCustomers() {

        customerDBConneciton.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    List <Customer> customers=new ArrayList<>();
                    for(DataSnapshot ds : task.getResult().getChildren()){
                        customers.add(ds.getValue(Customer.class));
                    }
                    Log.i("customer",customers.toString());
                    onFirestoreTaskComplete.customerListDataAdded(customers);
                }else{
                    Log.i("customer","failed");
                    onFirestoreTaskComplete.onRetrievalError(task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });



    }
    public void deleteUser(String userID){
        customerDBConneciton.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().hasChild(userID)){
                        //
                        customerDBConneciton.child(userID).removeValue();

                        onFirestoreTaskComplete.onUserDeleteTaskComplete(1);
                    }else{
                        onFirestoreTaskComplete.onUserDeleteError(-1);
                    }

                }else{
                    onFirestoreTaskComplete.onUserDeleteError(-1);
                }
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });;

    }

    public interface  OnFirestoreTaskComplete{
        void  customerListDataAdded(List<Customer> customers);
        void onRetrievalError(Exception e);
        void onUserDeleteTaskComplete(Integer deleteState);
        void onUserDeleteError(Integer deleteState);
    }

}
