package com.jack006.controller;

import com.jack006.common.ApplicationContextHelper;
import com.jack006.common.JsonData;
import com.jack006.dao.SysAclModuleMapper;
import com.jack006.exception.ParamException;
import com.jack006.model.SysAclModule;
import com.jack006.param.TestVo;
import com.jack006.util.BeanValidator;
import com.jack006.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/20 21:07
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        throw new RuntimeException("test exception");
        //return JsonData.success("hello permission");
    }

    @RequestMapping("/validator.json")
    @ResponseBody
    public JsonData validator(TestVo vo) throws ParamException {
        log.info("validator");
        SysAclModuleMapper moduleMapper =  ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        BeanValidator.check(vo);
        return JsonData.success("test validator");
    }
}
