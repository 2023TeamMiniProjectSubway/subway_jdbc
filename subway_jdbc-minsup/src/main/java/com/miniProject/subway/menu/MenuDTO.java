package com.miniProject.subway.menu;

public class MenuDTO {

    private String menuname;
    private int price;
    private String description;

    public MenuDTO(){

    }

    public MenuDTO(String menuname, int price, String description){
        this.menuname = menuname;
        this.price = price;
        this.description = description;
    }

    public String getMenuname() {
        return this.menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String menuInformation(){
        return "메뉴 이름 : " + this.menuname + "\n 메뉴 가격 : " + this.price + "\n 메뉴 설명 : " + this.description;
    }
}
