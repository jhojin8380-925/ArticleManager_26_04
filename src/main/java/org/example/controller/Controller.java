package org.example.controller;

import org.example.dto.Member;

public class Controller {

  protected static Member loginedMember = null;

  public void doAction(String cmd, String actionMethodName) {

  }

  public static boolean isLogined() {   //중복로그인을 하지않기 위한 메서드
    return loginedMember != null;  //loginedMember의 초기값은 null 이므로 null != null 은 false 이다
  } //고로 isLogined 값은 기본적으로 false 이다
}