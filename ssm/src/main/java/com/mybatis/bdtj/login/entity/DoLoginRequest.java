package com.mybatis.bdtj.login.entity;

/**
 * @author @author@ (@author-email@)
 * 
 * @version @version@, $Date: 2012-3-22$
 * 
 */
public class DoLoginRequest {
    // 用户输入密码
    private String password;
    // 验证码
    private String imageCode;
    // 验证码会话id
    private String imageSsid;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getImageSsid() {
        return imageSsid;
    }

    public void setImageSsid(String imageSsid) {
        this.imageSsid = imageSsid;
    }

    @Override
    public String toString() {
        return "DoLoginRequest [imageCode=" + imageCode + ", imageSsid=" + imageSsid + "]";
    }

}
