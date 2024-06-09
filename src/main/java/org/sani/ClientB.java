package org.sani;

public class ClientB {
    public static void main(String[] args) {
        Client clientB = new Client("localhost", 6828);
        clientB.start();
    }
}
