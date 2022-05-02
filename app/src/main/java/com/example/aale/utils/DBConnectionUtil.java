package com.example.aale.utils;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBConnectionUtil {
    private static final String URL ="https://aaledatingapp-default-rtdb.firebaseio.com/";
    private static final String USER="User";
    private static final String CUSTOMER="Customer";
    private static final String ADMIN="Admin";
    private static final String EMAIL="Email";
    private static final String SWIPE="Swipe";
    private static final String REPORT="Report";

    private static DatabaseReference dbRef=null;
    private DBConnectionUtil(){}
    private static DatabaseReference getDbRef(){
        try{

            if(dbRef==null){
                dbRef= FirebaseDatabase.getInstance(URL).getReference();
                Log.i("conneciton ","Conncetion stablished firstime");
            }

        }catch (Exception e){
            Log.i("conneciton ","Conncetion isuue");
        }
        return dbRef;
    }
    public static  DatabaseReference getUserReference(){
        return getDbRef().child(USER);

    }
    public static  DatabaseReference getCustomerReference(){
        return getUserReference().child(CUSTOMER);
    }
    public static  DatabaseReference getAdminReference(){
        return getUserReference().child(ADMIN);
    }
    public static  DatabaseReference getEmailReference(){
        return getDbRef().child(EMAIL);
    }
    public static  DatabaseReference getSwipeReference(){
        return getCustomerReference().child(SWIPE);
    }
    public static  DatabaseReference getReportReference(){
        return getDbRef().child(REPORT);
    }

}
