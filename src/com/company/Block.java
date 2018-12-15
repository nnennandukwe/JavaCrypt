package com.company;

import java.util.Date;

public class Block {

    private String hash;
	private String previousHash;
	private String data;
	private long timeStamp; //as number of milliseconds since 1/1/1970
    private int nonce;

    // CONSTRUCTOR
	public Block(String data, String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = genHash();
        this.nonce = 0;
    }

    // ACCESSOR METHODS

    public String getHash(){
	    return hash;
    }

    public String getData(){
	    return data;
    }

    public String getPreviousHash(){
	    return previousHash;
    }

    public long getTimeStamp(){
	    return timeStamp;
    }

    public int getNonce(){
        return nonce;
    }

    // MUTATOR METHODS
    public void setData(String newData){
        data = newData;
    }

    public void setNonce(int newNonce){
        nonce = newNonce;
    }

    // FUNCTIONALITY METHODS

    // generate a hash for the block
    public String genHash() {
        String generatedHash = StringDoctor.applySha256(
            previousHash +
            Long.toString(timeStamp) +
            data
        );
        return generatedHash;
    }

    public void mineBlock(int complexity) {
        String target = new String(new char[complexity]).replace('\0', '0');
        while(!hash.substring(0, complexity).equals(target)) {
            nonce ++;
            hash = genHash();
        }
        System.out.println("SUCCESSFUL BLOCK MINED: " + hash);
    }

}
