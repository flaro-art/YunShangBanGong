package com.atguigu.auth;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo1 {

    private final SysRoleMapper sysRoleMapper;

    @Autowired
    private TestMpDemo1(SysRoleMapper sysRoleMapper){
        this.sysRoleMapper = sysRoleMapper;
    }

   @Test
    public void getAll() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        System.out.println(sysRoles);
    }

    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");
        int insert = sysRoleMapper.insert(sysRole);
        System.out.println(insert);
        System.out.println(sysRole.getId());

    }

    @Test
    public void deleteID() {
        int delete = sysRoleMapper.deleteById(9);
        System.out.println(delete);
    }

    @Test
    public void testQuery1() {
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_name", "角色管理员");
        List<SysRole> sysRoles = sysRoleMapper.selectList(sysRoleQueryWrapper);
        System.out.println(sysRoles);
    }
    @Test
    public void testQuery2() {
        LambdaQueryWrapper<SysRole> sysRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysRoleLambdaQueryWrapper.eq(SysRole::getRoleName, "角色管理员");
        List<SysRole> sysRoles = sysRoleMapper.selectList(sysRoleLambdaQueryWrapper);
        System.out.println(sysRoles);
    }
}
