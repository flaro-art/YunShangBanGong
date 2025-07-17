package com.atguigu.auth.utils;

import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuHelper {
    //使用递归构建菜单树形结构
    public static List<SysMenu> buildTree(List<SysMenu> sysMenusList){
        List<SysMenu> trees = new ArrayList<>();;
        for (SysMenu sysMenu : sysMenusList) {
            if(sysMenu.getParentId() ==0){
                trees.add(getChildren(sysMenu,sysMenusList));
            }
        }
        return trees;
    }

    private static SysMenu getChildren(SysMenu sysMenu, List<SysMenu> sysMenusList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for (SysMenu menu : sysMenusList) {
            if (Objects.equals(menu.getParentId(), sysMenu.getId())){
                if (menu.getChildren() == null){
                    menu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildren(menu,sysMenusList));
            }
        }
        return sysMenu;
    }
}
