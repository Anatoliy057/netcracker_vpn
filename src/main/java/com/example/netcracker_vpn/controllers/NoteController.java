package com.example.netcracker_vpn.controllers;

import com.example.netcracker_vpn.domain.dto.VPNServiceDTO;
import com.example.netcracker_vpn.exceptions.ServerAlreadyExistsException;
import com.example.netcracker_vpn.exceptions.ServiceNotFoundByIdException;
import com.example.netcracker_vpn.services.VPNServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class NoteController {

    private final VPNServicesService servicesService;

    @Autowired
    public NoteController(VPNServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @PostMapping("/note")
    @ResponseBody
    public Collection<VPNServiceDTO.Response.Public> getAllVPNService() {
        return servicesService.getAll();
    }

    @PutMapping("/note")
    @ResponseBody
    public VPNServiceDTO.Response.Public addVPNService(@Valid @RequestBody VPNServiceDTO.Request.Create request) throws ServerAlreadyExistsException {
        return servicesService.add(request);
    }

    @PostMapping("/note/{id}")
    @ResponseBody
    public VPNServiceDTO.Response.Public getVPNService(@PathVariable long id) throws ServiceNotFoundByIdException {
        return servicesService.getById(id);
    }

    @PostMapping("/note/update/{id}")
    @ResponseBody
    public VPNServiceDTO.Response.Public updateVPNService(@PathVariable long id, @RequestBody VPNServiceDTO.Request.Create request) throws ServiceNotFoundByIdException {
        return(servicesService.update(id, request));
    }

    @DeleteMapping("/note/{id}")
    @ResponseBody
    public void deleteVPNServiceById(@PathVariable long id) throws ServiceNotFoundByIdException {
        servicesService.delete(id);
    }

    @GetMapping("/note")
    public String getIndex() {
        return "app";
    }

    @GetMapping("/note/{id}")
    public String getNote(@PathVariable long id) {
        return "app";
    }
}
