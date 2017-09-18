package com.nms.jms.test;

import com.nms.jms.common.OpviewMessage;
import com.nms.jms.jmsMeanager.JmsUtil;


public class Thread1 implements Runnable{
	private String str;
//	private boolean isRun = true;
	public Thread1(String string) {
		str = string;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			OpviewMessage opviewMessage = new OpviewMessage();
			opviewMessage.setMessageSource(str+"::"+i);
			if("service".equals(str)){
				JmsUtil.sendService(opviewMessage);
			}
		}
//		while(isRun){
//			Number.setCount(Number.getCount()+1);
//			for (int i = 0; i < 3; i++) {
//				System.out.println(str);
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			isRun = false;
//			if(Number.getCount()%3==0){
//				try {
//					TestPool.addCount();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

}
