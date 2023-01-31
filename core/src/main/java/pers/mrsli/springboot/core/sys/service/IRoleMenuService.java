package pers.mrsli.springboot.core.sys.service;

import cn.omtech.countyservice.core.modules.sys.dto.RoleMenuDto;
import cn.omtech.countyservice.core.modules.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 角色与菜单关联Service接口
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 权限列表
     *
     * @param username
     *
     * @return
     */
    Set<String> getByUsername(String username);

    List<Long> getMenuIdsByRoleId(long roleId);

    void deleteByRoleId(long roleId);

    void deleteByMenuId(long menuId);

    void save(RoleMenuDto roleMenu);

}
