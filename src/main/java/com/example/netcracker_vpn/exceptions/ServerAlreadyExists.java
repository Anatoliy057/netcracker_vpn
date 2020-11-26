package com.example.netcracker_vpn.exceptions;

public class ServerAlreadyExists extends UniqueException {

    public ServerAlreadyExists(String server) {
        super();
        putColumnAndValue("server", server);
    }
}
