package com.hzitoa.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hzitoa.entity.TbAuthority;
import com.hzitoa.mapper.TbAuthorityMapper;
import com.hzitoa.mapper.TbRoleMapper;
import com.hzitoa.service.ITbAuthorityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hzitoa.vo.AceTreeVo;
import com.hzitoa.vo.AdditionalParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Service
public class TbAuthorityServiceImpl extends ServiceImpl<TbAuthorityMapper, TbAuthority> implements ITbAuthorityService {

    @Autowired
    private TbAuthorityMapper tbAuthorityMapper;

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Override
    public List<AceTreeVo> getAuthTree(Integer roleId) {
        List<TbAuthority> authList = tbAuthorityMapper.selectList(new EntityWrapper<TbAuthority>().where("pid is null"));
        List<AceTreeVo> treeList = new ArrayList<>();
        for(TbAuthority auth : authList){
            AceTreeVo treeVo = this.packAuth(auth,tbRoleMapper.selectById(roleId).getResourceIds());
            treeList.add(treeVo);
        }
        return treeList;
    }

    private AceTreeVo packAuth(TbAuthority auth,String resourceIds){
        AceTreeVo treeVo = new AceTreeVo();
        treeVo.setName(auth.getAuthName());
        AdditionalParameters parameters = new AdditionalParameters();
        parameters.setId(auth.getAuthId());
        if(auth.getPid() == null){
            List<TbAuthority> subAuthList = tbAuthorityMapper.selectList(new EntityWrapper<TbAuthority>().where("pid = " + auth.getAuthId()));
            Map<String,AceTreeVo> subMap = new HashMap<>();
            if(subAuthList != null && subAuthList.size() != 0){
                treeVo.setType("folder");
                for(TbAuthority thAuth : subAuthList){
                    AceTreeVo subTreeVo = packAuth(thAuth,resourceIds);
                    subMap.put(subTreeVo.getName(),subTreeVo);
                }
                parameters.setChildren(subMap);
            }else{
                treeVo.setType("item");
            }
        }else{
            treeVo.setType("item");
        }
        if(resourceIds != null && !"".equals(resourceIds)) {
            if (resourceIds.contains(auth.getAuthId().toString()) && "item".equals(treeVo.getType())) {
                parameters.setItem_selected(true);
            }
        }
        treeVo.setAdditionalParameters(parameters);
        return treeVo;
    }
}
