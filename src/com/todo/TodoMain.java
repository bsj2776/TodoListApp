package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("[제목 순 정렬리스트]");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				System.out.println("[제목 역순 정렬리스트]");
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("[날짜 순 정렬리스트]");
				isList = true;
				break;
				
			case "find":
				String keyword = sc.next();
				sc.nextLine();
				TodoUtil.find(l, keyword);
				break;

			case "exit":
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("원하는 실행메뉴가 존재하지 않습니다. (도움말 - help)");
				break;
			}
			
			if(isList) l.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
