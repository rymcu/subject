package com.rymcu.subject.service.impl;

import com.rymcu.subject.dto.AddSignErrDTO;
import com.rymcu.subject.mapper.SubjectQuestionMapper;
import com.rymcu.subject.mapper.SubjectSignErrMapper;
import com.rymcu.subject.service.SubjectSignErrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 🐛
 */
@Service
public class SubjectSignErrSrviceImpl implements SubjectSignErrService {

    @Resource
    private SubjectSignErrMapper signErrMapper;
    @Resource
    SubjectQuestionMapper subjectQuestionMapper;

    @Override
    public Map postSubjectSignErr(AddSignErrDTO signErr) {
        Map map = new HashMap(1);
        if (signErr.getSqId() == 0) {
            map.put("message", "题目编号不能为空");
            return map;
        }
        if (signErr.getErrContent().isBlank()) {
            map.put("message", "错误描述不能为空");
            return map;
        }
        if (signErr.getErrDec().isBlank()) {
            map.put("message", "错误摘要不能为空");
            return map;
        }
        if (!subjectQuestionMapper.existsBySqId(signErr.getId())) {
            map.put("message", "题库记录不存在，请核实后发送");
            return map;
        }
        signErr.setCreatedTime(new Date());
        if (signErr.getId() == 0) {
            signErrMapper.insertSignErr(signErr.getSqId(), signErr.getErrDec(), signErr.getErrContent(),
                                        signErr.getCreatedBy(), signErr.getCreatedTime());
        } else {
            signErrMapper.updateSignErr(signErr.getId(), signErr.getErrDec(), signErr.getErrContent(),
                                        signErr.getCreatedBy(), signErr.getCreatedTime());
        }
        return map;
    }

    @Override
    public List<AddSignErrDTO> listSignErr() {
        return signErrMapper.listSignErr();
    }
}
