package com.example.netcracker_vpn.repo;

import com.example.netcracker_vpn.domain.VPNService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VPNServicesRepo extends CrudRepository<VPNService, Long> {

}
