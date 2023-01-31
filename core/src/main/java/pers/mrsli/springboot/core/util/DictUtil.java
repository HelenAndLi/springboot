package pers.mrsli.springboot.core.util;

import org.apache.commons.lang3.StringUtils;
import pers.mrsli.springboot.core.sys.entity.Dict;
import pers.mrsli.springboot.core.sys.mapper.DictMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 */
public class DictUtil {

    private static DictMapper dictMapper = SpringContextUtil.getBean(DictMapper.class);

    private static RedisUtil redisUtil = SpringContextUtil.getBean(RedisUtil.class);


    private static final String DICT_PREFIX = RedisKey.BASE_PREFIX + RedisKey.DICT_PREFIX;

    public static void setDictValue(String type, String label, String value){
        redisUtil.hSet(DICT_PREFIX + type, label, value);
    }

    public static void deleteDictVal(String type, String label){
        redisUtil.hdel(DICT_PREFIX + type, label);
    }


    public static String getDictValue(String type, String label, String defaultVal){
        if(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
            if(!redisUtil.hExistField(DICT_PREFIX + type, label)){
                Dict dict = dictMapper.getValue(type, label);
                if(dict == null){
                    return defaultVal;
                }
                return dict.getValue();
            }
            return redisUtil.hGetVal(DICT_PREFIX + type, label);
        }
        return defaultVal;
    }

    public static List<String> getLables(String type){
        //        Set<Object> objects = RedisUtil.hGetFields(type);
        List<String> lables = new ArrayList<>();
        //        for(Object object : objects){
        //            lables.add(String.valueOf(object));
        //        }
        return lables;
    }

    /**
     * key为Label
     * value为value
     *
     * @param type
     *
     * @return
     */
    public static Map<String, String> getDicts(String type){
        //        Map<Object, Object> maps = RedisUtil.hGet(type);
        Map<String, String> result = new HashMap<>();
        //        for(Map.Entry<Object, Object> map : maps.entrySet()){
        //            result.put(String.valueOf(map.getKey()), String.valueOf(map.getValue()));
        //        }
        return result;
    }

}

