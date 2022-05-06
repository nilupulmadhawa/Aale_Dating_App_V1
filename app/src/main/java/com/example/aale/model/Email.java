package com.example.aale.model;

public class Email {
   private  String email_id;
   private String sender;
   private String receiver;
   private String subject;
   private String message;

   public Email() {
   }

   public String getEmail_id() {
      return email_id;
   }

   public void setEmail_id(String email_id) {
      this.email_id = email_id;
   }

   public String getSender() {
      return sender;
   }

   public void setSender(String sender) {
      this.sender = sender;
   }

   public String getReceiver() {
      return receiver;
   }

   public void setReceiver(String receiver) {
      this.receiver = receiver;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }



}

