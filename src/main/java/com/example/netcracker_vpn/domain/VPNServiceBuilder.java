package com.example.netcracker_vpn.domain;

import java.time.LocalDate;

class VPNServiceBuilder {

    private long id;
    private String name;
    private String server;
    private String password;
    private LocalDate dateExpiry;

    public VPNServiceBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public VPNServiceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public VPNServiceBuilder setServer(String server) {
        this.server = server;
        return this;
    }

    public VPNServiceBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public VPNServiceBuilder setDateExpiry(LocalDate dateExpiry) {
        this.dateExpiry = dateExpiry;
        return this;
    }

    public VPNService build() {
        return new VPNService(
                id,
                name,
                server,
                password,
                dateExpiry
        );
    }
}
