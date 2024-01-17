package com.miniProject.subway.view;

import com.miniProject.subway.menu.MenuDTO;
import com.miniProject.subway.order.OrderController;
import com.miniProject.subway.view.Main;
import com.miniProject.subway.view.OrderMain;

import java.util.ArrayList;
import java.util.List;

public class OrderList {    // TODO :: 메뉴 어레이리스트

    private List<MenuDTO> orderList;
    private int orderNum;

    public OrderList() {
        orderList = new ArrayList<>();
        orderNum = 1;
    }

    public List<MenuDTO> getOrderList(){
        return orderList;
    }

    public void addOrderList(MenuDTO menuDTO) {
        this.orderList = orderList;
    }

    public void clearOrderList() {
        orderList.clear();
    }

    public int totalPrice() {
        int total = 0;
        for (MenuDTO order : orderList) {
            total += order.getPrice();
        }
        return total;
    }




    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }


    /** 주문 완료 메소드 */
    public void orderComplete(){

        int i = 0;
        for (int o = 0; o < 150; o++){
            double dnum = Math.random();
            i = (int)(dnum * 10);
        }
        System.out.println("                            ▷ 고객님의 주문 번호는 [" + i + "] 입니다!");
        System.out.println("=================================================================================");
        System.out.println();
        System.out.println();

        int ordernum = getOrderNum();

        OrderMain.orderMenuNum = 0;


        OrderController oc = new OrderController();

        oc.clearMenu();
        oc.orderMenuNum = 0;
        Main.login = false;
        Main main = new Main();
        main.MainMenu();
    }


}
