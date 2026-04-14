package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller { //컨트롤러 상속

  private Scanner sc;
  private List<Member> members;
  private String cmd;
  private int lastMemberId = 3;
  private Member loginedMember = null;

  public MemberController(Scanner sc) { //생성자 만듬
    this.sc = sc; //스캐너를 넘겨받으므로써 스캐너선언을 안해도됨
    members = new ArrayList<>();  //members라는 새로운 list를 생성
  }

  public void doAction(String cmd, String actionMethodName) {  //doAction 이라는 메서드를 발동시키기 위해 값을 입력
    this.cmd = cmd;  //cmd 변수를 이용하여 사용자의 입력값을 넘겨받음

    switch (actionMethodName) {  //ArticleController 랑 동일
      case "join":
        doJoin();
        break;
      case "login":
        dologin();
        break;
      case "logout":
        dologout();
        break;
      default:
        System.out.println("Invalid action method");
        break;
    }
  }

  private void doJoin() {  //회원가입 메서드
    System.out.println("==회원 가입==");
    int id = lastMemberId + 1;
    String loginId = null; //loginId 값에 null(빈공간) 입력
    while (true) {  //무한반복문을 통해 회원가입 시작
      System.out.print("로그인 아이디 : ");
      loginId = sc.nextLine().trim(); //loginId 값에 사용자가 입력한 값을 대입
      if (isJoinableLoginId(loginId) == false) {    //isJoinableLoginId 로 내려가보면 (124번째줄)
        System.out.println("이미 사용중인 loginId");  //동일한 값이 나왔으면 false == false => true 여서 if문 출력
        continue;
      }
      break;  //동일한 값이 안나왔으면 true == false => false 이기 때문에 출력안하고 break로 인해 반복문 중지
    }
    String password = null; //먼저 비밀번호 값을 null 로 지정
    while (true) { //비밀번호를 입력받기위해 반복문 시작
      System.out.print("비밀번호 : ");
      password = sc.nextLine().trim();  //password 에 사용자가 입력한 값을 대입
      System.out.print("비밀번호 확인: ");
      String passwordConfirm = sc.nextLine().trim(); // 처음입력한 비밀번호와 같은 것을 확인하기위해 passwordConfirm 변수에 사용자가 입력한 값 대입
      if (password.equals(passwordConfirm) == false) { //두개의 값이 틀리다면 false == false => true
        System.out.println("비번 확인해"); //출력 후 다시 비밀번호입력
        continue;
      }
      break; //두개의 값이 같다면 true == false => false 이기 때문에 if문 출력안함 반복문 중지
    }
    System.out.print("이름 : ");  //아이디와 비밀번호 설정이 끝나면 이름 설정
    String name = sc.nextLine().trim();
    String regDate = Util.getNowStr();  //회원가입 시간 등록
    String updateDate = Util.getNowStr();  //업데이트 시간도 등록



    System.out.println(id + "번 회원이 가입 되었습니다."); //회원번호 출력
    lastMemberId++;
  }

  private boolean isLogined() {   //중복로그인을 하지않기 위한 메서드
    return loginedMember != null;  //loginedMember의 초기값은 null 이므로 null != null 은 false 이다
  } //고로 isLogined 값은 기본적으로 false 이다

  private void dologin() {

      if (isLogined()) { //기본이 false 이기 때문에 출력을 안한다. 다만 loginedMember값에 다른 값이 들어간다면 출력.
        System.out.println("이미 로그인 중 입니다.");
        return;
      }
      System.out.println("== 로그인 시스템 ==");
      System.out.print("아이디를 입력해주세요 : ");
      String Lid = sc.nextLine().trim();  //Lid 변수에 사용자 입력값을 저장 (아이디)
      System.out.print("비밀번호를 입력해주세요 : ");
      String Lpw = sc.nextLine().trim(); //Lpw변수에 사용자 입력값을 저장(비밀번호)

      Member member = getMemberByLoginId(Lid);
    //getMemberByLoginId 메서드를 이용하여 사용자가 입력한 값과 기존에 등록되어있는 값이랑 동일한게 있는지를 확인후 member에 저장.
    //동일한 값이 없는경우 null
      if (member == null) { //만약 member에 값이 없다면 출력
        System.out.println("회원정보가 존재하지 않습니다.");
        return;
      }

      if (member.getLoginPw().equals(Lpw) == false) { //같이 넘어온 비밀번호와 같은지도 확인.
        System.out.println("비밀번호가 틀렸습니다.");
        return;
      }
      loginedMember = member; //loginedMember 값에  member를 대입

      System.out.println(loginedMember.getName() + "님 환영합니다."); //같이 넘어온 이름과 함께 출력


  }

  private void dologout() {
    if (!isLogined()) {//기본값은 false이기 때문에 !false 는 true 출력
      System.out.println("로그아웃 상태입니다. 로그인 해주세요.");
      return;
    } //다른값이 들어가있으면 true이기 때문에 !true는 false 출력안함

    loginedMember = null; //if문을 출력하지 않고넘어온다면 loginedMember의 값을 null로 초기화
    System.out.println("로그아웃 완료."); //출력
  }



  private boolean isJoinableLoginId(String loginId) {  //변수loginId 를 통해 사용자가 입력한 값을 넘겨받고
    for (Member member : members) {  //반복문을 통해 members 안에 들어있는 값들을 member에 하나씩 대입해보고
      if (member.getLoginId().equals(loginId)) { //사용자가 입력한 값이랑 동일한 값이 있을 경우 false를 저장하고
        return false;
      }
    }
    return true;  //동일한 값이 없을 경우 true 를 저장 다시 올라가보면 (49번째줄)
  }

  private Member getMemberByLoginId(String loginId) { //메서드 생성
    for (Member member : members) {  //members의 값을 하나씩 member에 넣어보는 반복문
      if (member.getLoginId().equals(loginId)) { //사용자가 입력한 id값과 기존에 있던 id 값이 같은지를 확인
        return member;  //같은게 있으면 member값에 그 값을 대입
      }
    }
    return null; //같은게 없다면 member값에 null 대입
  }


// 회원 테스트 데이터 생성

  public void makeTestData() {
    System.out.println("==회원 테스트 데이터 생성==");
    members.add(new Member(1, Util.getNowStr(), Util.getNowStr(), "t1", "t1", "테스트 회원1"));
    members.add(new Member(2, Util.getNowStr(), Util.getNowStr(), "t2", "t2", "테스트 회원2"));
    members.add(new Member(3, Util.getNowStr(), Util.getNowStr(), "t3", "t3", "테스트 회원3"));
  }
}