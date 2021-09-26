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
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 추가]" + "\n"
				+ "[카테고리를 입력하세요] > ");
		
		category = sc.nextLine().trim();
		System.out.print("[제목을 입력하세요] > ");
		
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("(이 항목은 이미 목록에 존재합니다.)");
			return;
		}
		
		System.out.print("[내용을 입력하세요] > ");
		desc = sc.nextLine().trim();
		
		System.out.print("[마감일자를 입력하세요] > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[항목 삭제]\n");
		
		System.out.print("[삭제하고 싶은 항목의 번호을 입력하세요] > ");
		
		int l_num = sc.nextInt();
		int i = 1;
		
		for (TodoItem item : l.getList()) {
			if(i == l_num) {
				System.out.println(item);
				System.out.print("[위 항목을 삭제하시겠습니까? (y/n)] > ");
				String select = sc.next().trim();
				if(select.equals("y")) {
					l.deleteItem(item);
					System.out.print("[정상적으로 삭제되었습니다]");
					break;
				}else
					return;
			}
			i++;
		}
		if(l_num > l.getList().size()) {
			System.out.print("\n[그 번호는 리스트 갯수를 초과하였습니다]\n");
		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "[수정하고 싶은 항목의 번호을 입력하세요] > ");
		int l_num = sc.nextInt();
		sc.nextLine();
		int i = 1;
		for (TodoItem item : l.getList()) {
			if(i == l_num) {
				System.out.println(i + ". " + item);
				
				System.out.print("[새 카테고리를 입력하세요] > ");
				String new_category = sc.nextLine();

				System.out.print("[새 제목을 입력하세요] > ");
				String new_title = sc.nextLine().trim();
				if (l.isDuplicate(new_title)) {
					System.out.println("(이 항목은 이미 목록에 존재합니다.)");
					return;
				}
				
				System.out.print("[내용을 입력하세요] > ");
				String new_description = sc.nextLine();
				
				System.out.print("[새 마감일자를 입력하세요]");
				String new_due_date = sc.nextLine();
				
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);
			}
			i++;
		}
		
		if(l_num > l.getList().size()+1) {
			System.out.print("(그 번호가 리스트크기를 초과하였습니다)");
			return;
		}
		
		System.out.print("[정상적으로 수정되었습니다]");
	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, " + "총 "+ l.getList().size() + "개]");
			int num = 1;
		for (TodoItem item : l.getList()) {
			System.out.print(num + ". ");
			System.out.println(item.toString());
			num++;
		}
	}
	
	public static void find(TodoList l, String key) {
		int i = 0; //몇 개의 항목을 읽었는지 확인하기 위한 변수
		int num = 1;
		for(TodoItem item : l.getList()) {
			if(item.getTitle().contains(key)) {
				System.out.println(num + ". " + item);
				i++;
			}else if(item.getDesc().contains(key)) {
				System.out.println(num + ". " + item);
				i++;
			}
			num++;
		}
		if(i > 0) {
			System.out.println(i+"개의 항목을 찾았습니다");
		}
		else {
			System.out.println("(그 키워드는 존재하지 않습니다)");
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
	}
}
