package com.app.akinportbackend.repository;


import com.app.akinportbackend.domain.SPermission;
import com.app.akinportbackend.domain.SUser;

import java.util.List;

public interface SUserDaoCustom {

    public List<SPermission> findPermissionsByUser(Integer userId);

    public SUser findUserByUserName(String userName);
    
    public List<SUser> findByUserNameOrEmail(String userName, String email);

    public List<SUser> findUsersOrder(String userName, String name, String surname);

    public List<SUser> findByPermission(Integer permissionId);

    public List<SUser> findUsersByRoleId(Integer roleId);
}
