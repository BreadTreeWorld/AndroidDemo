package com.example.myapplication.model;


public class ListItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CONTENT = 1;

    private int type;
    private String text;

    public ListItem(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() { return type; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
