package com.sterling.web.batch.components.buttons;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class ButtonAction extends AbstractAction implements Runnable {
	private static final long serialVersionUID = -4240798161887237504L;
	private ActionRunneable action;
	public ButtonAction(ActionRunneable action, String text, Icon icon, String description, char accelerator) {
		super(text, icon);
		this.action = action;
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		putValue(SHORT_DESCRIPTION, description);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(this).start();
	}
	
	public synchronized void run() {
		action.run();
	}
}