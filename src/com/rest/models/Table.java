package com.rest.models;

public class Table { 

    public enum Availability{
        FREE, OCCUPY
    }

    private final int id;
    private final int capacity;
    private Availability Status;

    public Table(int id,int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.Status = Availability.FREE;
    }
    public int getid(){
        return id;
    }
    public int getcapacity(){
        return capacity;
    }
    public Availability getStatus(){
        return Status;
    }
    public void freetable(){
        this.Status = Availability.FREE;
    }
    public void occupytable(){
        this.Status = Availability.OCCUPY;
    }



}
