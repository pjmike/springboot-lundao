package com.pjmike.lundao.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 账户
 *
 * @author pjmike
 * @create 2018-05-18 15:06
 */
@Data
public class Account implements Serializable {
    public interface group1{}
    public interface group2{}
    private static final long serialVersionUID = 356191011604414946L;
    @NotBlank(message = "用户名不能为空",groups = {group1.class})
    private String username;
    @NotBlank(message = "手机号不能为空",groups = {group1.class,group2.class})
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误",groups = {group1.class,group2.class})
    private String phone;
    @NotBlank(message = "密码不能为空",groups = {group1.class})
    private String password;
    private String code;
}
