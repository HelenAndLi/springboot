package pers.mrsli.springboot.core.sys.dto;

public class MenuSimpleDto {

    private long id;

    private String name;

    private Long pId;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getpId(){
        return pId;
    }

    public void setpId(Long pId){
        this.pId = pId;
    }
}
