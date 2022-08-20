package com.app.akinportbackend.repository;


import com.app.akinportbackend.domain.SPermission;
import com.app.akinportbackend.domain.SRole;

import java.util.List;

public interface UserRoleDaoCustom {
    public List<SRole> findByUser(Integer userId);

    public List<SRole> findByPermission(Integer permissionId);

    public List<SPermission> findPermissionByRole(Integer roleId);
}
