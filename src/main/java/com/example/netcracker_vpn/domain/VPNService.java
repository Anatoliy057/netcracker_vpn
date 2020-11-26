package com.example.netcracker_vpn.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(
        name = "vpn_services",
        uniqueConstraints = @UniqueConstraint(columnNames={"name", "server"})
)

public class VPNService {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "server", unique = true, length = 30, nullable = false)
    private String server;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "date_expiry", length = 30, nullable = false)
    private LocalDate dateExpiry;

    public VPNService() {}

    public VPNService(String name, String server, String password, LocalDate dateExpiry) {
        this.name = name;
        this.server = server;
        this.password = password;
        this.dateExpiry = dateExpiry;
    }
}
