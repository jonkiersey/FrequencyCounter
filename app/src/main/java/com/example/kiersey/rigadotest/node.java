package com.example.kiersey.rigadotest;


/**
 * Created by kiersey on 7/30/16.
 */
public class node {
    public int id;
    public int value;
    public node left;
    public node right;

    node(int setid, int setvalue) {
        id = setid;
        value = setvalue;
        left = null;
        right = null;
    }

}
