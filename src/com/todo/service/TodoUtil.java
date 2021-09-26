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
				+ "[�׸� �߰�]" + "\n"
				+ "[ī�װ��� �Է��ϼ���] > ");
		
		category = sc.nextLine().trim();
		System.out.print("[������ �Է��ϼ���] > ");
		
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("(�� �׸��� �̹� ��Ͽ� �����մϴ�.)");
			return;
		}
		
		System.out.print("[������ �Է��ϼ���] > ");
		desc = sc.nextLine().trim();
		
		System.out.print("[�������ڸ� �Է��ϼ���] > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[�׸� ����]\n");
		
		System.out.print("[�����ϰ� ���� �׸��� ��ȣ�� �Է��ϼ���] > ");
		
		int l_num = sc.nextInt();
		int i = 1;
		
		for (TodoItem item : l.getList()) {
			if(i == l_num) {
				System.out.println(item);
				System.out.print("[�� �׸��� �����Ͻðڽ��ϱ�? (y/n)] > ");
				String select = sc.next().trim();
				if(select.equals("y")) {
					l.deleteItem(item);
					System.out.print("[���������� �����Ǿ����ϴ�]");
					break;
				}else
					return;
			}
			i++;
		}
		if(l_num > l.getList().size()) {
			System.out.print("\n[�� ��ȣ�� ����Ʈ ������ �ʰ��Ͽ����ϴ�]\n");
		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "[�����ϰ� ���� �׸��� ��ȣ�� �Է��ϼ���] > ");
		int l_num = sc.nextInt();
		sc.nextLine();
		int i = 1;
		for (TodoItem item : l.getList()) {
			if(i == l_num) {
				System.out.println(i + ". " + item);
				
				System.out.print("[�� ī�װ��� �Է��ϼ���] > ");
				String new_category = sc.nextLine();

				System.out.print("[�� ������ �Է��ϼ���] > ");
				String new_title = sc.nextLine().trim();
				if (l.isDuplicate(new_title)) {
					System.out.println("(�� �׸��� �̹� ��Ͽ� �����մϴ�.)");
					return;
				}
				
				System.out.print("[������ �Է��ϼ���] > ");
				String new_description = sc.nextLine();
				
				System.out.print("[�� �������ڸ� �Է��ϼ���]");
				String new_due_date = sc.nextLine();
				
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);
			}
			i++;
		}
		
		if(l_num > l.getList().size()+1) {
			System.out.print("(�� ��ȣ�� ����Ʈũ�⸦ �ʰ��Ͽ����ϴ�)");
			return;
		}
		
		System.out.print("[���������� �����Ǿ����ϴ�]");
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, " + "�� "+ l.getList().size() + "��]");
			int num = 1;
		for (TodoItem item : l.getList()) {
			System.out.print(num + ". ");
			System.out.println(item.toString());
			num++;
		}
	}
	
	public static void find(TodoList l, String key) {
		int i = 0; //�� ���� �׸��� �о����� Ȯ���ϱ� ���� ����
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
			System.out.println(i+"���� �׸��� ã�ҽ��ϴ�");
		}
		else {
			System.out.println("(�� Ű����� �������� �ʽ��ϴ�)");
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
			System.out.println("<" + count + "���� �׸��� �о����ϴ�>");
		} catch (FileNotFoundException e) {
			System.out.println("<����� ������ �����ϴ�.>");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
