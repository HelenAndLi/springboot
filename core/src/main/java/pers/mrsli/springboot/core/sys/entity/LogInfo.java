package pers.mrsli.springboot.core.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_log")
public class LogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String module;

    /**
     * 操作用户ID
     */
    @TableField(value = "user_id")
    private long userId;

    /**
     * 操作时间
     */
    @TableField(value = "oper_time")
    private Date operTime;

    private String uri;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求方式
     */
    @TableField(value = "http_method")
    private String httpMethod;

    /**
     * 请求参数
     */
    @TableField(value = "req_param")
    private String reqParam;

    private String ip;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getModule(){
        return module;
    }

    public void setModule(String module){
        this.module = module;
    }

    public long getUserId(){
        return userId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public Date getOperTime(){
        return operTime;
    }

    public void setOperTime(Date operTime){
        this.operTime = operTime;
    }


    public String getUri(){
        return uri;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public String getMethod(){
        return method;
    }

    public void setMethod(String method){
        this.method = method;
    }

    public String getReqParam(){
        return reqParam;
    }

    public void setReqParam(String reqParam){
        this.reqParam = reqParam;
    }

    public String getHttpMethod(){
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod){
        this.httpMethod = httpMethod;
    }

    public String getIp(){
        return ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
}
