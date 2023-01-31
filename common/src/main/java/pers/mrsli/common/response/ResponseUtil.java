package pers.mrsli.common.response;

public class ResponseUtil {

    public static BaseResponse success(){
        return new BaseResponse(0, "请求成功");
    }

    public static BaseResponse success(Object data){
        return new BaseResponse(0, "请求成功", data);
    }

    public static BaseResponse success(PageResult pageResult){
        return new BaseResponse(pageResult);
    }

    public static BaseResponse success(String msg){
        return new BaseResponse(0, msg);
    }

    public static BaseResponse fail(String msg){
        return new BaseResponse(10500, msg);
    }

    public static BaseResponse fail(int code, String msg){
        return new BaseResponse(code, msg);
    }
}
