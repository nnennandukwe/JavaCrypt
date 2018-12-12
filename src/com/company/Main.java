package com.company;

import java.util.ArrayList;
import com.google.gson.*;


public class Main {

    public static void main(String[] args) {

        Chain chain = new Chain();
        chain.getChain().add(new Block("This is a brand new block 1", "0"));

        // build the chain with JSON
        String chainJson = new GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(chain);

        System.out.println(chainJson);

        chain.isValidChain();

    }

}
