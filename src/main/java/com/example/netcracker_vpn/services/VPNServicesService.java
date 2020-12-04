package com.example.netcracker_vpn.services;

import com.example.netcracker_vpn.domain.entities.VPNService;
import com.example.netcracker_vpn.domain.dto.VPNServiceDTO;
import com.example.netcracker_vpn.exceptions.ServerAlreadyExistsException;
import com.example.netcracker_vpn.exceptions.ServiceNotFoundByIdException;
import com.example.netcracker_vpn.repos.VPNServicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class VPNServicesService {

    private final VPNServicesRepo servicesRepo;

    @Autowired
    public VPNServicesService(VPNServicesRepo servicesRepo) {
        this.servicesRepo = servicesRepo;
    }

    public Collection<VPNServiceDTO.Response.Public> getAll() {
        Collection<VPNServiceDTO.Response.Public> response = new ArrayList<>();
        for (VPNService service :
                servicesRepo.findAll()) {
            response.add(VPNServiceDTO.Response.Public.fromEntity(service));
        }
        return response;
    }

    public VPNServiceDTO.Response.Public getById(long id) throws ServiceNotFoundByIdException {
        return VPNServiceDTO.Response.Public.fromEntity(
                servicesRepo.findById(id)
                .orElseThrow(() -> new ServiceNotFoundByIdException(id))
        );
    }

    public VPNServiceDTO.Response.Public add(VPNServiceDTO.Request.Create request) throws ServerAlreadyExistsException {
        if (servicesRepo.existsByServer(request.getServer())) {
            throw new ServerAlreadyExistsException(request.getServer());
        }

        VPNService service = VPNServiceDTO.Request.Create.toEntity(request);
        servicesRepo.save(service);

        return VPNServiceDTO.Response.Public.fromEntity(service);
    }

    public void delete(long id) throws ServiceNotFoundByIdException {
        if (servicesRepo.existsById(id)) {
            servicesRepo.deleteById(id);
        } else {
            throw new ServiceNotFoundByIdException(id);
        }
    }

    public VPNServiceDTO.Response.Public update(long id, VPNServiceDTO.Request.Create request) throws ServiceNotFoundByIdException {
        Optional<VPNService> optionalService = servicesRepo.findById(id);

        if (!optionalService.isPresent()) {
            throw new ServiceNotFoundByIdException(id);
        }

        VPNService service = optionalService.get();
        VPNServiceDTO.Request.Create.merge(request, service);
        servicesRepo.save(service);

        return VPNServiceDTO.Response.Public.fromEntity(service);
    }
}
