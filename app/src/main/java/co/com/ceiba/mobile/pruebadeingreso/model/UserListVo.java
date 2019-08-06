package co.com.ceiba.mobile.pruebadeingreso.model;

public class UserListVo {

    private Integer id;
    private String name;
    private String phone;
    private String email;

    public UserListVo() {

    }

    public UserListVo(String name, String phone, String email, Integer id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
