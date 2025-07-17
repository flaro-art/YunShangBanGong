package com.atguigu.auth.service.impl;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private  SysUserRoleServiceImpl sysUserRoleService;
    @Override
    public Map<String, Object> findRoelDataByUserId(Long userId) {
        // 1.查询所有角色，返回list集合
        List<SysRole> allSysRoles = baseMapper.selectList(null);
        // 2.根据userId查询用户角色关系表，查询userId拥有的角色id
        LambdaQueryWrapper<SysUserRole> sysUserRoleWrapper = new LambdaQueryWrapper<>();
        sysUserRoleWrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> exitUserRoleList = sysUserRoleService.list(sysUserRoleWrapper);
        List<Long> exitRoleList =
                exitUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        // 3.根据角色id，查询角色对象信息
        List<SysRole> assingRoleList = new ArrayList<SysRole>();
//        allSysRoles.forEach(allSysRole -> {
//            if (exitRoleList.contains(allSysRole.getId())){
//                assingRoleList.add(allSysRole);
//            }
//        });
        for (SysRole allSysRole : allSysRoles) {
            if (exitRoleList.contains(allSysRole.getId())){
                assingRoleList.add(allSysRole);
            }
        }
        Map<String, Object> roleMap=new HashMap<>();
        roleMap.put("assginRoleList",assingRoleList);
        roleMap.put("allRolesList",allSysRoles);
        return roleMap;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //删除用户已经分配的角色
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId,assginRoleVo.getUserId());
        sysUserRoleService.remove(queryWrapper);

        //重新进行分配
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (StringUtils.isEmpty(roleId)){
                continue;
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleService.save(sysUserRole);
        }
    }
}
