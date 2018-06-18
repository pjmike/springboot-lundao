package com.pjmike.lundao.web.controller;

import com.google.gson.Gson;
import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.qiniu.IQiNIuService;
import com.pjmike.lundao.service.DebateService;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import com.pjmike.lundao.utils.constants.Constants;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 辩题
 *
 * @author pjmike
 * @create 2018-05-22 12:07
 */
@RestController
@RequestMapping("/debates")
@Slf4j
public class DebateController {
    @Autowired
    private DebateService debateService;
    @Value("${QiNiu}")
    private String qiNiu;
    @Autowired
    private IQiNIuService qiNIuService;

    /**
     * 分页查询辩题
     *
     * @param page
     * @return
     */
    @GetMapping("/page/{page}")
    public Result getAllDebates(@PathVariable("page")Integer page) {
        if (page == null) {
            page = 1;
        }
        List<Debate> debates = debateService.findAllByPageAndSize(page, Constants.SIZE);
        return ResultUtils.success(debates);
    }

    /**
     * 辩题详情
     *
     * @param debateId
     * @return
     */
    @GetMapping("/{debateId}")
    public Result getDebateWithThesis(@PathVariable("debateId") Integer debateId,@RequestParam(value = "userId",defaultValue = "0")Integer userId) {
        System.out.println(userId);
        if (userId == 0) {
            return ResultUtils.success(debateService.findWithThesisIdAndWithoutUser(debateId));
        } else {
            return ResultUtils.success(debateService.findWithThesisId(userId,debateId));
        }
    }

    /**
     * 添加辩题
     *
     * @param file
     * @param debate
     * @return
     * @throws IOException
     */
    @PostMapping
    public Result addDebate(@RequestParam("file") MultipartFile file, Debate debate) throws IOException {
        if (file == null) {
            return ResultUtils.error("图片为空");
        }
        String originalFileName = file.getOriginalFilename();
        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        debate.setImage(qiNiu + originalFileName);
        debate = debateService.save(debate);
        return ResultUtils.success(debate);
    }

}
