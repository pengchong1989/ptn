package com.nms.ui.ptn.clock.view.cx.time;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.clock.view.cx.dialog.TCCreateDialog;

public class MyItemListener implements ItemListener {

	private TCCreateDialog tCCreateDialog = null;

	private int whichCombox = 0;

	public MyItemListener(TCCreateDialog tCCreateDialog, int whichCombox) {

		this.tCCreateDialog = tCCreateDialog;
		this.whichCombox = whichCombox;
	}

	public void itemStateChanged(ItemEvent itemEvent) {

		try {

			if (null == tCCreateDialog) {

				throw new Exception("No Combox is listened! ");
			}
			/* 1:监听‘接口类型’Combox； */
			if (whichCombox == 1) {

				interfaceType(itemEvent);
			}
			/* 2:监听‘延时机制’Combox； */
			if (whichCombox == 2) {

				delayMechanism(itemEvent);
			}
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void interfaceType(ItemEvent itemEvent) throws Exception {

		String name = null;
		try {

			if (itemEvent.getStateChange() == 1) {
				try {

					name = ((ControlKeyValue) itemEvent.getItem()).getName();
					if ("透传".equalsIgnoreCase(name)) {

						this.tCCreateDialog.getDelayMechanism_().setEnabled(false);
						this.tCCreateDialog.getOperationMode_().setEnabled(false);
						this.tCCreateDialog.getDelayReqPacketsCycle_().setEnabled(false);
						this.tCCreateDialog.getPdelReqPacketsCycle_().setEnabled(false);
					} else {

						this.tCCreateDialog.getDelayMechanism_().setEnabled(true);
						this.tCCreateDialog.getOperationMode_().setEnabled(true);
						this.tCCreateDialog.getDelayReqPacketsCycle_().setEnabled(true);
						this.tCCreateDialog.getPdelReqPacketsCycle_().setEnabled(true);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		} catch (Exception e) {

			throw e;
		} finally {

			name = null;
		}
	}

	private void delayMechanism(ItemEvent itemEvent) throws Exception {

		String name = null;
		try {

			if (itemEvent.getStateChange() == 1) {
				try {

					name = ((ControlKeyValue) itemEvent.getItem()).getName();
					if ("端到端".equalsIgnoreCase(name)) {

						this.tCCreateDialog.getDelayReqPacketsCycle_().setEnabled(true);
						this.tCCreateDialog.getPdelReqPacketsCycle_().setEnabled(false);
					} else {

						this.tCCreateDialog.getDelayReqPacketsCycle_().setEnabled(false);
						this.tCCreateDialog.getPdelReqPacketsCycle_().setEnabled(true);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		} catch (Exception e) {

			throw e;
		} finally {

			name = null;
		}
	}
}
