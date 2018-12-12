package com.company;

import java.util.Date;

public class Block {

    public String hash;
	public String previousHash;
	private String data;
	private long timeStamp; //as number of milliseconds since 1/1/1970


    // CONSTRUCTOR
	public Block(String data, String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = genHash();
    }

    public String genHash() {
        String generatedHash = StringDoctor.applySha256( 
                previousHash +
                Long.toString(timeStamp) +
                data 
                );
        return generatedHash;
    }
    
}
