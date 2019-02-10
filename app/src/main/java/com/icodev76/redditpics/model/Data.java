package com.icodev76.redditpics.model;

import com.icodev76.redditpics.model.children.Children;

import java.util.ArrayList;

public class Data {
    private ArrayList<Children> children;

    private String after;

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }
}
