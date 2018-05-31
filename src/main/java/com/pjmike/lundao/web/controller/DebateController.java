package com.pjmike.lundao.web.controller;

import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.service.DebateService;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-22 12:07
 */
@RestController
@RequestMapping("/debates")
public class DebateController {
    @Autowired
    private DebateService debateService;

    private final Integer size = 10;

    @GetMapping("/page/{page}")
    public Result getAllDebates(@PathVariable("page")Integer page) {
        if (page == null) {
            page = 1;
        }
        List<Debate> debates = debateService.findAllByPageAndSize(page, size);
        return ResultUtils.success(debates);
    }

    @GetMapping("/{debateId}")
    public Result getDebateWithThesis(@PathVariable("debateId") Integer debateId) {
        return ResultUtils.success(debateService.findDebateWithThesisById(debateId));
    }
}
