package com.pjmike.lundao.web.controller;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.pjmike.lundao.domain.bo.Thesis;
import com.pjmike.lundao.domain.bo.User;
import com.pjmike.lundao.qiniu.IQiNIuService;
import com.pjmike.lundao.service.ThesisSevice;
import com.pjmike.lundao.service.UserService;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

/**
 * 个人中心相关操作
 *
 * @author pjmike
 * @create 2018-06-07 0:37
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IQiNIuService qiNIuService;
    @Autowired
    private ThesisSevice thesisSevice;
    @Value("${QiNiu}")
    private String QiNiu;

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        if (java.util.Objects.isNull(user)) {
            return ResultUtils.error("用户不存在");
        }
        user.setPassword(null);
        user.setSalt(null);
        return ResultUtils.success(user);
    }

    /**
     * 修改头像
     *
     * @param id
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/{id}/avatar")
    public Result changeAvatar(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        User user = userService.findById(id);
        if (java.util.Objects.isNull(user)) {
            return ResultUtils.error("用户不存在");
        }
        String originalFileName = file.getOriginalFilename();
        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        user.setAvatar(QiNiu + originalFileName);
        userService.update(user);
        return ResultUtils.success(QiNiu + originalFileName);
    }

    /**
     * 修改用户信息
     *
     * @param id
     * @return
     */
    @PostMapping("/{id}/information")
    public Result updateInformation(@PathVariable("id") Integer id,@RequestBody User user) {
        userService.update(user);
        return ResultUtils.success();
    }

    /**
     * 查看用户的所有辩题
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/thesis")
    public Result getThesis(@PathVariable("id") Integer id) {
        Condition condition = new Condition(Thesis.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("uid", id);
        List<Thesis> thesisList = thesisSevice.findByCondition(condition);
        return ResultUtils.success(thesisList);
    }
}
