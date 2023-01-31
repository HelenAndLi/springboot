package pers.mrsli.springboot.core.sys.query;

public class PageQuery {
    private int pageNo;
    private int pageSize;
    private Integer limit;

    public int getPageNo(){
        return pageNo;
    }

    public void setPageNo(int pageNo){
        this.pageNo = pageNo;
    }

    public int getPageSize(){
        return pageSize;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    public Integer getLimit(){
        return limit;
    }

    public void setLimit(Integer limit){
        this.limit = limit;
    }
}
