package com.mycompany.rsadigitalsignature;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

public class RSADigitalSignature {
    public static void main(String[] args) throws Exception {
        String message = "Transfer $1000 to Mark";
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        Signature signature = Signature.getInstance("SHA256withRSA");
              
        signature.initSign(keyPair.getPrivate());
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] digitalSignature = signature.sign();

        System.out.println("Original Message: " + message);
        System.out.println("Digital Signature Created Successfully.");

        signature.initVerify(keyPair.getPublic());
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Signature Verification: "
                + signature.verify(digitalSignature));

        String changedMessage = "Transfer $9000 to Mark";

        signature.initVerify(keyPair.getPublic());
        signature.update(changedMessage.getBytes(StandardCharsets.UTF_8));
        System.out.println("Verification After Changing Message: "
                + signature.verify(digitalSignature));
    }
}