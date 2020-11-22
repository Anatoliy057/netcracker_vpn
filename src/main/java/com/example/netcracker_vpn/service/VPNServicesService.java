package com.example.netcracker_vpn.service;

import com.example.netcracker_vpn.domain.VPNService;
import com.example.netcracker_vpn.repo.VPNServicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class VPNServicesService {

    @Autowired
    VPNServicesRepo servicesRepo;

    public Iterable<VPNService> getAll() {
        return servicesRepo.findAll();
    }

    public Optional<VPNService> getById(long id) {
        return servicesRepo.findById(id);
    }
}
