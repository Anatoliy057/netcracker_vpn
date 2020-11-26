package com.example.netcracker_vpn.controllers;

import com.example.netcracker_vpn.domain.VPNServiceDTO;
import com.example.netcracker_vpn.exceptions.ServerAlreadyExists;
import com.example.netcracker_vpn.services.VPNServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    VPNServicesService servicesService;

    @PostMapping("/note")
    @ResponseBody
    public Collection<VPNServiceDTO.Response.Public> getAllVPNService() {
        return servicesService.getAll();
    }

    @PutMapping("/note")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addVPNService(VPNServiceDTO.Request.Create request) {
        try {
            servicesService.add(request);
            return ResponseEntity.ok().build();
        } catch (ServerAlreadyExists e) {
            return ResponseEntity.badRequest().body(e.getParams());
        }
    }

    @PostMapping("/note/{id}")
    @ResponseBody
    public VPNServiceDTO.Response.Public getVPNService(@PathVariable long id) {
        Optional<VPNServiceDTO.Response.Public> serviceDTO = servicesService.getById(id);
        return serviceDTO.orElse(null);
    }

    @PostMapping("/note/update/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateVPNService(@PathVariable long id,VPNServiceDTO.Request.Create request) {
        try {
            servicesService.update(id, request);
            return ResponseEntity.ok().build();
        } catch (ServerAlreadyExists e) {
            return ResponseEntity.badRequest().body(e.getParams());
        }
    }

    @DeleteMapping("/note/{id}")
    @ResponseBody
    public void updateVPNService(@PathVariable long id) {
        servicesService.delete(id);
    }

    @GetMapping("/note")
    public String getNotes() {
        return "notes";
    }

    @GetMapping("/note/{id}")
    public String getNote(@PathVariable long id) {
        return "note";
    }
}
