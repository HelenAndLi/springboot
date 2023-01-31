package pers.mrsli.springboot.application.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import pers.mrsli.springboot.core.sys.entity.LogInfo;
import pers.mrsli.springboot.core.sys.entity.User;
import pers.mrsli.springboot.core.sys.service.ILogService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class WebLogAspect {

    @Autowired
    private ILogService logService;

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * pers.mrsli.springboot.application.controller..*.*(..)) ")//切入点描述 这个是controller包的切入点
    public void controllerLog(){
    }//签名，可以理解成这个切入点的一个名称

    // 包的切入点
//    @Pointcut("execution(public * pers.mrsli.springboot.application.controller.sys..*(..))")//切入点描述 这个是controller
//    public void removeController(){
//    }

//    @Before("controllerLog()&&!removeController()") //在切入点的方法run之前要干的
    @Before("controllerLog()") //在切入点的方法run之前要干的
    public void logBeforeController(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder
        // 是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 记录下请求内容
        logger.warn("url:{}", request.getRequestURL().toString());
        logger.debug("method: {}", request.getMethod());
        logger.debug("ip: {}", request.getRemoteAddr());
        logger.debug("args: {}", Arrays.toString(joinPoint.getArgs()));

        //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
        logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.debug("TARGET: " + joinPoint.getSignature());//返回的是需要加强的目标类的对象
        logger.debug("THIS: " + joinPoint.getThis());//返回的是经过加强后的代理类的对象
        LogInfo logInfo = new LogInfo();
        logInfo.setUri(request.getRequestURI());
        logInfo.setHttpMethod(request.getMethod());
        logInfo.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for(int i = 0; i < args.length; i++){
            // 不能序列化的要排除
            if(args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile){
                continue;
            }
            arguments[i] = args[i];
        }

        if(arguments != null){
            try{
//                logInfo.setReqParam(JsonUtil.objToStr(joinPoint.getArgs()));
            }catch(Exception e){
                logger.debug("cannot set args:{}", arguments.toString());
            }
        }
        logInfo.setOperTime(new Date());
        User user
                = UserUtil.getUser();
        if(user != null){
            logInfo.setUserId(user.getId());
        }
        logService.save(logInfo);
    }


}