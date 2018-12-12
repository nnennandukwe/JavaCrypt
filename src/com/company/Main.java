package com.company;

import com.google.gson.*;
import org.bouncycastle.*;

public class Main {

    public static void main(String[] args) {

        Chain chain = new Chain();
        chain.getChain().add(new Block("This is a brand new block 1", "0"));

        // build the chain with JSON
        String decodedChain = new GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(chain);

        System.out.println(decodedChain);

        chain.isValidChain();

    }

}
