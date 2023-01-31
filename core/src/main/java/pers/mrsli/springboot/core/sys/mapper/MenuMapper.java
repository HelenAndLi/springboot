package pers.mrsli.springboot.core.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pers.mrsli.springboot.core.sys.dto.MenuDto;
import pers.mrsli.springboot.core.sys.entity.Menu;

import java.util.List;

/**
 * 菜单Mapper
 */
public interface MenuMapper extends BaseMapper<Menu> {

    //    Menu getParent(long id);

    List<Menu> getByPid(@Param("pid") long pid);

    List<Menu> getAll();

    List<Menu> getByUserId(long userId);

    List<Menu> getByUserIdAndPid(@Param("userId")long userId, @Param("pid")long pid);

    int countByPid(long pid);

    void updateSorts(List<MenuDto> list);

    void updateSort(MenuDto menu);


}
