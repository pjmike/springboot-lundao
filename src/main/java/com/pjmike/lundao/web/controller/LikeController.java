package com.pjmike.lundao.web.controller;

import com.pjmike.lundao.domain.Favour;
import com.pjmike.lundao.service.LikeService;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞操作
 *
 * @author pjmike
 * @create 2018-05-26 14:48
 */
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     * <p>
     * 需要type,typeId,status点赞状态,uid
     * </p>
     *
     * @param favour
     * @return
     */
    @PostMapping("/favour")
    public Result like(@RequestBody Favour favour) {
        Favour favour1 =  likeService.save(favour);
        return ResultUtils.success(favour1);
    }
}
