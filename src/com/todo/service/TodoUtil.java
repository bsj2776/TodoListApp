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
				+ "[�׸� �߰�]" + "\n"
				+ "[������ �Է��ϼ���] > ");
		
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("(�� �׸��� �̹� ��Ͽ� �����մϴ�.)");
			return;
		}
		
		System.out.print("[������ �Է��ϼ���] > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[�׸� ����]\n");
		
		System.out.print("[�����ϰ� ���� �׸��� ������ �Է��ϼ���] > ");
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
				+ "[�׸� ����]\n"
				+ "[�����ϰ� ���� �׸��� ������ �Է��ϼ���] > ");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("(�� �׸��� �������� �ʽ��ϴ�)");
			return;
		}

		System.out.print("[�߰��ϰ� ���� �׸��� ������ �Է��ϼ���] > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("(�� �׸��� �̹� ��Ͽ� �����մϴ�.)");
			return;
		}
		
		System.out.print("[������ �Է��ϼ���] > ");
		String new_description = sc.nextLine();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("(�׸��� �����Ǿ����ϴ�)");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	//����������(todolist.txt)����/�б� ��� - ���α׷� ���� �� �б� & ���� �� ����
	public static void saveList(TodoList l, String filename) {
		//FileWriter ���
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("<������ ����Ǿ����ϴ�>");
			w.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		//BufferedReader, FlieReader, StringTokenizer ���
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			int count = 0;
			String[] oneline = new String[100];
			while((oneline[count++] = br.readLine()) != null);
			System.out.println(count-1 + "���� ���� �о����ϴ�.");
			for(int i = 0; i < count-1; i++) {
				StringTokenizer st = new StringTokenizer(oneline[i], "##");
				System.out.println(oneline[i]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("<����� ������ �����ϴ�.>");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
