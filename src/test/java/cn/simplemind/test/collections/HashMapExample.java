package cn.simplemind.test.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @author yingdui_wu
 * @date   2018年8月14日 上午9:31:34
 */
public class HashMapExample {
    
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int TREEIFY_THRESHOLD = 8;

    static final int UNTREEIFY_THRESHOLD = 6;

    static final int MIN_TREEIFY_CAPACITY = 64;
    
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("111", "test");
        System.out.println(map);
        tableSizeFor(1000);
        
        hash("Jerry");
    }
    
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    
    static final int hash(Object key) {
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        
        return hash;
    }
}
