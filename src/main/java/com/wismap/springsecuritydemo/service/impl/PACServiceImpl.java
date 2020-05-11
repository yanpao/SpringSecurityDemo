package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.PACMapper;
import com.wismap.springsecuritydemo.mapper.Ref_User_PacMapper;
import com.wismap.springsecuritydemo.model.PAC;
import com.wismap.springsecuritydemo.service.IPACService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PACServiceImpl implements IPACService {

    private PACMapper pacMapper;
    private Ref_User_PacMapper ref_user_pacMapper;

    public PACServiceImpl(PACMapper pacMapper,
                          Ref_User_PacMapper ref_user_pacMapper)
    {
        this.pacMapper=pacMapper;
        this.ref_user_pacMapper=ref_user_pacMapper;
    }

    public PAC selectByPAC(String pac){
        return pacMapper.selectByPAC(pac);
    }

    public List<PAC> selectByName(String name){
        return pacMapper.selectByName(name);
    }

    public int delete(String pac){
        ref_user_pacMapper.deleteByPAC(pac);
        return pacMapper.delete(pac);
    }

    public PAC insert(PAC record)throws Exception{
        if(record.getPac()==record.getParent())
            throw new Exception("行政区父级节点不能与行政区代码相同");

        Integer result = pacMapper.insert(record);
        if (result>0)
            return pacMapper.selectByPAC(record.getPac());
        else
            return null;
    }

    public PAC update(PAC record){
        if (pacMapper.update(record)>0) {
            return pacMapper.selectByPAC(record.getPac());
        }
        else
        {
            return null;
        }
    }
}
