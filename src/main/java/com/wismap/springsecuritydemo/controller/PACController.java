package com.wismap.springsecuritydemo.controller;

import com.wismap.springsecuritydemo.model.PAC;
import com.wismap.springsecuritydemo.service.IPACService;
import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pac",produces = "application/json")
public class PACController {

    private IPACService pacService;

    public PACController(IPACService pacService)
    {
        this.pacService =pacService;
    }

    @GetMapping("/select")
    public String SelectByPAC(String paccode)
    {
        PAC pac=pacService.selectByPAC(paccode);
        return HttpResult.success(pac).toString();
    }

    @GetMapping("/selectbyname")
    public String SelectByName(String name)
    {
        List<PAC> pac=pacService.selectByName(name);
        return HttpResult.success(pac).toString();
    }

    @PostMapping("/insert")
    public String Insert(@RequestBody PAC pac)
    {
        try {
            PAC result = pacService.insert(pac);
            if (result != null) {
                return HttpResult.success(result).toString();
            } else {
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("新增失败").toString();
            }
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }

    @PostMapping("/delete")
    public String Delete(String paccode)
    {
        try {
            Integer result = pacService.delete(paccode);
            if (result>0)
                return HttpResult.success("删除区域成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("删除区域失败").toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }

    @PostMapping("/update")
    public String Update(@RequestBody PAC pac)
    {
        PAC result = pacService.update(pac);
        if (result!=null) {
            return HttpResult.success(result).toString();
        }
        else
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("修改失败").toString();
        }
    }

}
