package com.company;

import java.lang.reflect.Array;
import java.security.*;
import java.util.ArrayList;

public class Transaction {

    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public double value;
    public byte[] signature;
    public double minimumTransaction;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
    private static int sequence = 0;

    public Transaction(
        PublicKey from,
        PublicKey to,
        double value,
        ArrayList<TransactionInput> inputs
    ){

        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
        this.minimumTransaction =  0.1;
    }

    public String calculateHash(){
        sequence++;
        return StringDoctor.applySha256(
            StringDoctor.getStringFromKey(sender) +
            StringDoctor.getStringFromKey(recipient) +
            value + sequence
        );
    }

    //Signs all the data we don't wish to be tampered with.
    public void generateSignature(PrivateKey privateKey) {
        String data = StringDoctor.getStringFromKey(sender) + StringDoctor.getStringFromKey(recipient) +
        Double.toString(value);
	    signature = StringDoctor.applyECDSASig(privateKey,data);
    }

    //Verifies the data we signed hasnt been tampered with
    public boolean verifySignature() {
        String data = StringDoctor.getStringFromKey(sender) + StringDoctor.getStringFromKey(recipient) +
        Double.toString(value);
	    return StringDoctor.verifyECDSASig(sender, data, signature);
    }

    public boolean processTransaction() {
        if(verifySignature() == false) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        for(TransactionInput i : inputs) {
            i.UTXO = UTXOs.get(i.transactionOutputId);
        }

        if(getInputsValue() < minimumTransaction) {
            System.out.println("#Transaction Inputs to small: " + getInputsValue());
            return false;
        }

        double leftOver = getInputsValue() - value;
        transactionId = calculateHash();
        outputs.add(new TransactionOutput(this.recipient, value, transactionId)); // send value to recipient
        outputs.add(new TransactionOutput(this.sender, leftOver, transactionId));

        //Add outputs to Unspent list
		for(TransactionOutput o : outputs) {
			UTXOs.put(o.id , o);
		}
		
		//Remove transaction inputs from UTXO lists as spent:
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) continue; //if Transaction can't be found skip it 
			UTXOs.remove(i.UTXO.id);
		}
		
		return true;
    }

    public double getInputsValue(){
        double total = 0;
        for(TransactionInput i: inputs){
            if(i.UXTO == null) continue;
            total += i.UXTO.value;
        }
        return total;
    }

    public double getOutputsValue(){
        double total = 0;
        for(TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

}
