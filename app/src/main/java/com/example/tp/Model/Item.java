package com.example.tp.Model;

public class Item {
    private String header;
    private String subject;
    public Item(String header,String subject){
        this.header = header;
        this.subject = subject;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    @Override
    public String toString() {
        return "Item [header=" + header  + ", subject=" + subject + "]";
    }
}
