package pers.mrsli.springboot.core.sys.query;

public class DictQuery extends PageQuery {

    private String type;

    private String descr;

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getDescr(){
        return descr;
    }

    public void setDescr(String descr){
        this.descr = descr;
    }
}
