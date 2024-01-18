package com.miniProject.subway.view;

import com.miniProject.subway.member.MemberDTO;
import com.miniProject.subway.menu.MenuDTO;
import com.miniProject.subway.order.OrderController;
import com.miniProject.subway.order.OrderSandwichService;
import com.miniProject.subway.order.OrderService;

import java.util.ArrayList;
import java.util.List;

import static com.miniProject.subway.member.MemberController.loginMember;
import static com.miniProject.subway.order.OrderController.*;


public class OrderList {    // TODO :: 메뉴 어레이리스트

    OrderSandwichService orderSandwichService = new OrderSandwichService();
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
    public void orderComplete(ArrayList<MemberDTO> memberDTO){

        int i = 0;

            double dnum = Math.random();
            i = (int)(dnum * 10);


        System.out.println("                            ▷ 고객님의 주문 번호는 [" + i + "] 입니다!");
        System.out.println("=================================================================================");
        System.out.println();
        System.out.println();

        int ordernum = getOrderNum();

        OrderMain.orderMenuNum = 0;
        OrderService orderService = new OrderService();
        System.out.println("현재 로그인 중인 아이디 : " + loginMember.getid());

        orderService.insertMenu(loginMember.getid());          //회원아이디 넣는 방법을 new말고 모르겟음
        OrderController oc = new OrderController();

        String sandname = "";
        String sandcode = "";
        String breadname = "";
        int price = 0;

        for(int a = 0; a < ordermenu.size(); a++){          //주문한 샌드위치를 하나씩 비교
            if(menuhash.containsKey(ordermenu.get(a))){

                sandname = ordermenu.get(a).toString();
                sandcode = menuhash.get(sandname);
                breadname = choosebread.get(a).toString();
                price = menuDTO.get(a).getPrice();
                System.out.println("샌드위치 이름 : " + sandname + ", 샌드위치 코드 : " + sandcode + ", 빵 : " + breadname + ", 가격 : " + price);
            }

        }

        orderSandwichService.insertSandwichOrder(sandcode, sandname, breadname, price);


        oc.clearMenu();
        oc.orderMenuNum = 0;
        Main.login = false;
        Main main = new Main();
        main.MainMenu();
    }


}
