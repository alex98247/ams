package com.ams.security;

import org.apache.commons.lang3.StringUtils;

public class SecurityUtils {

    public static boolean validateTokenHeader(String requestHeader) {
        return StringUtils.isNoneEmpty(requestHeader) && requestHeader.startsWith("Bearer ");
    }

    public static String parseToken(String requestHeader) {
        return requestHeader.substring(7);
    }
}
