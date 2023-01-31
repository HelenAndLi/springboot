package pers.mrsli.springboot.core.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 用户和机构绑定关系entity
 */
@TableName("sys_user_office")
public class UserOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;


    @TableField(value = "user_uuid")
    private String userUuid;

    /**
     * 机构ID
     */
    @TableField(value = "office_id")
    private Long officeId;

    @TableField(value = "office_uuid")
    private String officeUuid;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getUserUuid(){
        return userUuid;
    }

    public void setUserUuid(String userUuid){
        this.userUuid = userUuid;
    }

    public Long getOfficeId(){
        return officeId;
    }

    public void setOfficeId(Long officeId){
        this.officeId = officeId;
    }

    public String getOfficeUuid(){
        return officeUuid;
    }

    public void setOfficeUuid(String officeUuid){
        this.officeUuid = officeUuid;
    }
}
