package pers.mrsli.springboot.core.sys.dto;

public class UserDto {

    private Long userId;

    private String username;

    private String pwd;

    private String pwdRepeat;

    private String realname;

    private String mobile;

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPwd(){
        return pwd;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public String getPwdRepeat(){
        return pwdRepeat;
    }

    public void setPwdRepeat(String pwdRepeat){
        this.pwdRepeat = pwdRepeat;
    }

    public String getRealname(){
        return realname;
    }

    public void setRealname(String realname){
        this.realname = realname;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }
}
