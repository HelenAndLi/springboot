package pers.mrsli.springboot.core.sys.dto;

public class RoleDto {
    private Long id;
    private String name;
    private String menuIds;

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

    public String getMenuIds(){
        return menuIds;
    }

    public void setMenuIds(String menuIds){
        this.menuIds = menuIds;
    }
}
