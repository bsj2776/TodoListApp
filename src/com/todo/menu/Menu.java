package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println("<TodolistApp의 사용법>");
        System.out.println("[add] : 항목 추가");
        System.out.println("[del] : 항목 삭제");
        System.out.println("[edit] : 항목 수정");
        System.out.println("[ls] : 전체 목록");
        System.out.println("[ls_name] : 제목 순으로 정렬");
        System.out.println("[ls_name_desc] : 제목 역순으로 정렬");
        System.out.println("[ls_date] : 날짜 순으로 정렬");
        System.out.println("[ls_date_desc] : 날짜 역순으로 정렬");
        System.out.println("[find <key>] : 키워드찾기");
        System.out.println("[ls_cate] : 카테고리 확인");
        System.out.println("[find_cate <key>] : 카테고리찾기");
        System.out.println("[exit] : 프로그램 종료");
    }
    
    public static void prompt() {
    	System.out.println();
    	System.out.print("원하는 실행메뉴 > ");
    }
    
}
