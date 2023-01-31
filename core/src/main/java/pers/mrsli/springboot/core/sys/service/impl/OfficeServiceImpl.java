package pers.mrsli.springboot.core.sys.service.impl;

import cn.omtech.countyservice.core.modules.sys.entity.Office;
import cn.omtech.countyservice.core.modules.sys.entity.User;
import cn.omtech.countyservice.core.modules.sys.mapper.OfficeMapper;
import cn.omtech.countyservice.core.modules.sys.mapper.UserMapper;
import cn.omtech.countyservice.core.modules.sys.mapper.UserOfficeMapper;
import cn.omtech.countyservice.core.modules.sys.service.IOfficeService;
import cn.omtech.countyservice.core.modules.thirdparty.dto.OfficeInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeMapper, Office> implements IOfficeService {

    @Autowired
    private OfficeMapper officeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserOfficeMapper userOfficeMapper;

    @Override
    public List<Office> getByUserId(Long usesId){
        User user = userMapper.selectById(usesId);
        if(user != null){
            return userOfficeMapper.getByUserUuid(user.getUuid());
        }
        return new ArrayList<>(1);
    }

    @Override
    public Office getByUuid(String uuid){
        return officeMapper.getByUuid(uuid);
    }

    @Override
    public void syncOffice(OfficeInfo officeInfo){
        Office office = officeMapper.getByUuid(officeInfo.getUuid());
        String zoneLevel = officeInfo.getZoneLevel();
        int zone = 0;
        if(StringUtils.equals(zoneLevel, "city") || StringUtils.equals(zoneLevel, "1")){
            zone = 1;
        }else if(StringUtils.equals(zoneLevel, "county") || StringUtils.equals(zoneLevel, "2")){
            zone = 2;
        }
        Office parent = null;

        if(officeInfo.getParentId() != null){
            parent = officeMapper.selectById(officeInfo.getParentId());
        }else if(StringUtils.isNotBlank(officeInfo.getParentUuid())){
            parent = officeMapper.getByUuid(officeInfo.getParentUuid());
        }

        Long pid = null;
        String pids = "";
        String pUuid = "";
        if(parent != null){
            pid = parent.getId();
            pUuid = parent.getUuid();
            if(StringUtils.isNotBlank(parent.getPids())){
                pids = parent.getPids() + "," + parent.getId();
            }else{
                pids = parent.getId() + "";
            }

        }

        if(office != null){
            office.setName(officeInfo.getName());
            office.setPuuid(pUuid);
            office.setSort(officeInfo.getSort());
            office.setZoneLevel(zone);
            office.setPid(pid);
            office.setPids(pids);
            officeMapper.updateById(office);
        }else{
            office = new Office();
            office.setName(officeInfo.getName());
            office.setUuid(officeInfo.getUuid());
            office.setPuuid(pUuid);
            office.setSort(officeInfo.getSort());
            office.setZoneLevel(zone);
            office.setPid(pid);
            office.setPids(pids);
            officeMapper.insert(office);
        }
    }

    @Override
    public void deleteByUuid(String uuid){
        officeMapper.deleteByUuid(uuid);
    }
}
