package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println("<TodolistApp�� ����>");
        System.out.println("[add] : �׸� �߰�");
        System.out.println("[del] : �׸� ����");
        System.out.println("[edit] : �׸� ����");
        System.out.println("[ls] : ��ü ���");
        System.out.println("[ls_name_asc] : ���� ������ ����");
        System.out.println("[ls_name_desc] : ���� �������� ����");
        System.out.println("[ls_date] : �ð� ������ ����");
        System.out.println("[find <key>] : Ű����ã��");
        System.out.println("[exit] : ���α׷� ����");
    }
    
    public static void prompt() {
    	System.out.println();
    	System.out.print("���ϴ� ����޴� > ");
    }
    
}
