package com.example.netcracker_vpn.domain;

import lombok.Value;

import java.time.LocalDate;

public enum VPNServiceDTO {;
    private interface Id { long getId(); }
    private interface Name { String getName(); }
    private interface Server { String getServer(); }
    private interface Password { String getPassword(); }
    private interface DateExpiry { LocalDate getDateExpiry(); }

    public enum Request{;
        @Value
        public static class Create implements Name, Server, Password, DateExpiry {
            String name;
            String server;
            String password;
            LocalDate dateExpiry;

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

    public enum Response{;
        @Value public static class Public implements Id, Name, Server, Password, DateExpiry{
            long id;
            String name;
            String server;
            String password;
            LocalDate dateExpiry;

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
