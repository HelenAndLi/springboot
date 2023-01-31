package pers.mrsli.springboot.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.mrsli.common.response.PageResult;
import pers.mrsli.springboot.core.sys.dto.DictDto;
import pers.mrsli.springboot.core.sys.entity.Dict;
import pers.mrsli.springboot.core.sys.query.DictQuery;

import java.util.List;

public interface IDictService extends IService<Dict> {

    boolean exist(Long id, String type, String label);

    PageResult<DictDto> pagelist(DictQuery query);

    List<String> allTypes();

    void save(DictDto vo);

    void delete(List<Long> ids);

    void delete(Long id);
}
