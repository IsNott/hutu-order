package org.nott.security.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nott
 * @date 2024-6-11
 */
public class SecurityConstants {

    public static final String ADMIN_REQUEST_SUFFIX = "/sys/";

    public final static List<String> PERMITTED_URL = Arrays.asList("/sys/admin/login", "/sys/admin/logout", "/error");

    public final static String ERROR_URL = "/error";

    public static final String ADMIN_ROLE_NAME = "admin";
}
