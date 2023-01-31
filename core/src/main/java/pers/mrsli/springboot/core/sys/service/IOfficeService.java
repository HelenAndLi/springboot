package pers.mrsli.springboot.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.mrsli.springboot.core.sys.entity.Office;

import java.util.List;

public interface IOfficeService extends IService<Office> {

    List<Office> getByUserId(Long usesId);

    Office getByUuid(String uuid);

    void deleteByUuid(String uuid);
}
