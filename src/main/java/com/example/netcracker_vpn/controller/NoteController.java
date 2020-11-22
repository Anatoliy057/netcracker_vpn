package com.example.netcracker_vpn.controller;

import com.example.netcracker_vpn.domain.VPNService;
import com.example.netcracker_vpn.service.VPNServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    VPNServicesService servicesService;

    @GetMapping("/note")
    public String getAllServices() {
        StringBuilder builder = new StringBuilder();
        builder.append("List of vpn services: <br>");
        builder.append("------------------------<br>");
        for (VPNService service :
                servicesService.getAll()) {
            builder.append("- ");
            builder.append(service.toString());
            builder.append("<br>");
        }
        builder.append("------------------------");
        return builder.toString();
    }

    @GetMapping("/note/{id}")
    public String getService(@PathVariable long id) {
        Optional<VPNService> optionalVPNService = servicesService.getById(id);

        if (optionalVPNService.isPresent()) {
            return optionalVPNService.get().toString();

        } else {
            return String.format("VPN service with given id %d not found", id);
        }
    }
}
