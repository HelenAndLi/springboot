package pers.mrsli.springboot.core.sys.service.impl;

import cn.omtech.countyservice.core.modules.sys.dto.RoleMenuDto;
import cn.omtech.countyservice.core.modules.sys.entity.Menu;
import cn.omtech.countyservice.core.modules.sys.entity.RoleMenu;
import cn.omtech.countyservice.core.modules.sys.mapper.RoleMenuMapper;
import cn.omtech.countyservice.core.modules.sys.mapper.UserMapper;
import cn.omtech.countyservice.core.modules.sys.service.IRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色菜单权限Service实现类
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Set<String> getByUsername(String username){
        List<Menu> menuList = roleMenuMapper.getByUsername(username);
        Set<String> permissions = new HashSet<>();
        menuList.forEach(m->permissions.add(m.getName()));

        return permissions;
    }

    @Override
    public List<Long> getMenuIdsByRoleId(long roleId){
        return roleMenuMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(long roleId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        roleMenuMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByMenuId(long menuId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("menu_id", menuId);
        roleMenuMapper.delete(queryWrapper);
    }

    @Override
    public void save(RoleMenuDto roleMenu){
        deleteByRoleId(roleMenu.getRoleId());
        if(StringUtils.isBlank(roleMenu.getMenuIds())){
            return;
        }
        List<String> menuIds = Arrays.asList(roleMenu.getMenuIds().split(","));
        if(menuIds != null && menuIds.size() > 0){
            menuIds.forEach(m->{
                roleMenuMapper.insert(new RoleMenu(roleMenu.getRoleId(), Long.valueOf(m)));
            });
        }else{
            roleMenuMapper.insert(new RoleMenu(roleMenu.getRoleId(), roleMenu.getMenuId()));
        }
    }
}
