package org.example.ArticleManager;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;

import java.util.Scanner;

public class App {
  public void run() {
    Scanner sc = new Scanner(System.in);

    System.out.println("==프로그램 시작==");

    MemberController memberController = new MemberController(sc); //memberController 객체를 새로만들고 sc의 값을 넘겨줌 (스캐너넘겨주기)
    ArticleController articleController = new ArticleController(sc); //위와동일

    articleController.makeTestData();  //테스트 테이터를 가져옴
    memberController.makeTestData(); //테스트 데이터를 가져옴

    Controller controller = null;  //controller 변수에 null값을 넣음

    while (true) {
      System.out.print("명령어 ) "); //무한반복문을 이용하여 사용자에게 입력값을 받음
      String cmd = sc.nextLine().trim();

      if (cmd.equals("exit")) { //exit 입력시 프로그램 종료
        break;
      } else if (cmd.length() == 0) {  //입력한 값이 없을 때 출력
        System.out.println("명령어 입력하세요");
        continue;
      }

      String[] cmdBits = cmd.split(" ");
      String controllerName = cmdBits[0];
      if (cmdBits.length == 1) {
        System.out.println("명령어 확인 필요");
        continue;
      }
      String actionMethodName = cmdBits[1];

      String forLoginChecks = controllerName + "/" + actionMethodName;
      //article write => article/write
      switch (forLoginChecks) {
        case "article/write":
        case "article/delete":
        case "article/modify":
        case "member/logout":
          if (Controller.isLogined() == false) {
            System.out.println("로그인을 해주세요.");
            continue;
          }
          break;
      }
      switch (forLoginChecks) {
        case "member/login":
        case "member/join":
          if (Controller.isLogined()) {
            System.out.println("로그아웃을 해주세요.");
            continue;
          }
          break;
      }


      if (controllerName.equals("article")) {
        controller = articleController;
      } else if (controllerName.equals("member")) {
        controller = memberController;
      } else {
        System.out.println("지원하지 않는 기능");
        continue;
      }
      controller.doAction(cmd, actionMethodName);
    }
    System.out.println("==프로그램 끝==");
    sc.close();
  }
}