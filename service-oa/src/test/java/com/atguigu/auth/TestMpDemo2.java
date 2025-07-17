package com.atguigu.auth;

import com.atguigu.auth.service.impl.SysRoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

@SpringBootTest
public class TestMpDemo2 {
    @Autowired
    private SysRoleServiceImpl sysRoleMapper;

    @Test
    public void testGetAll() {
        System.out.println(sysRoleMapper.list());
    }

    @Test
    public void testGetOne() {
        System.out.println(sysRoleMapper.getOne(null));
    }


}
