package pers.mrsli.springboot.core.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 机构entity
 */
@TableName("sys_office")
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    private String name;

    private Long pid;

    @TableField(value = "p_uuid")
    private String puuid;

    private String pids;

    @TableField(value = "zone_level")
    private int zoneLevel;

    private int sort;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUuid(){
        return uuid;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getPid(){
        return pid;
    }

    public void setPid(Long pid){
        this.pid = pid;
    }

    public int getZoneLevel(){
        return zoneLevel;
    }

    public void setZoneLevel(int zoneLevel){
        this.zoneLevel = zoneLevel;
    }

    public String getPuuid(){
        return puuid;
    }

    public void setPuuid(String puuid){
        this.puuid = puuid;
    }

    public int getSort(){
        return sort;
    }

    public void setSort(int sort){
        this.sort = sort;
    }

    public String getPids(){
        return pids;
    }

    public void setPids(String pids){
        this.pids = pids;
    }
}
