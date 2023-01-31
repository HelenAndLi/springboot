package pers.mrsli.springboot.core.sys.mapper;

import cn.omtech.countyservice.core.modules.sys.entity.User;
import cn.omtech.countyservice.core.modules.sys.query.UserQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 用户Mapper
 */
public interface UserMapper extends BaseMapper<User> {

    void updateLogin(User user);

    void deleteByUuid(String uuid);

    User getByUsername(String username);

    User getByMobile(String mobile);

    User getByUuid(String uuid);

    void update(User user);

    void updatePwd(User user);

    List<User> pagelist(UserQuery query);

    Long count(UserQuery query);

    void recoverByUuid(String uuid);
}
