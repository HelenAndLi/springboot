package pers.mrsli.springboot.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.mrsli.common.response.PageResult;
import pers.mrsli.springboot.core.sys.dto.UserDto;
import pers.mrsli.springboot.core.sys.entity.User;
import pers.mrsli.springboot.core.sys.query.UserQuery;

/**
 * 用户Service接口
 */
public interface IUserService extends IService<User> {


    User getByUsername(String username);

    void updateLoginInfo(User user);

    boolean save(Long operUserId, UserDto user);

    void delete(long userId);

    void delete(String uuid);

    PageResult pageList(UserQuery query);

    User getByMobile(String mobile);

    void recoverByUuid(String uuid);

    User getByUuid(String uuid);
}
