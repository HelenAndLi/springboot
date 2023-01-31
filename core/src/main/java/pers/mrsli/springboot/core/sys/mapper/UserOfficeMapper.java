package pers.mrsli.springboot.core.sys.mapper;

import cn.omtech.countyservice.core.modules.sys.entity.Office;
import cn.omtech.countyservice.core.modules.sys.entity.UserOffice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserOfficeMapper extends BaseMapper<UserOffice> {

    void deleteByUserId(long userId);

    List<UserOffice> getByUserId(long userId);

    List<String> getOffUuidByUserId(long userId);

    List<Office> getByUserUuid(String userUuid);
}
