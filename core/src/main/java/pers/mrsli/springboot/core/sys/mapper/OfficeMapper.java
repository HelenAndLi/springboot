package pers.mrsli.springboot.core.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pers.mrsli.springboot.core.sys.entity.Office;

import java.util.List;

/**
 * 菜单Mapper
 */
public interface OfficeMapper extends BaseMapper<Office> {

    Office getParent(long id);

    List<Office> getByPid(@Param("pid") long pid);

    Office getByUuid(@Param("uuid") String uuid);

    void deleteByUuid(String uuid);
}
