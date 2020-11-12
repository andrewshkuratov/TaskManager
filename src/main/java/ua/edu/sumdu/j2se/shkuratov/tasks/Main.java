package ua.edu.sumdu.j2se.shkuratov.tasks;

public class Main {

	public static void main(String[] args) {
		ArrayTaskList arrayTaskList = new ArrayTaskList();
		arrayTaskList.add(new Task("h", 1));
		System.out.println(arrayTaskList.size());
		arrayTaskList.add(new Task("n", 2));
		System.out.println(arrayTaskList.size());
		arrayTaskList.add(new Task("j", 3));
		System.out.println(arrayTaskList.size());
		arrayTaskList.add(new Task("k", 4));
		System.out.println(arrayTaskList.size());
	}
}
