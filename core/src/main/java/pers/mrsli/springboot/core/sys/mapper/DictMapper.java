package pers.mrsli.springboot.core.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pers.mrsli.springboot.core.sys.entity.Dict;
import pers.mrsli.springboot.core.sys.query.DictQuery;

import java.util.List;

public interface DictMapper extends BaseMapper<Dict> {

    List<Dict> all();

    List<Dict> pagelist(DictQuery query);

    int count(DictQuery query);

    Dict getByTypeAndLabel(@Param("type") String type, @Param("label") String label);

    List<String> allTypes();

    Dict getValue(@Param("type") String type, @Param("label") String label);
}
