package com.miniProject.subway.member;

public class MemberDTO {

    private String id;
    private String pwd;
    private String name;
    private String email;
    private String phone;
    private int point;

    public MemberDTO (){
        // 기본생성자
    }

    public MemberDTO (String id, String pwd, String name, String email, String phone){
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void information(){
        System.out.println("id : " + this.id + "\n" +
                "pwd : " + this.pwd + "\n" +
                "name : " + this.name + "\n" +
                "email : " + this.email + "\n" +
                "phone : " + this.phone + "\n" +
                "point : " + this.point);
    }
}
