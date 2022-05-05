package com.example.aale.ui.send_email;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.aale.databinding.FragmentSendEmailBinding;
import com.example.aale.model.Email;


public class SendEmailFragment extends Fragment {

    private FragmentSendEmailBinding binding;
    private EditText senderEmail;
    private EditText receiverEmail;
    private  EditText subject;
    private EditText message;
    private Button sendBtn;
    private   Email email;
    private SendEmailViewModel sendEmailViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sendEmailViewModel = new ViewModelProvider(this).get( SendEmailViewModel.class);
        binding = FragmentSendEmailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //bind  ui elements
        senderEmail = binding.edtSenderEmail;
        receiverEmail=binding.edtReceiverEmail;
        subject=binding.edtSubject;
        message=binding.edtMessage;
        sendBtn=binding.btnSendMail;

        email= new Email();



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get the arguments
        if(getArguments()!=null) {
            SendEmailFragmentArgs args = SendEmailFragmentArgs.fromBundle(getArguments());
            senderEmail.setText(args.getAdminEmailId());
            receiverEmail.setText(args.getEmailId());
        }

        email.setSender(senderEmail.getText().toString());
        email.setReceiver(receiverEmail.getText().toString());
        email.setMessage(message.getText().toString());
        email.setSubject(subject.getText().toString());

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!email.getMessage().equals("")&&!email.getSubject().equals("")){
                    sendEmailViewModel.sendEmail(email).observe(getViewLifecycleOwner(),integer -> {
                        if(integer >-1){
                            Toast.makeText(getContext(),"Email Sent successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"Email Sent unsuccessful",Toast.LENGTH_SHORT).show();
                        }

                    });
                }else{
                    if(email.getMessage().equals("")){
                        Toast.makeText(getContext(),"Subject  is empty",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

            //set email values



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}