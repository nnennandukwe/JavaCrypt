package com.company;

import java.lang.reflect.Array;
import java.security.*;
import java.util.ArrayList;

public class Transaction {

    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public float value;
    public byte[] signature;


    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionInput> outputs = new ArrayList<TransactionInput>();

    private static int sequence = 0;

    public Transaction(
            PublicKey from,
            PublicKey to,
            float value,
            ArrayList<TransactionInput> inputs
    ){

        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }

    public String calculatesHash(){
        sequence++;
    }

}
