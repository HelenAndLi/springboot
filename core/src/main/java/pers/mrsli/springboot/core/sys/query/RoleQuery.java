package pers.mrsli.springboot.core.sys.query;

import cn.omtech.countyservice.common.query.BaseQuery;

public class RoleQuery extends BaseQuery {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
