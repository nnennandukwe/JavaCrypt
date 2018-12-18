package com.company;

import java.security.PublicKey;

public class TransactionOutput {
    
    public String id;
    public PublicKey recipient;
    public double value;
    public String parentTransactionId;


    public TransactionOutput(
        PublicKey recipient,
        double value,
        String parentTransactionId
    ) {
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringDoctor.applySha256(StringDoctor.getStringFromKey(recipient) + value + parentTransactionId);
    }

    // Check if coin belongs to you
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == recipient);
    }

}
