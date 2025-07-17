package com.atguigu.auth.controller;

import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.auth.service.impl.SysRoleServiceImpl;
import com.atguigu.vo.system.AssginRoleVo;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    private final SysRoleServiceImpl sysRoleServiceImp;

    @Autowired
    public SysRoleController(SysRoleServiceImpl sysRoleServiceImp) {
        this.sysRoleServiceImp = sysRoleServiceImp;
    }


    //1 查询所有角色和获取用户所属角色
    @ApiOperation("获取角色")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
       Map<String, Object> roleData = sysRoleServiceImp.findRoelDataByUserId(userId);
       return Result.ok(roleData);
    }
    //2 为用户分配角色
    @ApiOperation("为用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleServiceImp.doAssign(assginRoleVo);
        return Result.ok() ;
    }

    //统一返回数据结果
    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result findAll() {
        List<SysRole> list = sysRoleServiceImp.list();
        return Result.ok(list);
    }

    @ApiOperation("分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQuery(@PathVariable Long page,
                            @PathVariable Long limit,
                            SysRoleQueryVo sysRoleQueryVo) {
        System.out.println("分页查询参数："+limit);
        //1 创建page对象，传递分页相关参数
        Page<SysRole> pageParam = new Page<>(page, limit);
        //2 封装查询条件，判断是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(SysRole::getRoleName, roleName);
        }
        //3 调用service方法，传入page对象，封装查询条件
        IPage<SysRole> pageModel = sysRoleServiceImp.page(pageParam, wrapper);
        System.out.println("分页查询结果："+pageModel);
        System.out.println("分页查询结果："+pageParam.getRecords());
        return Result.ok(pageModel);
    }

    @ApiOperation("添加角色")
    @PostMapping ("/save")
    public Result save(@RequestBody SysRole sysRole) {
        boolean isSave = sysRoleServiceImp.save(sysRole);
        if (isSave){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    //修改角色-根据id查询
    @ApiOperation("根据id查询")
    @PutMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole sysRole = sysRoleServiceImp.getById(id);
        return Result.ok(sysRole);
    }
    @ApiOperation("修改角色")
    @PutMapping("/update")
    public Result update(@RequestBody SysRole sysRole) {
        boolean isUpdate = sysRoleServiceImp.updateById(sysRole);
        if (isUpdate){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean isRemove = sysRoleServiceImp.removeById(id);
        if (isRemove){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean isBatchRemove = sysRoleServiceImp.removeByIds(idList);
        if (isBatchRemove){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


}
