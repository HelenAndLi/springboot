package pers.mrsli.springboot.core.sys.dto;

import java.util.List;

public class UserRoleDto {
    private Long userId;
    private Long roleId;
    private List<Long> roleIds;

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getRoleId(){
        return roleId;
    }

    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    public List<Long> getRoleIds(){
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds){
        this.roleIds = roleIds;
    }
}
