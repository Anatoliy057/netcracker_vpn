package com.example.netcracker_vpn.domain.dto;

import com.example.netcracker_vpn.domain.entities.VPNService;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public enum VPNServiceDTO {
    ;

    private interface Id {
        long getId();
    }

    private interface Name {
        String getName();
    }

    private interface Server {
        String getServer();
    }

    private interface Password {
        String getPassword();
    }

    private interface DateExpiry {
        Timestamp getDateExpiry();
    }

    public enum Request {
        ;

        @Value
        public static class Create implements Name, Server, Password, DateExpiry {
            @NotNull
            @Length(min=4, max=30)
            String name;
            @NotNull
            @Length(min=4, max=100)
            String server;
            @NotNull
            @Length(min=4, max=30)
            String password;
            @NotNull
            Timestamp dateExpiry;

            public Create(String name, String server, String password, Timestamp dateExpiry) {
                this.name = name;
                this.server = server;
                this.password = password;
                this.dateExpiry = dateExpiry;
            }

            public static VPNService toEntity(Create request) {
                return new VPNService(
                        request.getName(),
                        request.getServer(),
                        request.getPassword(),
                        request.getDateExpiry()
                );
            }

            public static void merge(Create request, VPNService service) {
                service.setName(request.getName());
                service.setServer(request.getServer());
                service.setPassword(request.getPassword());
                service.setDateExpiry(request.getDateExpiry());
            }
        }
    }

    public enum Response {
        ;

        @Value
        public static class Public implements Id, Name, Server, Password, DateExpiry {
            long id;
            @NotNull
            String name;
            @NotNull
            String server;
            @NotNull
            String password;
            @NotNull
            Timestamp dateExpiry;

            public static Public fromEntity(VPNService service) {
                return new Public(
                        service.getId(),
                        service.getName(),
                        service.getServer(),
                        service.getPassword(),
                        service.getDateExpiry()
                );
            }
        }
    }
}
