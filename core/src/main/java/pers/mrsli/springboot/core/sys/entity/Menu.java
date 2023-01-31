package pers.mrsli.springboot.core.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 菜单entity
 */
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单或权限名
     */
    private String name;

    /**
     * 链接
     */
    private String uri;

    /**
     * 权限值
     */
    private String permission;

    /**
     * 是否展示
     */
    private boolean display;

    /**
     * 层级：最小为1,第i层就是i
     */
    private int level;

    /**
     * 排序，值越小越靠前
     */
    private int sort;

    /**
     * 父级菜单id
     */
    private Long pid;

    /**
     * 父级菜单id列表
     */
    private String pids;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接打开的目标窗口
     */
    private String target;

    /**
     * 菜单类型：0-普通菜单
     * 1-APP菜单
     * 2-首页特殊菜单那
     */
    private int type;

    /**
     * 打开方式：0-iframe
     * 1-blank
     */
    @TableField(value = "open_type")
    private int openType;

    @TableField(value = "app_class")
    private String appClass;

    @TableField(value = "app_sort")
    private Integer appSort;

    @TableField(value = "app_jump")
    private Integer appJump;

    @TableField(value = "app_url")
    private String appUrl;

    @TableField(value = "app_before_icon")
    private String appBeforeIcon;

    @TableField(value = "app_after_icon")
    private String appAfterIcon;

    @TableField(value = "has_child")
    private boolean hasChild;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_by")
    private Long createBy;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "update_by")
    private Long updateBy;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUri(){
        return uri;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public void setDisplay(boolean display){
        this.display = display;
    }

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }

    public boolean isDisplay(){
        return display;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getSort(){
        return sort;
    }

    public void setSort(int sort){
        this.sort = sort;
    }

    public long getPid(){
        return pid;
    }

    public void setPid(long pid){
        this.pid = pid;
    }

    public String getPids(){
        return pids;
    }

    public void setPids(String pids){
        this.pids = pids;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Long getCreateBy(){
        return createBy;
    }

    public void setCreateBy(Long createBy){
        this.createBy = createBy;
    }

    public Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Long getUpdateBy(){
        return updateBy;
    }

    public void setUpdateBy(Long updateBy){
        this.updateBy = updateBy;
    }

    public boolean isHasChild(){
        return hasChild;
    }

    public void setHasChild(boolean hasChild){
        this.hasChild = hasChild;
    }

    public void setPid(Long pid){
        this.pid = pid;
    }

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getTarget(){
        return target;
    }

    public void setTarget(String target){
        this.target = target;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getOpenType(){
        return openType;
    }

    public void setOpenType(int openType){
        this.openType = openType;
    }

    public String getAppClass(){
        return appClass;
    }

    public void setAppClass(String appClass){
        this.appClass = appClass;
    }

    public Integer getAppSort(){
        return appSort;
    }

    public void setAppSort(Integer appSort){
        this.appSort = appSort;
    }

    public Integer getAppJump(){
        return appJump;
    }

    public void setAppJump(Integer appJump){
        this.appJump = appJump;
    }

    public String getAppUrl(){
        return appUrl;
    }

    public void setAppUrl(String appUrl){
        this.appUrl = appUrl;
    }

    public String getAppBeforeIcon(){
        return appBeforeIcon;
    }

    public void setAppBeforeIcon(String appBeforeIcon){
        this.appBeforeIcon = appBeforeIcon;
    }

    public String getAppAfterIcon(){
        return appAfterIcon;
    }

    public void setAppAfterIcon(String appAfterIcon){
        this.appAfterIcon = appAfterIcon;
    }
}
