package com.nms.drive.network.test;

public class ThreadAction {// �����鿴JVM�����������̺߳��߳������
	// ��ʾ�߳���Ϣ
	private static void threadMessage(Thread thread, String index) {
		if (thread == null)
			return;
		System.out.println(index + "ThreadName- " + thread.getName()
				+ "  Priority- " + thread.getPriority()
				+ (thread.isDaemon() ? " Daemon" : "")
				+ (thread.isAlive() ? "" : " Inactive"));
	}
	// ��ʾ�߳�����Ϣ
	private static void threadGroupMessage(ThreadGroup group, String index) {
		if (group == null)
			return; // �ж��߳���
		int count = group.activeCount(); // ��û���߳���
		// ��û���߳�����
		int countGroup = group.activeGroupCount();
		// ��ݻ���߳���ָ��������߳�����
		Thread[] threads = new Thread[count];
		// ��ݻ���߳�����ָ��������߳�������
		ThreadGroup[] groups = new ThreadGroup[countGroup];
		group.enumerate(threads, false); // �����л��������ø��Ƶ�ָ�������У�false��ʾ����������������л���������
		group.enumerate(groups, false);
		System.out.println(index + "ThreadGroupName-" + group.getName()
				+ "MaxPriority- " + group.getMaxPriority()
				+ (group.isDaemon() ? " Daemon" : ""));
		// ѭ����ʾ��ǰ����߳���Ϣ
		for (int i = 0; i < count; i++)
			threadMessage(threads[i], index + "    ");
		for (int i = 0; i < countGroup; i++)
			// ѭ����ʾ��ǰ����߳�����Ϣ
			threadGroupMessage(groups[i], index + "    ");// �ݹ���÷���
	}
	public static void threadsList() { // �ҵ����߳��鲢�г���ݹ����Ϣ
		ThreadGroup currentThreadGroup; // ��ǰ�߳���
		ThreadGroup rootThreadGroup; // ���߳���
		ThreadGroup parent;
		// ��õ�ǰ����߳���
		currentThreadGroup = Thread.currentThread().getThreadGroup();
		rootThreadGroup = currentThreadGroup; // ��ø��߳���
		parent = rootThreadGroup.getParent(); // ��ø��߳�
		while (parent != null) { // ѭ���Ը��߳������¸�ֵ
			rootThreadGroup = parent;
			parent = parent.getParent();
		}
		threadGroupMessage(rootThreadGroup, ""); // ��ʾ���߳���
	}
	public static void main(String[] args) { // java��������ڴ�
		System.out.println("�鿴JVM�����е��̵߳Ļ״�����£�");
		ThreadAction.threadsList(); // ���÷�����ʾ�����̵߳���Ϣ
	}
}
