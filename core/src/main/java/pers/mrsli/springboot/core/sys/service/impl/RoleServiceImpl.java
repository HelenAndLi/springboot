package pers.mrsli.springboot.core.sys.service.impl;

import cn.omtech.countyservice.common.response.PageResult;
import cn.omtech.countyservice.core.modules.sys.dto.RoleDto;
import cn.omtech.countyservice.core.modules.sys.entity.Role;
import cn.omtech.countyservice.core.modules.sys.mapper.RoleMapper;
import cn.omtech.countyservice.core.modules.sys.query.RoleQuery;
import cn.omtech.countyservice.core.modules.sys.service.IRoleMenuService;
import cn.omtech.countyservice.core.modules.sys.service.IRoleService;
import cn.omtech.countyservice.core.modules.sys.service.IUserRoleService;
import cn.omtech.countyservice.core.modules.sys.vo.RoleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色Service实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public List<Role> getByUserId(long userId){
        return roleMapper.getByUserId(userId);
    }

    @Override
    public List<Role> getAll(){
        return roleMapper.getAll();
    }

    @Override
    public RoleVo getById(long id){
        Role role = roleMapper.selectById(id);
        RoleVo vo = new RoleVo();
        BeanUtils.copyProperties(role, vo);
        return vo;
    }

    @Override
    public PageResult pageList(RoleQuery query){
        PageResult pagination = new PageResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotBlank(query.getName())){
            queryWrapper.like("name", query.getName());
        }
        Page<Role> page = new Page<>(query.getPageNo(), query.getPageSize());

        IPage iPage = roleMapper.selectPage(page, queryWrapper);
        pagination.setPage(query.getPageNo());
        pagination.setPageSize(query.getPageSize());
        pagination.setTotals(iPage.getTotal());
        List<Role> menuList = iPage.getRecords();
        List<RoleVo> vos = new ArrayList<>();
        menuList.forEach(m->{
            RoleVo v = new RoleVo();
            BeanUtils.copyProperties(m, v);
            vos.add(v);
        });
        pagination.setData(vos);
        return pagination;
    }

    @Override
    public void deleteById(long id){
        roleMapper.deleteById(id);

        //清除用户关联关系
        userRoleService.deleteByRoleId(id);
        //清除权限关联关系
        roleMenuService.deleteByRoleId(id);
    }

    @Override
    public boolean existName(Long id, String name){
        Role role = roleMapper.getByName(name);
        if(role == null || id == null){
            return false;
        }
        if(id != role.getId()){
            return true;
        }
        return false;
    }

    @Override
    public void save(RoleDto dto){
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        if(role.getId() != null){
            //            role.setUpdateBy(UserUtil.getUser().getId());
            role.setUpdateTime(new Date());
            roleMapper.updateById(role);
        }else{
            //            role.setCreateBy(UserUtil.getUser().getId());
            role.setCreateTime(new Date());
            roleMapper.insert(role);
        }

    }
}
