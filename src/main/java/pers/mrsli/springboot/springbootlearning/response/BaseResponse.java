package pers.mrsli.springboot.springbootlearning.response;

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

    /**
     * 若结果为分页，data直接返回Pagination对象即可。
     *
     * @see Pagination
     */
    private T data;

    public BaseResponse(){

    }

    public BaseResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
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
}
