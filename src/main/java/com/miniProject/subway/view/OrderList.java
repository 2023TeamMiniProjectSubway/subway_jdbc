package com.miniProject.subway.view;

import com.miniProject.subway.member.MemberDTO;
import com.miniProject.subway.menu.MenuDTO;
import com.miniProject.subway.order.OrderController;
import com.miniProject.subway.order.OrderSandwichService;
import com.miniProject.subway.order.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.ArrayList;
import java.util.Iterator;
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
    public void orderComplete(ArrayList<MemberDTO> memberDTO) {


        int ordernum = getOrderNum();

        OrderMain.orderMenuNum = 0;


        //=================주문 정보 (subway_order) 넣기 ==================================================
        OrderService orderService = new OrderService();
//        System.out.println("현재 로그인 중인 아이디 : " + loginMember.getid());


        orderService.insertMenu(loginMember.getid());          //회원아이디 넣는 방법을 new말고 모르겟음

        //=================현재 주문번호 받아오기==========================================================
        String orderCode = orderService.getOrderCode();
//        System.out.println("현재 주문 번호 : " + orderCode);          //0001
        System.out.println("                            ▷ 고객님의 주문 번호는 [" + orderCode + "] 입니다!");
        System.out.println("=================================================================================");
        System.out.println();
        System.out.println();

        //================주문 샌드위치 정보 (order_sandwich) 넣기==========================================
        OrderController oc = new OrderController();


        String OrderSandwichCode = "";
        String sandname = "";
        String sandcode = "";
        String breadname = "";
        int price = 0;

        int count = 0;      // 같은 샌드위치의 갯수를 셀 변수
        String countstr = "";

        for (int a = 0; a < ordermenu.size(); a++) {          //주문한 샌드위치를 하나씩 비교

            sandname = ordermenu.get(a).toString();
            sandcode = menuhash.get(sandname);
            breadname = choosebread.get(a).toString();
            price = menupriceHash.get(sandname);
            countstr = String.format("%03d", count);        // 001 등으로 포맷


            // 주문 샌드위치 코드 : 주문번호 + 샌드위치코드 + 같은샌드위치의 순번 (ex: 001S1004 : 001주문번호 S1샌드위치 4번째)
            OrderSandwichCode = "" + orderCode + sandcode + countstr;
            for (int b = a + 1; b < ordermenu.size(); b++) {
                if (ordermenu.get(a).equals(ordermenu.get(b))) {
                    count++;            //같은 샌드위치가 있으면 샌드위치코드 숫자 1 늘려주기
                }
            }
//            System.out.println("샌드위치 코드 : " + OrderSandwichCode + ", 샌드위치 이름 : " + sandname + ", 빵 : " + breadname + ", 가격 : " + price);
            orderSandwichService.insertSandwichOrder(OrderSandwichCode, orderCode, sandname, breadname, price);     // order_sandwich DB에 값 넣어주기


            //==================샌드위치 옵션 (sandwich_option) 넣기===========================================================================
            // 야채 (V)
            String veg = "";                        //뺄 야채 이름
            String vegCode = "";                    //뺄 야채 코드
            int vegcount = 1;                       //한 샌드위치에서 빼는 야채의 갯수
            String strVeg = "";

            String topping = "";
            String toppingCode = "";
            int toppingCount = 1;
            String strTopping = "";

            //0번째 샌드위치~v번째 샌드위치

            Iterator iter = minusVegetable.get(a).iterator();   //0번째 샌드위치의 야채셋 // Iterator -- > 세트에 있는 것을 꺼내주
            while (iter.hasNext()) {
                veg = iter.next().toString();
                strVeg = String.format("%03d", vegcount);
                vegCode = "" + OrderSandwichCode + "V" + strVeg;
                vegcount++;

            }

            Iterator iter2 = addTopping.get(a).iterator();
            while (iter2.hasNext()) {
                topping = iter2.next().toString();
                strTopping = String.format("%03d", toppingCount);
                toppingCode = "" + OrderSandwichCode + "-T" + strTopping;
                toppingCount++;
            }


            OrderSandwichService.insertSandwichOption(OrderSandwichCode, vegCode, veg);
            OrderSandwichService.insertSandwichOption(OrderSandwichCode, toppingCode, topping);

        }

        oc.clearMenu();
        oc.orderMenuNum = 0;
        Main.login = false;
        Main main = new Main();
        main.MainMenu();
    }

}
