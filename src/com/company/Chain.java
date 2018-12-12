package com.company;

import java.util.ArrayList;

public class Chain {

    private ArrayList<Block> chain;
    private int complexity;

    // CONSTRUCTORS

    Chain(){
        this.chain = new ArrayList<Block>();
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

}
