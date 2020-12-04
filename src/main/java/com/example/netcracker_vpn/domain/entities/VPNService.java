package com.example.netcracker_vpn.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "vpn_services")

public class VPNService {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "server", unique = true, length = 30, nullable = false)
    private String server;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "date_expiry", nullable = false)
    private Timestamp dateExpiry;

    public VPNService() {}

    public VPNService(String name, String server, String password, Timestamp dateExpiry) {
        this.name = name;
        this.server = server;
        this.password = password;
        this.dateExpiry = dateExpiry;
    }
}
