package pers.mrsli.springboot.core.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.mrsli.springboot.core.sys.dto.RoleUserDto;
import pers.mrsli.springboot.core.sys.entity.Role;
import pers.mrsli.springboot.core.sys.entity.User;
import pers.mrsli.springboot.core.sys.entity.UserRole;
import pers.mrsli.springboot.core.sys.query.RoleUserQuery;

import java.util.List;

/**
 * 用户和角色关联Mapper
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<User> getByRoleId(String roleId);

    List<Role> getByUserId(long userId);

    List<Long> getRoleIdsByUserId(long userId);

    List<RoleUserDto> pagelist(RoleUserQuery query);

    int count(RoleUserQuery query);

    List<Role> getByUsername(String username);

}
