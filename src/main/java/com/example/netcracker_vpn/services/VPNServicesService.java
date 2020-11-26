package com.example.netcracker_vpn.services;

import com.example.netcracker_vpn.domain.VPNService;
import com.example.netcracker_vpn.domain.VPNServiceDTO;
import com.example.netcracker_vpn.exceptions.ServerAlreadyExists;
import com.example.netcracker_vpn.repos.VPNServicesRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VPNServicesService {

    final VPNServicesRepo servicesRepo;

    public VPNServicesService(VPNServicesRepo servicesRepo) {
        this.servicesRepo = servicesRepo;
    }

    public Collection<VPNServiceDTO.Response.Public> getAll() {
        return StreamSupport.stream(servicesRepo.findAll().spliterator(), false)
                .map(VPNServiceDTO.Response.Public::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<VPNServiceDTO.Response.Public> getById(long id) {
        return servicesRepo.findById(id)
                .map(VPNServiceDTO.Response.Public::fromEntity);
    }

    public void add(VPNServiceDTO.Request.Create request) throws ServerAlreadyExists {

        if(servicesRepo.existsByServer(request.getServer())) {
            throw new ServerAlreadyExists(request.getServer());
        }

        servicesRepo.save(VPNServiceDTO.Request.Create.toEntity(request));
    }

    public void delete(long id) {
        servicesRepo.deleteById(id);
    }

    public boolean update(long id, VPNServiceDTO.Request.Create request) throws ServerAlreadyExists {
        Optional<VPNService> optionalService = servicesRepo.findById(id);

        if(optionalService.isPresent()) {

            if(servicesRepo.existsByServer(request.getServer())) {
                throw new ServerAlreadyExists(request.getServer());
            }

            VPNService service = optionalService.get();
            VPNServiceDTO.Request.Create.merge(request, service);
            servicesRepo.save(service);

            return true;
        }

        return false;
    }
}
