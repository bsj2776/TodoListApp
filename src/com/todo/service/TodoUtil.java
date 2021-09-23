package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 추가]" + "\n"
				+ "[제목을 입력하세요] > ");
		
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("(이 항목은 이미 목록에 존재합니다.)");
			return;
		}
		
		System.out.print("[내용을 입력하세요] > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[항목 삭제]\n");
		
		System.out.print("[삭제하고 싶은 항목의 제목을 입력하세요] > ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "[수정하고 싶은 항목의 제목을 입력하세요] > ");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("(그 항목은 존재하지 않습니다)");
			return;
		}

		System.out.print("[추가하고 싶은 항목의 제목을 입력하세요] > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("(이 항목은 이미 목록에 존재합니다.)");
			return;
		}
		
		System.out.print("[내용을 입력하세요] > ");
		String new_description = sc.nextLine();
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
			System.out.println(item.toString());
		}
	}
	//데이터파일(todolist.txt)저장/읽기 기능 - 프로그램 시작 시 읽기 & 종료 시 저장
	public static void saveList(TodoList l, String filename) {
		//FileWriter 사용
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("<정보가 저장되었습니다>");
			w.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		//BufferedReader, FlieReader, StringTokenizer 사용
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			int count = 0;
			String[] oneline = new String[100];
			while((oneline[count++] = br.readLine()) != null);
			System.out.println(count-1 + "개의 줄을 읽었습니다.");
			for(int i = 0; i < count-1; i++) {
				StringTokenizer st = new StringTokenizer(oneline[i], "##");
				System.out.println(oneline[i]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("<저장된 파일이 없습니다.>");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
