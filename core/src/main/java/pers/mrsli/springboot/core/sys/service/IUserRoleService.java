package pers.mrsli.springboot.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.mrsli.common.response.PageResult;
import pers.mrsli.springboot.core.sys.dto.RoleUserDto;
import pers.mrsli.springboot.core.sys.dto.UserRoleDto;
import pers.mrsli.springboot.core.sys.entity.UserRole;
import pers.mrsli.springboot.core.sys.query.RoleUserQuery;

import java.util.List;
import java.util.Set;

/**
 * 用户与角色关联Service接口
 */
public interface IUserRoleService extends IService<UserRole> {

    Set<String> getByUsername(String username);

    List<Long> getByUserId(long userId);

    void deleteByUserId(long userId);

    void deleteByRoleId(long roleId);

    PageResult<RoleUserDto> pagelist(long roleId, RoleUserQuery query);

    void save(long userId, UserRoleDto userRoleDto);

    void save(long userId, List<String> roleIds);
}
