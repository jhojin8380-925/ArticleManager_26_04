package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

  List<Article> articles; //ArrayList 변수 articles 선언

  public App() {
    articles = new ArrayList<>();  //articles 라는 ArrayList 를 만듦
  }

  public void run() {   //run 함수 생성
    Scanner sc = new Scanner(System.in);  //스캐너

    System.out.println("==프로그램 시작==");

    MemberController memberController = new MemberController(sc); //MemberController 객체 불러오기
    ArticleController articleController = new ArticleController(sc);  //ArticleController 객체 불러오기

    articleController.makeTestData();  //article의 테스트 데이터 호출
    memberController.makeTestData();   //member의 테스트 데이터 호출

    while (true) {  //반복문 시작
      System.out.print("명령어 ) ");
      String cmd = sc.nextLine().trim();   //명령어를 입력받음

      if (cmd.equals("exit")) {   //exit 입력했을시 반복문 종료
        break;
      } else if (cmd.length() == 0) {        // 사용자가 입력한 값이 없으면 출력
        System.out.println("명령어 입력하세요");
        continue;  //다시 반복문 시작
      }

      if (cmd.equals("member join")) {   //member join 을 입력했을 시 doJoin 함수 호출
        memberController.doJoin();

      } else if (cmd.equals("article write")) { //article write 을 입력했을 시 dowrite 함수 호출
        articleController.dowrite(cmd);

      } else if (cmd.startsWith("article list")) {  //article list 을 입력했을 시 dolist 함수 호출
        articleController.dolist(cmd);

      } else if (cmd.startsWith("article detail")) {  //article detail 을 입력했을시 dodetail 함수 호출
        articleController.dodetail(cmd);

      } else if (cmd.startsWith("article delete")) {  //article delete 을 입력했을시 dodelete 함수 호출
        articleController.dodelete(cmd);

      } else if (cmd.startsWith("article modify")) {  //article modify 을 입력했을시 domodyfi 함수 호출
        articleController.domodyfi(cmd);

      } else {                                   // 그 외 잘못된 값 입력시 출력
        System.out.println("사용할 수 없는 명령어입니다");
      }
    }
    System.out.println("==프로그램 끝==");
    sc.close();    //스캐너 종료
  }


}