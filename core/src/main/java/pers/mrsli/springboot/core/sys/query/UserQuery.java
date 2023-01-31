package pers.mrsli.springboot.core.sys.query;

public class UserQuery extends PageQuery {
    private String username;
    private String realname;
    private String mobile;

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

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }
}
