package com.example.corelib.util;

/**
 * 检查状态工具类
 *
 * @author 彭翔宇
 */
public class CheckStatus {
    /**
     * 检查登录用户名、密码是否为空
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkUserAndPass(String username, String password) {
        if (!username.equals("") && !username.isEmpty()) {
            if (!password.isEmpty() && !password.equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    public static boolean checkUserAndPass(String username, String password, String repassword) {
        if (!username.equals("") && !username.isEmpty()) {
            if (!password.isEmpty() && !password.equals("")) {
                if (!repassword.isEmpty() && !repassword.equals("")) {
                    return true;
                }
            }
        }
        return false;
    }
}
