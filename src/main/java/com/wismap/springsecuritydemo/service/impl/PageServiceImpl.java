package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.PageMapper;
import com.wismap.springsecuritydemo.mapper.PrivilegeMapper;
import com.wismap.springsecuritydemo.mapper.Ref_Privilege_PageMapper;
import com.wismap.springsecuritydemo.model.Page;
import com.wismap.springsecuritydemo.model.Privilege;
import com.wismap.springsecuritydemo.model.Ref_Privilege_Page;
import com.wismap.springsecuritydemo.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PageServiceImpl implements IPageService {

    @Autowired
    PageMapper pageMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private Ref_Privilege_PageMapper refPrivilegePageMapper;

    @Override
    public List<Page> SelectAll(){
        return pageMapper.SelectAll();
    }

    @Override
    public Page selectByUrl(String url)
    {
        return pageMapper.selectByUrl(url);
    }

    @Override
    public Long Insert(Page page)
    {
        Long maxSort = pageMapper.selectMaxSort(page.getPid());
        if (maxSort==null){
            maxSort=0L;
        }else {
            maxSort+=1;
        }
        page.setSort(maxSort);
        Long insertCount = pageMapper.insert(page);
        if (insertCount>0)
        {
            Privilege privilege= new Privilege();
            privilege.setPritype(2L);//API资源类型
            privilegeMapper.insert(privilege);

            Ref_Privilege_Page ref_privilege_page=new Ref_Privilege_Page(privilege.getId(),page.getId());
            refPrivilegePageMapper.insert(ref_privilege_page);
        }
        return insertCount;
    }

    @Override
    public Long delete(Long id)
    {
        Long deleteCount=0L;
        Ref_Privilege_Page ref_privilege_page=refPrivilegePageMapper.selectByPageid(id);
        deleteCount+=privilegeMapper.delete(ref_privilege_page.getPriId());
        deleteCount+=refPrivilegePageMapper.delete(ref_privilege_page.getPriId(),id);
        deleteCount+=pageMapper.delete(id);
        return deleteCount;
    }

    @Override
    public Long update(Page page)throws Exception
    {
        if (page.getId()==page.getPid())
            throw new Exception("id不可以和pid相同");
        return pageMapper.update(page);
    }

    @Override
    public Boolean SetSeq(Long id, Long sort, Long pid) {

        Page page = pageMapper.selectById(id);
        try {
            if(page.getPid().equals(pid))//同一组之间排序
            {
                if(page.getSort()>sort)
                    pageMapper.set_up(pid,sort,page.getSort());
                else
                    pageMapper.set_down(pid,sort,page.getSort());
                page.setSort(sort);
                update(page);
            }
            else
            {
                pageMapper.clearsequp(pid, sort);
                pageMapper.clearseq(page.getPid(),page.getSort());
                page.setSort(sort);
                page.setPid(pid);
                update(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void updatePrivilegePage(Page page) {
            Privilege privilege = new Privilege();
            privilege.setId(page.getPriid());
            privilege.setPritype(2L);
            privilegeMapper.update(privilege);
    }

    @Override
    public Page selectById(Long id) {
        return pageMapper.selectById(id);
    }

}
