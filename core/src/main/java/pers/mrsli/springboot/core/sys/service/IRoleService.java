package pers.mrsli.springboot.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.mrsli.common.response.PageResult;
import pers.mrsli.springboot.core.sys.dto.RoleDto;
import pers.mrsli.springboot.core.sys.entity.Role;
import pers.mrsli.springboot.core.sys.query.RoleQuery;

import java.util.List;

/**
 * 角色Service接口
 */
public interface IRoleService extends IService<Role> {

    Role getById(long id);

    PageResult pageList(RoleQuery query);

    void deleteById(long id);

    void save(RoleDto role);

    boolean existName(Long id, String name);

    List<Role> getAll();

    List<Role> getByUserId(long userId);
}
