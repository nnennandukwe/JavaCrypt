package com.company;

import java.util.ArrayList;
import com.google.gson.*;


public class Main {

    public static ArrayList<Block> chain = new ArrayList<Block>();

    public static void main(String[] args) {

        chain.add(new Block("This is a brand new block 1", "0"));

        // build the chain with JSON
        String chainJson = new GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(chain);

        System.out.println(chainJson);

    }


    public static Boolean isValidChain(){
        
        Block currentBlock;
        Block prevBlock;

        for(int i=1; i < chain.size(); i++){
            currentBlock = chain.get(i);
            prevBlock = chain.get(i-1);

            if (!currentBlock.getHash().equals(prevBlock.genHash())) {
                System.out.println("Invalid chain due to differing CURRENT hashes.");
                return false;
            }
            if (!prevBlock.getHash().equals(prevBlock.genHash())) {
                System.out.println("Invalid chain due to different PREVIOUS hashes.");
                return false;
            }
        }

        return true;
    }

}
