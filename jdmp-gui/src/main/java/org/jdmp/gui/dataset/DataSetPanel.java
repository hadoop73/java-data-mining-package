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

package org.jdmp.gui.dataset;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import org.jdmp.gui.sample.SampleListPanel;
import org.ujmp.gui.MatrixGUIObject;
import org.ujmp.gui.panels.AbstractPanel;
import org.ujmp.gui.panels.MatrixEditorPanel;

public class DataSetPanel extends AbstractPanel {
	private static final long serialVersionUID = -742923218356821961L;

	private final JSplitPane splitPane = new JSplitPane();

	public DataSetPanel(DataSetGUIObject ds) {
		super(ds);
		splitPane.setLeftComponent(new MatrixEditorPanel("Meta Data", (MatrixGUIObject) ds
				.getMetaData().getGUIObject()));
		splitPane.setRightComponent(new SampleListPanel(ds.getCoreObject()));
		add(splitPane, BorderLayout.CENTER);
	}

}
