package com.example.netcracker_vpn.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "vpn_services")
public class VPNService {

    @Id
    @Column(name = "vpn_service_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "vpn_service_name", nullable = false)
    private String name;
    @Column(name = "vpn_service_server", length = 30, nullable = false)
    private String server;
    @Column(name = "vpn_service_password", length = 100, nullable = false)
    private String password;
    @Column(name = "vpn_service_date_expiry", length = 30, nullable = false)
    private LocalDate dateExpiry;

    public static VPNServiceBuilder builder() {
        return new VPNServiceBuilder();
    }

    public VPNService() {}

    public VPNService(long id, String name, String server, String password, LocalDate dateExpiry) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.password = password;
        this.dateExpiry = dateExpiry;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateExpiry() {
        return dateExpiry;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateExpiry(LocalDate dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VPNService that = (VPNService) o;
        return id == that.id &&
                name.equals(that.name) &&
                server.equals(that.server) &&
                password.equals(that.password) &&
                dateExpiry.equals(that.dateExpiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, server, password, dateExpiry);
    }

    @Override
    public String toString() {
        return "VPNService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", password='" + password + '\'' +
                ", dateExpiry=" + dateExpiry +
                '}';
    }
}
