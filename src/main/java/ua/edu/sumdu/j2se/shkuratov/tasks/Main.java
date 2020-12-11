package ua.edu.sumdu.j2se.shkuratov.tasks;

public class Main {
	public static void main(String[] args) {
		AbstractTaskList linkedTaskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
		Task[] tasks = new Task[5];
		tasks[0] = new Task("a", 1);
		tasks[1] = new Task("b", 2);
		tasks[2] = new Task("c", 3);
		tasks[3] = new Task("d", 4);
		tasks[4] = new Task("e", 11);
//		System.out.println(linkedTaskList.getClass());
		for (Task task: tasks) {
			linkedTaskList.add(task);
		}
		System.out.println("Worked");
		linkedTaskList.incoming(0, 1000);
//		System.out.println(linkedTaskList.incoming(0, 10).size());
//		linkedTaskList.remove(new Task("a", 1));
//		System.out.println(linkedTaskList.size());
//		LinkedTaskList list = linkedTaskList.incoming(0, 10);
//		list.print();
//		System.out.println("Incoming size = " +
//				list.size());
//		(LinkedTaskList)linkedTaskList.print();
	}
}
