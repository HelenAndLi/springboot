package pers.mrsli.springboot.core.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.mrsli.springboot.core.sys.entity.LogInfo;
import pers.mrsli.springboot.core.sys.mapper.LogMapper;
import pers.mrsli.springboot.core.sys.service.ILogService;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogInfo> implements ILogService {

}
