package pers.mrsli.springboot.core.sys.service.impl;

import cn.omtech.countyservice.common.response.PageResult;
import cn.omtech.countyservice.core.modules.sys.dto.UserDto;
import cn.omtech.countyservice.core.modules.sys.entity.Office;
import cn.omtech.countyservice.core.modules.sys.entity.User;
import cn.omtech.countyservice.core.modules.sys.entity.UserOffice;
import cn.omtech.countyservice.core.modules.sys.mapper.UserMapper;
import cn.omtech.countyservice.core.modules.sys.mapper.UserOfficeMapper;
import cn.omtech.countyservice.core.modules.sys.query.UserQuery;
import cn.omtech.countyservice.core.modules.sys.service.IOfficeService;
import cn.omtech.countyservice.core.modules.sys.service.IUserRoleService;
import cn.omtech.countyservice.core.modules.sys.service.IUserService;
import cn.omtech.countyservice.core.modules.sys.vo.UserVo;
import cn.omtech.countyservice.core.modules.thirdparty.dto.UserInfo;
import cn.omtech.countyservice.core.utils.AESUtil;
import cn.omtech.countyservice.core.utils.Md5Util;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserOfficeMapper userOfficeMapper;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IOfficeService officeService;

    @Override
    public User getByUsername(String username){
        return userMapper.getByUsername(username);
    }

    @Autowired
    Environment env;

    @Override
    public void delete(String uuid){
        User user = userMapper.getByUuid(uuid);
        if(user != null){
            userMapper.deleteById(user.getId());
            //清除用户与角色关联关系
            userRoleService.deleteByUserId(user.getId());
        }
    }

    @Override
    public void delete(long userId){
        userMapper.deleteById(userId);
        //清除用户与角色关联关系
        userRoleService.deleteByUserId(userId);
    }

    @Override
    public PageResult<List<UserVo>> pageList(UserQuery query){
        PageResult<List<UserVo>> pagination = new PageResult<>();
        query.setLimit((query.getPageNo() - 1) * query.getPageSize());
        List<User> users = userMapper.pagelist(query);
        pagination.setPage(query.getPageNo());
        pagination.setPageSize(query.getPageSize());
        pagination.setTotals(userMapper.count(query));

        List<UserVo> vos = new ArrayList<>();
        users.forEach(m->{
            UserVo v = new UserVo();
            BeanUtils.copyProperties(m, v);
            vos.add(v);
        });
        pagination.setData(vos);
        return pagination;
    }

    @Override
    public User getByMobile(String mobile){
        return userMapper.getByMobile(mobile);
    }

    @Override
    public void recoverByUuid(String uuid){

    }

    @Override
    public User getByUuid(String uuid){
        return userMapper.getByUuid(uuid);
    }

    @Override
    public void syncUser(UserInfo userInfo){
        User user;
        if(userInfo.getUserId() == null){
            userMapper.deleteByUuid(userInfo.getUuid());
            user = new User();
            user.setRealname(userInfo.getRealname());
            user.setMobile(userInfo.getMobile());
            //            user.setCreateBy(UserUtil.getUser().getId());
            user.setCreateTime(new Date());
            user.setUuid(userInfo.getUuid());
            user.setUsername(userInfo.getUsername());
            userMapper.insert(user);
        }else{
            user = userMapper.selectById(userInfo.getUserId());
            user.setRealname(userInfo.getRealname());
            user.setMobile(userInfo.getMobile());
            //            user.setUpdateBy(UserUtil.getUser().getId());
            user.setUpdateTime(new Date());
            //            u.setPwd(pwdEncrypt);
            user.setUsername(userInfo.getUsername());
            userMapper.update(user);
            userOfficeMapper.deleteByUserId(user.getId());
        }

        if(StringUtils.isNotBlank(userInfo.getOfficeUuids())){
            String[] officeUuids = userInfo.getOfficeUuids().split(",");
            for(String uuid : officeUuids){
                UserOffice userOffice = new UserOffice();
                userOffice.setUserId(user.getId());
                userOffice.setUserUuid(user.getUuid());
                Office office = officeService.getByUuid(uuid);
                if(office != null){
                    userOffice.setOfficeId(office.getId());
                }
                userOffice.setOfficeUuid(uuid);
                userOfficeMapper.insert(userOffice);
            }
        }

        userRoleService.save(user.getId(), userInfo.getRoleIds());
    }

    @Override
    public boolean save(Long operUserId, UserDto user){
        User u;
        String pwdTmp = Md5Util.MD5(user.getPwd());

        String sKey = Md5Util.MD5("nim").substring(0, 16);

        String pwdEncrypt = AESUtil.encrypt(pwdTmp, sKey).substring(0, 16);

        if(user.getUserId() != null){
            u = userMapper.selectById(user.getUserId());
            u.setRealname(user.getRealname());
            u.setMobile(user.getMobile());
            u.setUpdateBy(operUserId);
            u.setUpdateTime(new Date());
            //            u.setPwd(pwdEncrypt);
            userMapper.update(u);
        }else{
            u = new User();
            u.setRealname(user.getRealname());
            u.setMobile(user.getMobile());
            u.setCreateBy(operUserId);
            u.setCreateTime(new Date());
            userMapper.insert(u);
        }
        return true;
    }

    @Override
    public void updateLoginInfo(User user){
        userMapper.updateLogin(user);
    }
}
