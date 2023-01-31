package pers.mrsli.springboot.core.sys.dto;


public class MenuDto {
    private Long id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 链接
     */
    private String uri;

    /**
     * 图标
     */
    private String icon;
    private String permission;
    private boolean display;
    private int level;
    private int sort;
    private Long pid;

    private String appClass;

    private Integer appJump;

    /**
     * app 菜单排序
     */
    private Integer appMenuSort;

    /**
     * 菜单类型
     */
    private int type;

    private String target;

    private String appUrl;

    private String appBeforeIcon;

    private String appAfterIcon;

    private int openType;



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

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }

    public boolean isDisplay(){
        return display;
    }

    public void setDisplay(boolean display){
        this.display = display;
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

    public Long getPid(){
        return pid;
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

    public String getAppClass(){
        return appClass;
    }

    public void setAppClass(String appClass){
        this.appClass = appClass;
    }

    public Integer getAppJump(){
        return appJump;
    }

    public void setAppJump(Integer appJump){
        this.appJump = appJump;
    }

    public Integer getAppMenuSort(){
        return appMenuSort;
    }

    public void setAppMenuSort(Integer appMenuSort){
        this.appMenuSort = appMenuSort;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public String getTarget(){
        return target;
    }

    public void setTarget(String target){
        this.target = target;
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

    public int getOpenType(){
        return openType;
    }

    public void setOpenType(int openType){
        this.openType = openType;
    }
}
