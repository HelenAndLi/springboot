package pers.mrsli.springboot.core.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户entity
 */
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * uuid
     */
    private String uuid;
    /**
     * 姓名
     */
    private String realname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    private String pwd;

    /**
     * 账号状态
     * @see cn.omtech.countyservice.core.modules.sys.enums.UserStatusEnum
     */
    private int status;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_by")
    private Long createBy;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "update_by")
    private Long updateBy;
    @TableField(value = "pwd_expiration")
    private Date pwdExpiration;

    private boolean delFlag;

    @TableField(value = "login_ip")
    private String loginIp;

    @TableField(value = "latest_login_time")
    private Date latestLoginTime;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Long getCreateBy(){
        return createBy;
    }

    public void setCreateBy(Long createBy){
        this.createBy = createBy;
    }

    public Long getUpdateBy(){
        return updateBy;
    }

    public void setUpdateBy(Long updateBy){
        this.updateBy = updateBy;
    }

    public boolean isDelFlag(){
        return delFlag;
    }

    public void setDelFlag(boolean delFlag){
        this.delFlag = delFlag;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public String getUuid(){
        return uuid;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
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

    public Date getPwdExpiration(){
        return pwdExpiration;
    }

    public void setPwdExpiration(Date pwdExpiration){
        this.pwdExpiration = pwdExpiration;
    }

    public String getLoginIp(){
        return loginIp;
    }

    public void setLoginIp(String loginIp){
        this.loginIp = loginIp;
    }

    public Date getLatestLoginTime(){
        return latestLoginTime;
    }

    public void setLatestLoginTime(Date latestLoginTime){
        this.latestLoginTime = latestLoginTime;
    }
}
