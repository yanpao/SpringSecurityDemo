package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.Ref_Privilege_RoleMapper;
import com.wismap.springsecuritydemo.mapper.Ref_User_RoleMapper;
import com.wismap.springsecuritydemo.mapper.ResourceMapper;
import com.wismap.springsecuritydemo.mapper.RoleMapper;
import com.wismap.springsecuritydemo.model.*;
import com.wismap.springsecuritydemo.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements IRoleService {

    private RoleMapper roleMapper;
    private ResourceMapper resourceMapper;
    private Ref_Privilege_RoleMapper ref_privilege_roleMapper;
    private Ref_User_RoleMapper ref_user_roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper,
                           ResourceMapper resourceMapper,
                           Ref_Privilege_RoleMapper ref_privilege_roleMapper,
                           Ref_User_RoleMapper ref_user_roleMapper)
    {
        this.roleMapper=roleMapper;
        this.resourceMapper=resourceMapper;
        this.ref_privilege_roleMapper=ref_privilege_roleMapper;
        this.ref_user_roleMapper=ref_user_roleMapper;
    }

    public Map<String,Object> selectALL(Long limit, Long offset, String roleName, String roleNameLocal)
    {
        List<Role> all=roleMapper.selectALL(limit,offset,roleName,roleNameLocal);
        Integer count = roleMapper.selectAllCount(roleName,roleNameLocal);
        Map<String,Object> result = new HashMap<>();
        result.put("count",count);
        result.put("roles",all);
        return result;
    }

    public List<Long> getRolePri(Long roleid,Long pritype)
    {
        return ref_privilege_roleMapper.selectPriByRoleid(roleid,pritype);
    }

    public Long insert(Role record)
    {
        Date date=new Date();
        record.setGenTime(date);
        return roleMapper.insert(record);
    }

    public Long delete(Long id)
    {
        Long deleteCount=0L;
        List<Long> allPriIds =ref_privilege_roleMapper.selectPriByRoleid(id,null);
        for (Long priid:allPriIds)
        {
            ref_privilege_roleMapper.delete(id,priid);
            deleteCount++;
        }
        deleteCount+=roleMapper.delete(id);
        deleteCount+=ref_user_roleMapper.deleteByRoleId(id);

        return deleteCount;
    }

    public Boolean Update(Role role)
    {
        if(roleMapper.update(role)>0)
            return true;
        else
            return false;
    }

    public Boolean Authorize(Long roleID,Long[] privilegeIds)
    {
        try{
            ref_privilege_roleMapper.deleteByRoleId(roleID);
            for(Long priID : privilegeIds)
            {
                Ref_Privilege_Role ref_privilege_role = ref_privilege_roleMapper.select(priID,roleID);//避免重复授权
                if (ref_privilege_role==null)
                {
                    ref_privilege_role=new Ref_Privilege_Role(priID,roleID);
                    ref_privilege_roleMapper.insert(ref_privilege_role);
                }
            }
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public Boolean Revoke(Long roleID,Long[] privilegeIds)
    {
        try{
            for(Long priID : privilegeIds)
            {
                ref_privilege_roleMapper.delete(roleID,priID);
            }
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

}
