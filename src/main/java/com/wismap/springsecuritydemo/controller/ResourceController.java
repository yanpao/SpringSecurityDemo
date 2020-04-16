package com.wismap.springsecuritydemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.wismap.springsecuritydemo.model.Resource;
import com.wismap.springsecuritydemo.service.IResourceService;
import com.wismap.springsecuritydemo.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String Insert(@RequestBody Resource resource)
    {
        resourceService.insert(resource);
        return renderSuccess("添加资源成功");
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String SelectAll()
    {
        List<Resource> AllResources = resourceService.selectAll();
        return JSONArray.toJSONString(AllResources);
    }


}

