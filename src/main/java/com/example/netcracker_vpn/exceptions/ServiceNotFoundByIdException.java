package com.example.netcracker_vpn.exceptions;

public class ServiceNotFoundByIdException extends Exception {

    private long id;

    public ServiceNotFoundByIdException(long id) {
        super(String.format("Сервиса с таким id %d не существует", id));
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
