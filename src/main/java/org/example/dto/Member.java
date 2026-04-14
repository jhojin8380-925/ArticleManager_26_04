package org.example.dto;

public class Member extends Dto {  //멤버 클래스 만들고 Dto 클래스 상속화
  private String loginId;  //회원 아이디를 담을 변수 생성
  private String loginPw;  //비밀번호 담을 변수 생성
  private String name;  // 회원 이름 생성

  public Member(int id, String regDate, String updateDate, String loginId, String loginPw, String name) {
    //생성자를 소환해서 값을 적용
    this.id = id;
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.loginId = loginId;
    this.loginPw = loginPw;
    this.name = name;
  }

  public int getId() {
    return id;
  }  //회원번호를 출력하기 위해 getId를 사용

  public void setId(int id) {
    this.id = id;
  } //회원 번호를 수정할 때 사용하기 위해 set(세터) 를 사용

  public String getRegDate() {
    return regDate;
  }  //regDate는 (현재시간)

  public void setRegDate(String regDate) {
    this.regDate = regDate;
  }

  public String getUpdateDate() {
    return updateDate;
  } //UpdateDate는 (업데이트 시간)

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String title) {
    this.loginId = loginId;
  }

  public String getLoginPw() {
    return loginPw;
  }

  public void setLoginPw(String body) {
    this.loginPw = loginPw;
  }

  public String getName() {
    return name;
  }

  public void setName(String body) {
    this.loginPw = name;
  }
}