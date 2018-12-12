package com.company;

import java.util.ArrayList;

public class Chain {

    private ArrayList<Block> chain;
    private int complexity;

    // CONSTRUCTORS

    Chain(){
        this.chain = new ArrayList<>();
        this.complexity = 1;
    }

    Chain(int complexity){
        this.chain = new ArrayList<Block>();
        this.complexity = complexity;
    }

    // MUTATORS
    
    public void setComplexity(int newComplexity){
        complexity = newComplexity;
    }

    // ACCESSORS
    
    /**
     * @return the complexity
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * 
     * @return the chain
     */
    public ArrayList getChain(){
        return chain;
    }


    // FUNCTIONALITY METHODS

    public Boolean isValidChain(){

        Block currentBlock;
        Block prevBlock;
        String hashTarget = new String(new char[complexity]).replace('\0','0');

        for(int i=1; i < chain.size(); i++){
            currentBlock = chain.get(i);
            prevBlock = chain.get(i-1);

            if (!currentBlock.getHash().equals(prevBlock.genHash())) {
                System.out.println("Invalid chain due to differing CURRENT hashes.");
                return false;
            }
            if (!prevBlock.getHash().equals(prevBlock.genHash())) {
                System.out.println("Invalid chain due to differing PREVIOUS hashes.");
                return false;
            }

            if(!currentBlock.getHash().substring(0, complexity).equals(hashTarget)) {
                System.out.println("WARNING!! BLOCK FAILED TO BE MINED.");
                return false;
            }
        }

        return true;
    }

}
