package com.example.aale.activities;





import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.aale.R;
import com.example.aale.databinding.ActivityRegisterBinding;
import com.example.aale.model.Customer;
import com.example.aale.model.UserFactory;
import com.example.aale.repo.DBConnectionRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class RegisterActivity extends AppCompatActivity {


    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    private Customer customer;
    private DatabaseReference dbRef;
    private EditText useremailEditText;
    private EditText usernameEditText;
    private EditText phoneNumberEditText;
    private EditText passwordEditText ;
    private Spinner spinnerGenders;
    private Spinner spinnerLookingFor;

    private Button registerButton;

    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        //intializing database
        dbRef= DBConnectionRepository.getCustomerReference();

        mAuth=FirebaseAuth.getInstance();
        //If the user is already logged
        firebaseAuthStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            //get the firebase user
            final FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){

                    Log.i("reg","logged in");
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        //intitate customer object
        customer = (Customer) UserFactory.getCustomer();
        //Set spinner values
        spinnerGenders=binding.rgSpinnerGender;
        spinnerLookingFor=binding.rgSpinnerLookingFor;
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGenders.setAdapter(adapter);
        spinnerLookingFor.setAdapter(adapter);

        //initialize input variables
        useremailEditText = binding.rgUseremail;
        usernameEditText=binding.rgUsername;
        phoneNumberEditText=binding.rgPhoneNumber;
        passwordEditText = binding.rgPassword;

        registerButton= binding.rgRegister;






    }

    @Override
    protected void onResume() {
        super.onResume();
        //create user
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //initilizing customer object values
                final  String gender=spinnerGenders.getSelectedItem().toString();
                final  String lookingfor=spinnerLookingFor.getSelectedItem().toString();
                final String username=usernameEditText.getText().toString();
                final Integer userPhoneNumber=Integer.parseInt(phoneNumberEditText.getText().toString());
                final  String userEmail=useremailEditText.getText().toString();
                final  String userPasssword=passwordEditText.getText().toString();
                //checo if the all the vales are entered
                try{
                    if(TextUtils.isEmpty(userEmail)){
                        Toast.makeText(getApplicationContext(),"Please enter an user Name ",Toast.LENGTH_SHORT).show();

                    }else if(TextUtils.isEmpty(userPasssword)){
                        Toast.makeText(getApplicationContext(),"Please enter password ",Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(gender)) {
                        Toast.makeText(getApplicationContext(),"Please enter your gender  ",Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(lookingfor)){
                        Toast.makeText(getApplicationContext(),"Please enter who you are looking for  ",Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(username)){
                        Toast.makeText(getApplicationContext(),"Please enter your name ",Toast.LENGTH_SHORT).show();
                    }else{



                        mAuth.createUserWithEmailAndPassword(userEmail,userPasssword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"Sign up error",Toast.LENGTH_SHORT).show();
                                }else{
                                    final String userId =mAuth.getCurrentUser().getUid();

                                    customer.setUserID(userId);
                                    customer.setGender(gender);
                                    customer.setLooking_for(lookingfor);
                                    customer.setPhone_number(userPhoneNumber);
                                    customer.setUserName(username);
                                    customer.setEmail(userEmail);
                                    customer.setPassword(userPasssword);
                                    customer.setUserType("customer");
                                    //get user inputs and assigning them into stdObject

                                    //pass child value as the key
                                    dbRef.child(userId).setValue(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext()," Data saved Successfully",Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext()," Data was not saved Successfully",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }
                        });
                    }

                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext()," Invalid contact Number ",Toast.LENGTH_SHORT).show();;


                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}