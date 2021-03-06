/*
 * Copyright (C) 2008-2015 by Holger Arndt
 *
 * This file is part of the Java Data Mining Package (JDMP).
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership and licensing.
 *
 * JDMP is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * JDMP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with JDMP; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.jdmp.gui.dataset.actions;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.jdmp.core.algorithm.basic.CreateIris;
import org.jdmp.core.sample.Sample;
import org.jdmp.gui.dataset.DataSetGUIObject;
import org.ujmp.core.interfaces.CoreObject;

public class IrisDataSetAction extends DataSetAction {
	private static final long serialVersionUID = 3110361907469109630L;

	public IrisDataSetAction(JComponent c, DataSetGUIObject ds) {
		super(c, ds);
		putValue(Action.NAME, "Iris DataSet");
		putValue(Action.SHORT_DESCRIPTION, "Creates a demo DataSet with flowers");
		putValue(Action.MNEMONIC_KEY, KeyEvent.VK_I);
		putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
	}

	public Object call() {
		try {
			Object o = new CreateIris().calculate().get(Sample.TARGET);
			((CoreObject) o).showGUI();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
