package pers.mrsli.springboot.core.sys.dto;

public class RoleMenuDto {

    private long roleId;

    private long menuId;

    private String menuIds;

    public long getRoleId(){
        return roleId;
    }

    public void setRoleId(long roleId){
        this.roleId = roleId;
    }

    public long getMenuId(){
        return menuId;
    }

    public void setMenuId(long menuId){
        this.menuId = menuId;
    }

    public String getMenuIds(){
        return menuIds;
    }

    public void setMenuIds(String menuIds){
        this.menuIds = menuIds;
    }
}
