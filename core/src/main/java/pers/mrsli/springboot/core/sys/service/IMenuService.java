package pers.mrsli.springboot.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.mrsli.springboot.core.sys.dto.MenuDto;
import pers.mrsli.springboot.core.sys.dto.MenuSimpleDto;
import pers.mrsli.springboot.core.sys.dto.MenuTreeDto;
import pers.mrsli.springboot.core.sys.entity.Menu;

import java.util.List;

public interface IMenuService extends IService<Menu> {

    boolean save(Long operUserId, MenuDto menu);

    void saveSort(List<MenuDto> menus);

    void deleteById(Long userId, long id);

    void deleteBatch(Long userId, List<Long> ids);

    Menu getById(long id);

    List<Menu> getByPid(Long pid);

    List<MenuSimpleDto> getByUserId(long userId);

    List<Menu> getAll();

    List<Menu> getBaseByUserId(long userId);

    List<MenuTreeDto> homePageMenu(boolean isAdmin, long userId);

}
