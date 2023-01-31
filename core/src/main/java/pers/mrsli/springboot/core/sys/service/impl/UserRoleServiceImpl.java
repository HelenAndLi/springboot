package pers.mrsli.springboot.core.sys.service.impl;

import cn.omtech.countyservice.common.response.PageResult;
import cn.omtech.countyservice.core.modules.sys.bo.UserRoleBo;
import cn.omtech.countyservice.core.modules.sys.dto.RoleUserDto;
import cn.omtech.countyservice.core.modules.sys.entity.Role;
import cn.omtech.countyservice.core.modules.sys.entity.UserRole;
import cn.omtech.countyservice.core.modules.sys.mapper.UserMapper;
import cn.omtech.countyservice.core.modules.sys.mapper.UserRoleMapper;
import cn.omtech.countyservice.core.modules.sys.query.RoleUserQuery;
import cn.omtech.countyservice.core.modules.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户与角色关联Service实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<RoleUserDto> pagelist(long roleId, RoleUserQuery query){
        PageResult pagination = new PageResult<>();
        pagination.setPage(query.getPageNo());
        pagination.setPageSize(query.getPageSize());

        query.setLimit((query.getPageNo() - 1) * query.getPageSize());
        query.setRoleId(query.getRoleId());
        pagination.setTotals(userRoleMapper.count(query));
        if(pagination.getTotals() == 0){
            return pagination;
        }
        pagination.setData(userRoleMapper.pagelist(query));

        return pagination;
    }

    @Override
    public List<Long> getByUserId(long userId){
        List<Long> roleIds = userRoleMapper.getRoleIdsByUserId(userId);
        if(roleIds == null){
            return new ArrayList<>(1);
        }
        return roleIds;
    }

    @Override
    public Set<String> getByUsername(String username){
        List<Role> roles = userRoleMapper.getByUsername(username);
        Set<String> roleIds = new HashSet<>();
        roles.forEach(r->{
            roleIds.add(r.getName());
        });

        return roleIds;
    }

    @Override
    public void deleteByUserId(long userId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        userRoleMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByRoleId(long roleId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        userRoleMapper.delete(queryWrapper);
    }

    @Override
    public void save(long userId, UserRoleBo userRole){
        //清空已有关系
        deleteByUserId(userId);

        List<Long> roleIds = userRole.getRoleIds();
        if(roleIds != null && roleIds.size() > 0){
            //批量添加
            for(Long roleId : roleIds){
                userRoleMapper.insert(new UserRole(userId, roleId));
            }
        }else{
            userRoleMapper.insert(new UserRole(userId, userRole.getRoleId()));
        }
    }

    @Override
    public void save(long userId, List<String> roleIds){
        //清空已有关系
        deleteByUserId(userId);

        if(roleIds != null && roleIds.size() > 0){
            //批量添加
            for(String roleId : roleIds){
                userRoleMapper.insert(new UserRole(userId, Long.valueOf(roleId)));
            }
        }
    }
}
