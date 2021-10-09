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
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 추가]" + "\n"
				+ "[제목을 입력하세요] > ");
		title = sc.nextLine().trim();
		
		if (l.isDuplicate(title)) {
			System.out.println("[ERROR] 제목이 중복됩니다!");
			return;
		}
	
		System.out.print("[카테고리를 입력하세요] > ");
		category = sc.nextLine().trim();
		
		System.out.print("[내용을 입력하세요] > ");
		desc = sc.nextLine().trim();
		
		System.out.print("[마감일자를 입력하세요] > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(l.addItem(t) > 0)
			System.out.println("추가되었습니다!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[항목 삭제]\n");
		System.out.print("[삭제하고 싶은 항목의 번호을 입력하세요] > ");
		
		int index = sc.nextInt();
		if(l.deleteItem(index) > 0)
			System.out.println("삭제되었습니다!");
	}


	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "[수정하고 싶은 항목의 번호을 입력하세요] > ");
		int index = sc.nextInt();
		
		sc.nextLine(); //enter
		
		System.out.print("[새 제목을 입력하세요] > ");
		new_title = sc.nextLine().trim();
		System.out.print("[새 카테고리를 입력하세요] > ");
		new_category = sc.nextLine().trim();
		System.out.print("[새 내용을 입력하세요] > ");
		new_desc = sc.nextLine().trim();
		System.out.print("[새 마감일자를 입력하세요] > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("수정되었습니다!");
	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item+" ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}
	//데이터파일(todolist.txt)저장/읽기 기능 - 프로그램 시작 시 읽기 & 종료 시 저장
	/*public static void saveList(TodoList l, String filename) {
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
			String oneline;
			while((oneline = br.readLine()) != null) {
				String[] list = oneline.split("##");
				String category = list[0];
				String title = list[1];
				String description = list[2];
				String due_date = list[3];
				String current_date = list[4];
				
				TodoItem item = new TodoItem(category, title, description, due_date, current_date);	
				l.addItem(item);
				count++;
			}	
			br.close();
			System.out.println("<" + count + "개의 항목을 읽었습니다>");
		} catch (FileNotFoundException e) {
			System.out.println("<저장된 파일이 없습니다.>");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
