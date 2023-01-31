package pers.mrsli.springboot.core.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.mrsli.springboot.core.sys.entity.Role;

import java.util.List;

/**
 * 角色Mapper
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role getByName(String name);

    List<Role> getAll();

    List<Role> getByUserId(long userId);
}
