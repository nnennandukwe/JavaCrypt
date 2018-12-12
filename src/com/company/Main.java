package com.company;

import java.util.ArrayList;
import com.google.gson.*;


public class Main {

    public static ArrayList<Block> chain = new ArrayList<Block>();

    public static void main(String[] args) {

        chain.add(new Block("This is a brand new block 1", "0"));

        String chainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        System.out.println(chainJson);

    }


}
