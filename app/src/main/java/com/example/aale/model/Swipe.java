package com.example.aale.model;

public class Swipe {
    //set new sqwipe id bu t swipes child shou be th user swipe person id
    private String swipe_id;

    // person who has swiped person
    private String author;
    private String swiped_by;
    private String swiped_date;
    private String swiped_time;
    private String swipe_type;
    private boolean seen_status;


    public Swipe() {
    }
    public String getSwipe_id() {
        return swipe_id;
    }

    public void setSwipe_id(String swipe_id) {
        this.swipe_id = swipe_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getSwiped_by() {
        return swiped_by;
    }

    public void setSwiped_by(String swiped_by) {
        this.swiped_by = swiped_by;
    }



    public String getSwiped_date() {
        return swiped_date;
    }

    public void setSwiped_date(String swiped_date) {
        this.swiped_date = swiped_date;
    }



    public String getSwipe_type() {
        return swipe_type;
    }

    public void setSwipe_type(String swipe_type) {
        this.swipe_type = swipe_type;
    }

    public boolean isSeen_status() {
        return seen_status;
    }

    public void setSeen_status(boolean seen_status) {
        this.seen_status = seen_status;
    }






}
