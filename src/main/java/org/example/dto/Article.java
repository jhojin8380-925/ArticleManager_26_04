package org.example.dto;

public class Article extends Dto { //게시판 클래스를 만들고 Dto에 상속화
  private String title;  //제목 담을 변수 생성
  private String body;  //내용 담을 변수 생성
  private String member;

  public Article(int id, String regDate, String updateDate, String title, String body) {
    this.id = id;  //생성자를 만들어 변수들에 값을 적용
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.title = title;
    this.body = body;
  }


  public int getId() {  //게시판 번호를 출력하기위해 게터사용
    return id;
  }

  public String getRegDate() {
    return regDate;
  }

  public void setRegDate(String regDate) {
    this.regDate = regDate;
  }

  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

}