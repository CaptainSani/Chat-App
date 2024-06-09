package org.sani;

public class ClientA {
    public static void main(String[] args) {
        Client clientA = new Client("localhost", 6828);
        clientA.start();
    }
}
