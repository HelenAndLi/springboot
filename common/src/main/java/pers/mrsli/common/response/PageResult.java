package pers.mrsli.common.response;

public class PageResult<T> {

    private int page;

    private int pageSize;

    private long totals;

    private T data;

    public int getPage(){
        return page;
    }

    public void setPage(int page){
        this.page = page;
    }

    public int getPageSize(){
        return pageSize;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    public long getTotals(){
        return totals;
    }

    public void setTotals(long totals){
        this.totals = totals;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }
}
