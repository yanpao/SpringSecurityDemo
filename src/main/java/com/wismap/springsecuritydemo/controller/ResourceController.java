package com.wismap.springsecuritydemo.controller;

import com.wismap.springsecuritydemo.model.Resource;
import com.wismap.springsecuritydemo.service.IResourceService;
import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/resource",produces = "application/json;charset=utf-8")
public class ResourceController{

    private IResourceService resourceService;

    public ResourceController(IResourceService resourceService)
    {
        this.resourceService=resourceService;
    }

    @PostMapping("/insert")
    public String Insert(@RequestBody Resource resource)
    {
        resourceService.insert(resource);
        return HttpResult.success().setMessage("添加资源成功").toString();
    }

    @GetMapping("/selectAll")
    public String SelectAll()
    {
        List<Resource> AllResources = resourceService.selectAll();
        return HttpResult.success(AllResources).toString();
    }

    @PostMapping("/delete")
    public String Delete(Long resourceid)
    {
        if (resourceService.delete(resourceid))
            return HttpResult.success().setMessage("删除资源成功").toString();
        else
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("删除资源失败").toString();
    }

    @PostMapping("/update")
    public String Update(@RequestBody  Resource resource)
    {
        try
        {
            return HttpResult.success(resourceService.update(resource)).toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }


}

