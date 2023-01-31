package pers.mrsli.springboot.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;
//    private static RedisTemplate redisTemplate = SpringContextUtil.getBean(RedisTemplate.class);


    /*********************** String *************************/
    /**
     * 根据key获取值
     *
     * @param key
     *         键
     *
     * @return 值
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 将值放入缓存
     *
     * @param key
     *         键
     * @param value
     *         值
     *
     * @return true成功 false 失败
     */
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将值放入缓存并设置时间
     *
     * @param key
     *         键
     * @param value
     *         值
     * @param time
     *         时间(秒) -1为无期限
     *
     * @return true成功 false 失败
     */
    public void set(String key, String value, long time){
        if(time > 0){
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        }else{
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /*********************** Hash *************************/

    /**
     * Hash
     * 获取hash中field对应的值
     *
     * @param key
     * @param field
     *
     * @return
     */
    public String hGetVal(String key, String field){
        Object val = redisTemplate.opsForHash().get(key, field);
        return val == null ? null : val.toString();
    }

    /**
     * 获取 key 下的 所有  hashkey 和 value
     *
     * @param key
     *         键
     *
     * @return
     */
    public Map<Object, Object> hGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取 key 下的 所有 hashkey 字段
     *
     * @param key
     * @return
     */
    public Set<Object> hGetFields(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 验证指定 key 下 有没有指定的 hashkey
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hExistField(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 加入缓存
     *
     * @param key
     *         键
     * @param map
     *         键
     *
     * @return
     */
    public void hSet(String key, Map<String, String> map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * Hash
     * 添加or更新hash的值
     *
     * @param key
     * @param field
     * @param value
     */
    public void hSet(String key, String field, String value){
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * Hash
     * 删除hash中field这一对kv
     *
     * @param key
     * @param field
     */
    public void hdel(String key, String field){
        redisTemplate.opsForHash().delete(key, field);
    }

    public void del(String key){
        redisTemplate.delete(key);
    }

    /**
     * Hash自增
     *
     * @param key
     * @param field
     * @param value
     *
     * @return
     */
    public long hIncr(String key, String field, long value){
        return redisTemplate.opsForHash().increment(key, field, value);
    }
}
