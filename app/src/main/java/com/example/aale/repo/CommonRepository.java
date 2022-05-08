package com.example.aale.repo;

import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.aale.model.Advertisement;
import com.example.aale.model.Customer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class CommonRepository {
    private static final DatabaseReference customerDBConneciton = DBConnectionRepository.getCustomerReference();
    private static final DatabaseReference advertisementDBConnection = DBConnectionRepository.getAdvertisementReference();
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
                        Log.i("customer",ds.getValue(Customer.class).getEmail());
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
        });

    }

    public void getAds() {
        advertisementDBConnection.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>(){

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    List <Advertisement> advertisements=new ArrayList<>();
                    for(DataSnapshot ds : task.getResult().getChildren()){

                        advertisements.add(ds.getValue(Advertisement.class));
                       Log.i("advertisement",ds.getValue(Advertisement.class).getAdID());
                    }
                    Log.i("advertisement",advertisements.toString());
                    onFirestoreTaskComplete.advertisementListDataAdded(advertisements);
                }else{
                    Log.i("advertisement","failed");
                    onFirestoreTaskComplete.onRetrievalError(task.getException());
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void deleteAd(String adID ){
        advertisementDBConnection.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().hasChild(adID)){
                        //
                        advertisementDBConnection.child(adID).removeValue();

                        onFirestoreTaskComplete.onAdDeleteTaskComplete(1);
                    }else{
                        onFirestoreTaskComplete.onAdDeleteError(-1);
                    }

                }else{
                    onFirestoreTaskComplete.onUserDeleteError(-1);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void addAdvertisement(Advertisement add){
        advertisementDBConnection.child(add.getAdID()).setValue(add).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                     onFirestoreTaskComplete.onAdvertisementSuccess(1);
                }else{
                    onFirestoreTaskComplete.onAdvertisementUnSuccess(-1);
                }

            }
        });

    }
    public interface  OnFirestoreTaskComplete{
        void  customerListDataAdded(List<Customer> customers);
        void advertisementListDataAdded(List<Advertisement> advertisements);

        void onRetrievalError(Exception e);
        void onUserDeleteTaskComplete(Integer deleteState);
        void onUserDeleteError(Integer deleteState);

        void onAdDeleteTaskComplete(Integer deleteStates);
        void onAdvertisementSuccess(Integer addSuccess);
        void onAdvertisementUnSuccess(Integer addSuccess);

        void onAdDeleteError(Integer deleteStates);
    }

}
