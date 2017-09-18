package com.nms.service.notify;

public interface INotifyPublisher {
	
	public void register(INotifyListener listener);
	public void unregister(INotifyListener listener);
	public void notifyListeners(Message msg);

}
