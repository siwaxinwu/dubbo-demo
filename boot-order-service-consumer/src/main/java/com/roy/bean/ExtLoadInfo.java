package com.roy.bean;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 21:31 2023/1/24
 */
public class ExtLoadInfo {

    private String beanName;

    private String scriptBase64;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getScriptBase64() {
        return scriptBase64;
    }

    public void setScriptBase64(String scriptBase64) {
        this.scriptBase64 = scriptBase64;
    }
}
