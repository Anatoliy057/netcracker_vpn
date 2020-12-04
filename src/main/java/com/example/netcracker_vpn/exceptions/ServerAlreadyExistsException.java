package com.example.netcracker_vpn.exceptions;

public class ServerAlreadyExistsException extends Exception {

    private String value;

    public ServerAlreadyExistsException(String value) {
        super(String.format("Сервер %s с таким именем уже существует", value));
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
