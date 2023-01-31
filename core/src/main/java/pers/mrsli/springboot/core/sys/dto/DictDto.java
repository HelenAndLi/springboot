package pers.mrsli.springboot.core.sys.dto;

public class DictDto {
    private Long id;

    private String type;

    private String label;

    private String value;

    private Integer sort;

    private String descr;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public String getDescr(){
        return descr;
    }

    public void setDescr(String descr){
        this.descr = descr;
    }
}
