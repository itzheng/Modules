package org.itzheng.and.baseutils.common;

import java.util.List;
import java.util.Map;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2017/8/14.
 */
public class ArrayUtils {

    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static boolean isEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static int size(List list) {
        return (list == null) ? 0 : list.size();
    }
}
