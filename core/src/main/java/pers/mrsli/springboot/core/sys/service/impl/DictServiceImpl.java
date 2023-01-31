package pers.mrsli.springboot.core.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mrsli.common.response.PageResult;
import pers.mrsli.springboot.core.sys.dto.DictDto;
import pers.mrsli.springboot.core.sys.entity.Dict;
import pers.mrsli.springboot.core.sys.mapper.DictMapper;
import pers.mrsli.springboot.core.sys.query.DictQuery;
import pers.mrsli.springboot.core.sys.service.IDictService;
import pers.mrsli.springboot.core.util.DictUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public boolean exist(Long id, String type, String label){
        Dict dict = dictMapper.getByTypeAndLabel(type, label);
        if(dict == null || id == null){
            return false;
        }
        if(dict.getId().equals(id)){
            return true;
        }
        return false;
    }

    @Override
    public List<String> allTypes(){
        return dictMapper.allTypes();
    }

    @Override
    public void save(DictDto vo){
        DictUtil.setDictValue(vo.getType(), vo.getLabel(), vo.getValue());
        Dict dict;
        if(vo.getId() != null){
            dict = dictMapper.selectById(vo.getId());
            BeanUtils.copyProperties(vo, dict);
            dict.setUpdateTime(new Date());
            dictMapper.updateById(dict);
        }else{
            dict = new Dict();
            BeanUtils.copyProperties(vo, dict);
            dict.setCreateTime(new Date());
            dictMapper.insert(dict);
        }
    }

    @Override
    public PageResult<DictDto> pagelist(DictQuery query){

        PageResult pageResult = new PageResult();
        pageResult.setPage(query.getPageNo());
        pageResult.setPageSize(query.getPageSize());

        query.setLimit((query.getPageNo() - 1) * query.getPageSize());
        int count = dictMapper.count(query);
        pageResult.setTotals(count);
        if(count < 1){
            return pageResult;
        }
        List<Dict> dicts = dictMapper.pagelist(query);
        List<DictDto> vos = new ArrayList<>();
        for(Dict dict : dicts){
            DictDto vo = new DictDto();
            BeanUtils.copyProperties(dict, vo);
            vos.add(vo);
        }
        pageResult.setData(vos);
        return pageResult;
    }

    @Override
    public void delete(Long id){
        Dict dict = dictMapper.selectById(id);
        DictUtil.deleteDictVal(dict.getType(), dict.getLabel());
        dictMapper.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids){
        ids.forEach(id->delete(id));
    }
}
