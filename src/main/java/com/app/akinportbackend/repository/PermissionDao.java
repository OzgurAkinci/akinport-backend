package com.app.akinportbackend.repository;

import com.app.akinportbackend.domain.SPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PermissionDao extends JpaRepository<SPermission, Integer> {
    public List<SPermission> findAllByOrderByNameAsc();
    public List<SPermission> findByparentPermissionIsNullOrderByNameAsc();
}
