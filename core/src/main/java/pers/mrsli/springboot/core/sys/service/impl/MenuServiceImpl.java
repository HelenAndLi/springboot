package pers.mrsli.springboot.core.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mrsli.springboot.core.sys.dto.MenuDto;
import pers.mrsli.springboot.core.sys.dto.MenuSimpleDto;
import pers.mrsli.springboot.core.sys.dto.MenuTreeDto;
import pers.mrsli.springboot.core.sys.entity.Menu;
import pers.mrsli.springboot.core.sys.mapper.MenuMapper;
import pers.mrsli.springboot.core.sys.service.IMenuService;
import pers.mrsli.springboot.core.sys.service.IRoleMenuService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<Menu> getBaseByUserId(long userId){
        if(userId == 1){
            return menuMapper.getAll();
        }
        return menuMapper.getByUserId(userId);
    }

    @Override
    public List<Menu> getAll(){
        return menuMapper.getAll();
    }

    @Override
    public boolean save(Long operUserId, MenuDto menu){
        if(menu.getPid() == null){
            menu.setPid(0L);
        }
        Menu m;
        long oldPid = 0L;
        if(menu.getId() != null){
            m = menuMapper.selectById(menu.getId());
            m.setName(menu.getName());
            m.setPermission(menu.getPermission());
            m.setUri(menu.getUri());
            m.setDisplay(menu.isDisplay());
            m.setUpdateBy(operUserId);
            m.setUpdateTime(new Date());
            m.setIcon(menu.getIcon());
            m.setTarget(menu.getTarget());
            m.setSort(menu.getSort());
            m.setDisplay(menu.isDisplay());
            m.setAppSort(menu.getAppMenuSort());
            m.setAppClass(menu.getAppClass());
            m.setAppJump(menu.getAppJump());
            m.setAppUrl(menu.getAppUrl());
            m.setAppBeforeIcon(menu.getAppBeforeIcon());
            m.setAppAfterIcon(menu.getAppAfterIcon());
            m.setOpenType(menu.getOpenType());
            oldPid = m.getPid();
        }else{
            m = new Menu();
            m.setName(menu.getName());
            m.setPermission(menu.getPermission());
            m.setUri(menu.getUri());
            m.setDisplay(menu.isDisplay());
            m.setCreateBy(operUserId);
            m.setCreateTime(new Date());
            m.setIcon(menu.getIcon());
            m.setTarget(menu.getTarget());
            m.setSort(menu.getSort());
            m.setDisplay(menu.isDisplay());
            m.setAppSort(menu.getAppMenuSort());
            m.setAppClass(menu.getAppClass());
            m.setAppJump(menu.getAppJump());
            m.setAppUrl(menu.getAppUrl());
            m.setAppBeforeIcon(menu.getAppBeforeIcon());
            m.setAppAfterIcon(menu.getAppAfterIcon());
            m.setOpenType(menu.getOpenType());
        }

        if(menu.getPid() != null && menu.getPid() != 0){

            m.setPid(menu.getPid());
            Menu parent = menuMapper.selectById(menu.getPid());
            if(parent == null){
                logger.warn("cannot find parent id:{}", menu.getPid());
                return false;
            }
            String pids = StringUtils.isNotBlank(parent.getPids()) ? parent.getPids() + parent.getId() + "," :
                    parent.getId() + ",";

            m.setPids(pids);
            m.setLevel(parent.getLevel() + 1);

        }else{
            m.setPid(0);
            m.setPids("");
            m.setLevel(1);
        }

        if(menu.getId() != null){
            menuMapper.updateById(m);

            //是修改，而且pid发生变化了
            if(oldPid != m.getPid()){
                //子树要调整
                updatePTree(operUserId, m.getId(), m.getPids());

                updateHasChildren(operUserId, m.getPid());
                updateHasChildren(operUserId, oldPid);
            }
        }else{
            menuMapper.insert(m);
            updateHasChildren(operUserId, m.getPid());
        }
        return true;
    }

    /**
     * 改了父节点，且本身存在子树的，其子树的pids需要更新
     *
     * @param id
     *         当前节点id
     * @param pids
     *         父节点id列表
     */
    private void updatePTree(long userId, long id, String pids){

        List<Menu> children = menuMapper.getByPid(id);
        if(children == null || children.size() < 1){
            return;
        }
        for(Menu child : children){
            child.setPids(pids + id + ",");
            child.setUpdateBy(userId);
            child.setUpdateTime(new Date());
            menuMapper.updateById(child);
            if(child.isHasChild()){
                updatePTree(userId, child.getId(), child.getPids());
            }
        }
    }

    private void updateHasChildren(long userId, long id){
        if(id == 0){
            return;
        }
        List<Menu> children = menuMapper.getByPid(id);
        Menu parent = menuMapper.selectById(id);
        if(children == null || children.size() <= 0){
            parent.setHasChild(false);
        }else{
            parent.setHasChild(true);
        }
        parent.setUpdateTime(new Date());
        parent.setUpdateBy(userId);
        menuMapper.updateById(parent);
    }

    @Override
    public void saveSort(List<MenuDto> menus){
        //        menuMapper.updateSorts(menus);
        for(MenuDto menu : menus){
            menuMapper.updateSort(menu);
        }
    }

    /**
     * 删除菜单同时，需删除其菜单子树
     *
     * @param id
     */
    @Override
    public void deleteById(Long userId, long id){
        //检查其父节点是否还存在其他子节点，不存在则更新父节点的hasChild属性

        Menu menu = menuMapper.selectById(id);
        if(menu == null){
            return;
        }
        long pid = menu.getPid();

        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.and(w->w.like("pids", "," + id + ",").or().likeRight("pids", id + ","));

        menuMapper.delete(queryWrapper);
        menuMapper.deleteById(id);

        //清除权限关联关系
        roleMenuService.deleteByMenuId(id);

        if(pid != 0){
            updateHasChildren(userId, pid);
        }
    }

    @Override
    public void deleteBatch(Long userId, List<Long> ids){
        for(Long id : ids){
            deleteById(userId, id);
        }
    }

    @Override
    public Menu getById(long id){
        return menuMapper.selectById(id);
    }

    @Override
    public List<Menu> getByPid(Long pid){
        if(pid == null){
            pid = 0L;
        }
        List<Menu> menuList = menuMapper.getByPid(pid);

        return menuList;
    }

    @Override
    public List<MenuSimpleDto> getByUserId(long userId){
        List<Menu> menus = menuMapper.getByUserId(userId);
        List<MenuSimpleDto> vos = new ArrayList<>();
        for(Menu menu : menus){
            MenuSimpleDto vo = new MenuSimpleDto();
            vo.setId(menu.getId());
            vo.setpId(menu.getPid());
            vo.setName(menu.getName());
            vos.add(vo);
        }
        return vos;
    }


    @Override
    public List<MenuTreeDto> homePageMenu(boolean isAdmin,long userId){
        List<MenuTreeDto> vos;
        if(isAdmin){
            vos = getByPid(null, 0L);
            recursionMenuList(null, vos);
            return vos;
        }else{
            vos = getByPid(userId, 0L);
            recursionMenuList(userId, vos);
            return vos;
        }

    }

    private List<MenuTreeDto> getByPid(Long userId, long pid){
        List<Menu> parents;
        if(userId != null){
            parents = menuMapper.getByUserIdAndPid(userId, pid);
        }else{
            parents = menuMapper.getByPid(pid);
        }

        List<MenuTreeDto> vos = new ArrayList<>();
        for(Menu menu : parents){
            MenuTreeDto vo = new MenuTreeDto();
            vo.setId(menu.getId());
            vo.setpId(menu.getPid());
            vo.setName(menu.getName());
//            vo.setIcon(menu.getIcon());
//            vo.setHaveChild(menu.isHasChild());
//            vo.setUri(menu.getUri());
            vos.add(vo);
        }
        return vos;
    }

    private void recursionMenuList(Long userId, List<MenuTreeDto> menuList){
        for(MenuTreeDto menu : menuList){
            List<MenuTreeDto> children = getByPid(userId, menu.getId());

            if(children.size() > 0){
//                menu.setChildren(children);
                recursionMenuList(userId, children);
            }
        }
    }
}
