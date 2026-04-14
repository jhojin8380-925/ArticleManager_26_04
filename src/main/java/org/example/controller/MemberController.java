package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

  private Scanner sc;
  private List<Member> members;
  private String cmd;
  private int lastMemberId = 3;
  private Member loginedMember = null;

  public MemberController(Scanner sc) {
    this.sc = sc;
    members = new ArrayList<>();
  }

  public void doAction(String cmd, String actionMethodName) {
    this.cmd = cmd;

    switch (actionMethodName) {
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

  private void doJoin() {
    System.out.println("==회원 가입==");
    int id = lastMemberId + 1;
    String loginId = null;
    while (true) {
      System.out.print("로그인 아이디 : ");
      loginId = sc.nextLine().trim();
      if (isJoinableLoginId(loginId) == false) {
        System.out.println("이미 사용중인 loginId");
        continue;
      }
      break;
    }
    String password = null;
    while (true) {
      System.out.print("비밀번호 : ");
      password = sc.nextLine().trim();
      System.out.print("비밀번호 확인: ");
      String passwordConfirm = sc.nextLine().trim();
      if (password.equals(passwordConfirm) == false) {
        System.out.println("비번 확인해");
        continue;
      }
      break;
    }
    System.out.print("이름 : ");
    String name = sc.nextLine().trim();
    String regDate = Util.getNowStr();
    String updateDate = Util.getNowStr();



    System.out.println(id + "번 회원이 가입 되었습니다.");
    lastMemberId++;
  }

  private boolean isLogined() {
    return loginedMember != null;
  }

  private void dologin() {

      if (isLogined()) {
        System.out.println("이미 로그인 중 입니다.");
        return;
      }
      System.out.println("== 로그인 시스템 ==");
      System.out.print("아이디를 입력해주세요 : ");
      String Lid = sc.nextLine().trim();
      System.out.print("비밀번호를 입력해주세요 : ");
      String Lpw = sc.nextLine().trim();

      Member member = getMemberByLoginId(Lid);

      if (member == null) {
        System.out.println("회원정보가 존재하지 않습니다.");
        return;
      }

      if (member.getLoginPw().equals(Lpw) == false) {
        System.out.println("비밀번호가 틀렸습니다.");
        return;
      }
      loginedMember = member;

      System.out.println(loginedMember.getName() + "님 환영합니다.");


  }

  private void dologout() {
    if (!isLogined()) {
      System.out.println("로그아웃 상태입니다. 로그인 해주세요.");
      return;
    }

    loginedMember = null;
    System.out.println("로그아웃 완료.");
  }



  private boolean isJoinableLoginId(String loginId) {
    for (Member member : members) {
      if (member.getLoginId().equals(loginId)) {
        return false;
      }
    }
    return true;
  }

  private Member getMemberByLoginId(String loginId) {
    for (Member member : members) {
      if (member.getLoginId().equals(loginId)) {
        return member;
      }
    }
    return null;
  }


// 회원 테스트 데이터 생성

  public void makeTestData() {
    System.out.println("==회원 테스트 데이터 생성==");
    members.add(new Member(1, Util.getNowStr(), Util.getNowStr(), "t1", "t1", "테스트 회원1"));
    members.add(new Member(2, Util.getNowStr(), Util.getNowStr(), "t2", "t2", "테스트 회원2"));
    members.add(new Member(3, Util.getNowStr(), Util.getNowStr(), "t3", "t3", "테스트 회원3"));
  }
}