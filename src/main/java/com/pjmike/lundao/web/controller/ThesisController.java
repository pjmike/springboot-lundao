package com.pjmike.lundao.web.controller;

import com.pjmike.lundao.domain.bo.Thesis;
import com.pjmike.lundao.service.ThesisSevice;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import com.pjmike.lundao.utils.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 论点
 *
 * @author pjmike
 * @create 2018-06-06 22:28
 */
@RestController
@Slf4j
public class ThesisController {
    @Autowired
    private ThesisSevice thesisSevice;
    /**
     * 返回论点列表
     *
     * @param page
     * @return
     */
    @GetMapping("/debates/thesis/{debateId}/pages/{page}")
    public Result getAllThesis(@PathVariable("page")Integer page,@PathVariable("debateId")Integer debateId) {
        List<Thesis> thesisList = thesisSevice.findThesisByPageAndSize(page,Constants.SIZE,debateId);
        return ResultUtils.success(thesisList);
    }

    /**
     * 添加论点
     *
     * @param thesis
     * @return
     */
    @PostMapping("/thesis")
    public Result addThesis(@RequestBody Thesis thesis) throws IOException {
        Thesis result = thesisSevice.save(thesis);
        return ResultUtils.success(result);
    }

    /**
     * 修改论点
     *
     * @param thesisId
     * @param thesis
     * @return
     */
    @PutMapping("/thesis/{thesisId}")
    public Result updateThesis(@PathVariable("thesisId") Integer thesisId, @RequestBody Thesis thesis) {
        thesisSevice.update(thesis);
        return ResultUtils.success(thesis);
    }
}
