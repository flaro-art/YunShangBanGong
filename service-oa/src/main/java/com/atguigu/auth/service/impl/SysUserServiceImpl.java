package com.atguigu.auth.service.impl;

import com.atguigu.model.system.SysUser;
import com.atguigu.auth.mapper.SysUserMapper;
import com.atguigu.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2025-07-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public void updateStatus(Long id, Integer status) {
        // 根据id查询用户信息
        SysUser sysUser = baseMapper.selectById(id);
        // 设置修改状态值
        if (status == 1|| status == 0){
            sysUser.setStatus(status);
        }
        // 调用方法修改用户状态
        baseMapper.updateById(sysUser);
    }
}
