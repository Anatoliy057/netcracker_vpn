package com.example.netcracker_vpn.repos;

import com.example.netcracker_vpn.domain.VPNService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VPNServicesRepo extends CrudRepository<VPNService, Long> {

    boolean existsByServer(String server);
}
