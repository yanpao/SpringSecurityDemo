package com.wismap.springsecuritydemo.controller;

import com.alibaba.fastjson.JSON;
import com.wismap.springsecuritydemo.model.Position;
import com.wismap.springsecuritydemo.service.IPositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "position",produces = "application/json")
public class PositionController {

    private IPositionService positionService;

    public PositionController(IPositionService positionService){
        this.positionService=positionService;
    }

    @GetMapping("/select")
    public String select(Integer id)
    {
        Position position=positionService.selectById(id);
        return JSON.toJSONString(position);
    }
}
