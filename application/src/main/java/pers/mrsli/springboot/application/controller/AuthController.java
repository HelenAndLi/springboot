package pers.mrsli.springboot.application.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pers.mrsli.common.response.BaseResponse;
import pers.mrsli.common.response.ResponseUtil;
import pers.mrsli.springboot.core.sys.dto.UserDto;

// https://www.cnblogs.com/xiaofengxzzf/p/10733955.html
@Validated
@RestController("/auth/")
public class AuthController {

    /**
     * @param userDTO
     *
     * @return
     *
     * @description 添加用户
     */
    @PostMapping("user")
    public BaseResponse add(@RequestBody UserDto userDTO){

        return ResponseUtil.success();
    }

    /**
     * 49      * @description 删除用户
     * 50      * @param id
     * 51      * @return
     * 52
     */
    @DeleteMapping("user/{id}")
    public BaseResponse deleteUser(@PathVariable("id") Integer id){

        return ResponseUtil.success();
    }

    /**
     * 60      * @descripiton 修改用户
     * 61      * @param userDTO
     * 62      * @return
     * 63
     */
    @PutMapping("user")
    public BaseResponse updateUser(@RequestBody UserDto userDTO){
        return ResponseUtil.success();
    }

    /**
     * 71      * @description 获取用户列表
     * 72      * @return
     * 73
     */
    @GetMapping("user")
    public BaseResponse findAllUser(){
        return ResponseUtil.success();
    }

    /**
     * 80      * @description 用户登录
     * 81      * @param loginUserDTO
     * 82      * @return
     * 83
     */
    @PostMapping("user/login")
    public BaseResponse login(UserDto loginUserDTO){
        return ResponseUtil.success();
    }


    /**
     * 91      * @description 用户注销
     * 92      * @param authorization
     * 93      * @return
     * 94
     */
    @GetMapping("user/logout")
    public BaseResponse logout(@RequestHeader("Authorization") String authorization){
        return ResponseUtil.success();
    }

    /**
     * 102      * @description 用户刷新Token
     * 103      * @param refreshToken
     * 104      * @return
     * 105
     */
    @GetMapping("user/refresh/{refreshToken}")
    public BaseResponse refresh(@PathVariable(value = "refreshToken") String refreshToken){
        return ResponseUtil.success();
    }


    /**
     * @return
     *
     * @description 获取所有角色列表
     */
    @GetMapping("role")
    public BaseResponse findAllRole(){
        return ResponseUtil.success();
    }
}
