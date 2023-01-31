package pers.mrsli.springboot.core.sys.dto;

public class RoleUserDto {

    private long userId;

    private String username;

    private String realname;

    private long roleId;

    public long getUserId(){
        return userId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getRealname(){
        return realname;
    }

    public void setRealname(String realname){
        this.realname = realname;
    }

    public long getRoleId(){
        return roleId;
    }

    public void setRoleId(long roleId){
        this.roleId = roleId;
    }
}
