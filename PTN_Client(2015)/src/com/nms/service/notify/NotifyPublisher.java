package com.nms.service.notify;

import java.util.Vector;

public class NotifyPublisher implements INotifyPublisher {

	private static NotifyPublisher instance = new NotifyPublisher();
	private Vector<INotifyListener> listeners = new Vector<INotifyListener>();
	
	private NotifyPublisher(){
		
	}
	
	public static NotifyPublisher getInstance(){
		return instance;
	}
	
	@Override
	public synchronized void notifyListeners(Message msg) {
		if(listeners.isEmpty()){
			return;
		}
		for (INotifyListener listener : listeners) {
			listener.onMessage(msg);
		}
	}

	@Override
	public synchronized void register(INotifyListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public synchronized void unregister(INotifyListener listener) {
		if (listeners.contains(listener)) {
			listeners.removeElement(listener);
		}
	}

}
