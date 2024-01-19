package com.miniProject.subway.view;

import com.miniProject.subway.menu.MenuDTO;
import com.miniProject.subway.order.OrderController;
import com.miniProject.subway.view.Main;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderMain {

    Scanner sc = new Scanner(System.in);
    private OrderController oc = new OrderController();
    private MenuDTO menudto = new MenuDTO();
    int choice;
    boolean orderfinish = false;            //주문끝난지 확인
    public static int orderMenuNum = 0;
    public static boolean paycancel = false;
    public static boolean showMenuAgain = false;


    /** 메뉴 선택창_1 메소드 */
    public void orderMenu(){
    
        while(orderfinish == false){
        while(true) {
            System.out.println("=================================================================================");
            System.out.println("                            ▷ 🥙 주문할 메뉴를 선택해주세요.                            ");
            System.out.println("                            ▷ 1. 클래식                                           ");
            System.out.println("                            ▷ 2. 프레쉬&라이트                                     ");
            System.out.println("                            ▷ 3. 프리미엄                                          ");
            System.out.println("                            ▷ 4. 신제품 (new!)                                    ");
            System.out.println("                                                                                 ");
            System.out.println("                            ▶ 0. 이전 메뉴로                                      ");
            System.out.println("=================================================================================");

            try {
                int ordermenu = sc.nextInt();

                switch (ordermenu) {
                    case 1:
                        classicMenu();
                        if (orderfinish == true) {
                            orderfinish = false;
                            return;
                        }
                        continue;
                    case 2:
                        freshlightMenu();
                        if (orderfinish == true) {
                            orderfinish = false;
                            return;
                        }
                        continue;

                    case 3:
                        premiumMenu();
                        if (orderfinish == true) {
                            orderfinish = false;
                            return;
                        }
                        continue;
                    case 4:
                        newMenu();
                        if (orderfinish == true) {
                            orderfinish = false;
                            return;
                        }
                        continue;
                    case 0:
                        System.out.println("                            ▶ 이전 메뉴로 돌아갑니다.                         ");
                        oc.clearMenu();
                        orderMenuNum = 0;
                        sc.nextLine();
                        return;
                    default:
                        System.out.println("                            ▶ 😥️ 번호를 잘못 입력하였습니다.                     ");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("                            ▶ 😥️ 잘못 입력하였습니다. 다시 입력해주세요.                  ");
                sc.nextLine();
            }
         }
        }
        if(orderfinish ==  true){
            return;
        }

    }


    /** 클래식 메뉴 메소드  */
    public void classicMenu(){

        while(true){
            System.out.println("=================================================================================");
            System.out.println("                            ▷ 🥙 주문할 메뉴를 선택해주세요.                            ");
            System.out.println("---------------------------------- 클래식 ----------------------------------------");
            System.out.println("                            ▷ 1. 에그마요                                         ");
            System.out.println("                            ▷ 2. 이탈리안 비엠티                                   ");
            System.out.println("                            ▷ 3. 비엘티                                           ");
            System.out.println("                            ▷ 4. 햄                                              ");
            System.out.println("                            ▷ 5. 참치                                            ");
            System.out.println("                                                                                 ");
            System.out.println("                            ▶ 0. 이전 메뉴로                                       ");
            System.out.println("=================================================================================");

            try {
                int classicMenu = sc.nextInt();
                choice = classicMenu - 1;

                switch (classicMenu) {
                    case 1:  case 2: case 3: case 4: case 5:
                        oc.showMenuDetail(choice);

                        if(showMenuAgain == true)
                        {
                            continue;
                        }

                            if (orderContinue() == true) {

                                continue;
                            } else {

                                return;
                            }


                    case 0:
                        return;
                    default:
                        System.out.println("                            ▶ 😥️ 번호를 잘못 입력하였습니다.                     ");
                        sc.nextLine();
                        break;
                }
            }catch (InputMismatchException e)
            {
                System.out.println("                            ▶ 😥 잘못 입력하였습니다. 다시 입력해주세요.");
                sc.nextLine();
            }
        }

    }


    /** 프레쉬&라이트 메소드 */
    public void freshlightMenu(){
        while(true) {
            System.out.println("=================================================================================");
            System.out.println("                            ▷ 🥙 주문할 메뉴를 선택해주세요.                           ");
            System.out.println("-------------------------------- 프레쉬 & 라이트 ---------------------------------");
            System.out.println("                            ▷ 1. 치킨 슬라이스                                   ");
            System.out.println("                            ▷ 2. 치킨 베이컨 아보카도                             ");
            System.out.println("                            ▷ 3. 로스트 치킨                                     ");
            System.out.println("                            ▷ 4. 로티세리 바비큐 치킨                             ");
            System.out.println("                            ▷ 5. 써브웨이 클럽                                  ");
            System.out.println("                            ▷ 6. 베지                                         ");
            System.out.println();
            System.out.println("                            ▶ 0. 이전 메뉴로                                    ");
            System.out.println("=================================================================================");

            try {
                int classicMenu = sc.nextInt();
                choice = classicMenu + 4;

                switch (classicMenu) {
                    case 1: case 2: case 3: case 4: case 5:  case 6:
                        oc.showMenuDetail(choice);
                        if (orderContinue() == true) {
                            continue;
                        } else {

                            return;
                        }

                    case 0:
                        return;
                    default:
                        System.out.println("                            ▶ 😥 번호를 잘못 입력하였습니다.                     ");
                        break;

                }
            }catch (InputMismatchException e)
            {
                System.out.println("                            ▶ 😥 잘못 입력하였습니다. 다시 입력해주세요.                 ");
                sc.nextLine();
            }
        }
    }

    /** 프리미엄 메뉴 메소드 */
    public void premiumMenu(){

        while(true) {
            System.out.println("=================================================================================");
            System.out.println("                            ▷ 🥙 주문할 메뉴를 선택해주세요.                            ");
            System.out.println("--------------------------------- 프리미엄 ---------------------------------------");
            System.out.println("                            ▷ 1. 스파이시 쉬림프                                   ");
            System.out.println("                            ▷ 2. 쉬림프                                           ");
            System.out.println("                            ▷ 3. K-바비큐                                         ");
            System.out.println("                            ▷ 4. 풀드 포크 바비큐                                  ");
            System.out.println("                            ▷ 5. 스테이크 & 치즈                                   ");
            System.out.println("                            ▷ 6. 스파이시 이탈리안                                  ");
            System.out.println("                            ▷ 7. 치킨 데리야끼                                     ");
            System.out.println();
            System.out.println("                            ▶ 0. 이전 메뉴로                                       ");
            System.out.println("=================================================================================");


            try{
            int classicMenu = sc.nextInt();
            choice = classicMenu + 10;

            switch (classicMenu) {
                case 1:     case 2:     case 3:     case 4:     case 5:     case 6:     case 7:
                    oc.showMenuDetail(choice);
                    if (orderContinue() == true) {
                        continue;
                    } else {
                        System.out.println("주문 끝!");

                        return;
                    }

                case 0:
                    return;
                default:
                    System.out.println("                            ▶ 😥️ 번호를 잘못 입력하였습니다.                 ");
                    break;
                }
            }catch (InputMismatchException e)
            {
                System.out.println("                            ▶ 😥‍️ 잘못 입력하였습니다. 다시 입력해주세요.            ");
                sc.nextLine();
            }
        }

    }

    /** 랍스타 메소드 */
    public void newMenu(){

        while(true){

            System.out.println("=================================================================================");
            System.out.println("                            ▷ 🥙 주문할 메뉴를 선택해주세요.                            ");
            System.out.println("----------------------------------- 신제품 -------------------------------------- ");
            System.out.println("                            ▷  1. 랍스터                                          ");
            System.out.println("                                                                                 ");
            System.out.println("                            ▶ 0. 이전 메뉴로                                       ");
            System.out.println("=================================================================================");

            try {
                int classicMenu = sc.nextInt();
                choice = classicMenu + 17;

                switch (classicMenu) {
                    case 1:
                        oc.showMenuDetail(choice);
                        if (orderContinue() == true) {
                            continue;
                        } else {
                            System.out.println("주문 끝!");

                            return;
                        }

                    case 0:
                        return;
                    default:
                        System.out.println("                            ▶ 😥 번호를 잘못 입력하였습니다.                 ");
                        break;
                }
            }catch(InputMismatchException e)
            {
                System.out.println("                            ▶ 😥️ 잘못 입력하였습니다. 다시 입력해주세요.               ");
                sc.nextLine();
            }
        }
    }

    /** 추가 주문 메소드 */
    public boolean orderContinue(){
        boolean result = true;
        while(true) {
            System.out.println("=================================================================================");
            System.out.println("                            ▷ 👉 계속 주문하시겠습니까?                                ");
            System.out.println("                            ▷ 1. 예                                             ");
            System.out.println("                            ▷ 2. 아니오(장바구니로 이동합니다.)                      ");
            System.out.println("=================================================================================");

            try {
                int orderContinue = sc.nextInt();

                switch (orderContinue) {
                    case 1:
                        result = true;
                        return result;
                    case 2:
                        result = false;

                        oc.lastBasket();
                        if(paycancel == true)
                        {
                            orderfinish = false;
                            return result;
                        }
                        else{
                            orderfinish = true;
                            return result;
                        }

                    default:
                        System.out.println("                            ▶ 😥 번호를 잘못 입력하였습니다. 다시 입력해주세요.");
                        continue;
                }
            }catch (InputMismatchException e)
            {
                System.out.println("                            ▶ 😥 잘못 입력하였습니다. 다시 입력해주세요.               ");
                sc.nextLine();
            }
        }
    }


}
