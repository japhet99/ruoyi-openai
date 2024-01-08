package com.ruoyi.common.utils;

import java.util.*;

public class PayUtils {

    public static String generateSign(Map<String, Object> params, String key) {
        // Step 1: 过滤不参与签名的参数（sign、sign_type、空值）
        Map<String, Object> filteredParams = filterParams(params);

        // Step 2: 参数按照参数名ASCII码从小到大排序
        List<String> sortedKeys = new ArrayList<>(filteredParams.keySet());
        Collections.sort(sortedKeys);

        // Step 3: 拼接参数为URL键值对的格式
        StringBuilder sb = new StringBuilder();
        for (String paramName : sortedKeys) {
            String paramValue = (String)filteredParams.get(paramName);
            sb.append(paramName).append("=").append(paramValue).append("&");
        }
        sb.deleteCharAt(sb.length() - 1); // 移除末尾的 "&"

        // Step 4: 加密拼接好的字符串得到sign签名参数
        String signStr = sb.toString() + key;
        String sign = md5(signStr).toLowerCase();

        return sign;
    }

    private static Map<String, Object> filterParams(Map<String, Object> params) {
        Map<String, Object> filteredParams = new HashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String paramName = entry.getKey();
            if(entry.getValue()!=null){
                String paramValue = entry.getValue().toString();
                if (paramName.equalsIgnoreCase("sign") ||
                        paramName.equalsIgnoreCase("sign_type") ||
                        paramValue.isEmpty()) {
                    continue;
                }
                filteredParams.put(paramName, paramValue);
            }
        }
        return filteredParams;
    }

    // 这里使用了第三方库或API来计算MD5，例如 Apache Commons Codec
    private static String md5(String input) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(input);
    }
}
