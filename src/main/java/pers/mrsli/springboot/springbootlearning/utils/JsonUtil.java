package pers.mrsli.springboot.springbootlearning.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Json工具类（Jackson）
 */
public class JsonUtil {

    private static final ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
        // 忽略json中在对象不存在对应属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略空bean转json错误
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private JsonUtil(){
    }

    /**
     * 对象转json
     */
    public static String objToStr(Object object){
        try{
            return mapper.writeValueAsString(object);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * map转对象
     */
    public static <T> T mapToObj(Map map, Class<T> valueType){
        return mapper.convertValue(map, valueType);
    }

    /**
     * json转对象
     */
    public static <T> T toBean(String content, Class<T> valueType){
        if(StringUtils.isBlank(content)){
            return null;
        }
        try{
            return mapper.readValue(content, valueType);
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> toList(String content, Class<T> valueType){
        if(StringUtils.isBlank(content)){
            return null;
        }
        try{
            return mapper.readValue(content, new TypeReference<List<T>>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
