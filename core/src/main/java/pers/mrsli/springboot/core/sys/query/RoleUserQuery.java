package pers.mrsli.springboot.core.sys.query;

import cn.omtech.countyservice.common.query.BaseQuery;

public class RoleUserQuery extends BaseQuery {
    private String realname;
    private String username;
    private Long roleId;

    public Long getRoleId(){
        return roleId;
    }

    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    public String getRealname(){
        return realname;
    }

    public void setRealname(String realname){
        this.realname = realname;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
