package com.wismap.springsecuritydemo.controller;

import com.wismap.springsecuritydemo.model.Page;
import com.wismap.springsecuritydemo.service.IPageService;
import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/page",produces = "application/json;charset=utf-8")
public class PageController {

    private IPageService pageService;

    public PageController(IPageService pageService)
    {
        this.pageService=pageService;
    }

    @GetMapping("/selectAll")
    public String selectAll() {
        try {
            List<Page> allPages = pageService.SelectAll();
            return HttpResult.success(allPages).toString();

        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(e.getMessage()).toString();
        }
    }

    @PostMapping("/insert")
    public String Insert(@RequestBody Page page)
    {
        try {
            Page page1=pageService.selectByUrl(page.getUrl());
            if (page1!=null)
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("页面已经存在，新增页面失败").toString();
            Long newid = pageService.Insert(page);
            if (newid>0)
                return HttpResult.success().setMessage("新增页面成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("新增页面失败").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(e.getMessage()).toString();
        }
    }

    @PostMapping("/delete")
    public String Delete(@RequestParam("id")Long id) {
        try {
            Page page = pageService.selectById(id);
            if (page==null)
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("该页面不存在！").toString();
            Long deletecount = pageService.delete(id);
            if (deletecount>0)
                return HttpResult.success().setMessage("删除页面成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("删除页面失败").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(e.getMessage()).toString();
        }
    }

    @PostMapping("/update")
    public String Update(@RequestBody Page page) {
        try {
            Page page1 = pageService.selectById(page.getId());
            if (page1==null){
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("该页面不存在！").toString();
            }
            Page page2 = pageService.selectByUrl(page.getUrl());
            if (page2 != null && !page2.getId().equals(page.getId())) {
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("该url已存在！").toString();
            }
            Long updatetecount = pageService.update(page);
            if (updatetecount>0)
                return HttpResult.success().setMessage("修改页面成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("修改页面失败").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(e.getMessage()).toString();
        }
    }

    @PostMapping("/UpdateSeqByID")
    public String UpdateSeqByID(@RequestParam(value = "id")Long id,
                                @RequestParam(value = "sort")Long sort,
                                @RequestParam(value="pid")Long pid){

        try {
            if ( pageService.SetSeq(id,sort,pid)){
                return HttpResult.success().setMessage("修改排序成功").toString();
            }else {
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("修改排序失败").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(e.getMessage()).toString();
        }
    }
}
