package per.xiaozhi.pojo;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/23-9:16
 *注册页面表单
 *
 */

public class RegisterForm {
    private String name;
    private String password;
    private Integer userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
