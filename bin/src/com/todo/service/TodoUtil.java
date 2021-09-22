package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[항목 추가]"
				+ "[제목을 입력하세요] > \n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("(이 항목은 이미 목록에 존재합니다.)");
			return;
		}
		
		System.out.println("[내용을 입력하세요] > ");
		desc = sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[항목 삭제]\n");
		String title = sc.next();
		
		System.out.println("[삭제하고 싶은 항목의 제목을 입력하세요] > \n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[항목 수정]\n"
				+ "[수정하고 싶은 항목의 제목을 입력하세요] > \n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("(그 항목은 존재하지 않습니다)");
			return;
		}

		System.out.println("[추가하고 싶은 항목의 제목을 입력하세요] > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("(이 항목은 이미 목록에 존재합니다.)");
			return;
		}
		
		System.out.println("[내용을 입력하세요] > ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("(항목이 수정되었습니다)");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println("[제목] : " + item.getTitle() + "  [내용] :  " + item.getDesc());
		}
	}
}
