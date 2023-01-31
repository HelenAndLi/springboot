package pers.mrsli.common.response;

/**
 * 通用响应格式
 *
 * @param <T>
 */
public class BaseResponse<T> {

    /**
     * 0表示成功，非0表示异常情况，一般构成“10+三位码”，如 10500
     */
    private int code;

    private String msg;

    private boolean state;

    /**
     * 若结果为分页，data直接返回Pagination对象即可。
     *
     * @see Pagination
     */
    private T data;

    private Pagination pagination;

    public BaseResponse(){

    }

    public BaseResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
        if(code != 0){
            this.state = false;
        }else{
            this.state = true;
        }
    }

    public BaseResponse(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
        if(code != 0){
            this.state = false;
        }else{
            this.state = true;
        }
    }

    public BaseResponse(PageResult<T> pageResult){
        this.code = 0;
        this.msg = "请求成功！";
        this.data = pageResult.getData();
        Pagination pagination = new Pagination();
        pagination.setPage(pageResult.getPage());
        pagination.setPageSize(pageResult.getPageSize());
        pagination.setTotals(pageResult.getTotals());
        pagination.setTotalPages((int) pageResult.getTotals() / pageResult.getPage());
        pagination.setHasNextPage(pageResult.getPage() < pagination.getTotalPages());
        if(pagination.isHasNextPage()){
            pagination.setNextPage(pagination.getPage() + 1);
        }else{
            pagination.setNextPage(pageResult.getPage());
        }
        this.pagination = pagination;
        if(code != 0){
            this.state = false;
        }else{
            this.state = true;
        }
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public boolean isState(){
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public Pagination getPagination(){
        return pagination;
    }

    public void setPagination(Pagination pagination){
        this.pagination = pagination;
    }
}
