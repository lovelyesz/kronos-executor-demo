package com.yz.kronos;

import java.util.Map;

/**
 * @author shanchong
 * @date 2020-06-20
 **/
public class Main {

    public static void main(String[] args) {
        String execId = System.getenv(ExecuteConstant.KRONOS_EXECUTE_ID);
        System.out.println(execId);
        Map<String, String> getenv = System.getenv();
        System.out.println(getenv);
    }

}
