/*
 * Copyright 2013 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vaadin.tltv.vprocjs.client.ui;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Client side GWT widget wrapping processing.js javascript library. Made for
 * Vaadin Framework 7.</br></br>
 * 
 * By default, this widget has a simple sketch written with Processing
 * Visualization Language that draws a big X over the canvas.</br></br>
 * 
 * This default sketch can be overridden by the
 * {@link #setProcessingCode(String)} method, if you want to use Processing
 * Visualization Language.</br>
 * 
 * Or alternatively by {@link #setProcessingJavaCode(ProcessingCode)} method, if
 * you want to write sketch with Java (with GWT restrictions).
 * 
 * @author Tltv, modified by Kamil Morong
 * 
 */
public class VProcessing extends Composite {

	public static final String CLASSNAME = "VProcessing";

	private ProcessingJavascriptObject proJsObj;

	private final VerticalPanel panel;

	protected Canvas canvas;

	/**
	 * Default Processing Visualization Language code.
	 */
	private static final String DEFAULT_CODE =
			"void setup() {\n" + "size(300, 250);\n" +
			"smooth();\n" + "}\n" + "void draw() {\n" +
			"line(0,0,width,height);\n" + "line(width,0,0,height);\n" + "}";

	/**
	 * Current Processing code.
	 */
	private String processingCode = null;

	private String uid;

	public VProcessing() {

		panel = new VerticalPanel();

		canvas = Canvas.createIfSupported();
		setCanvasSize(300, 300);

		canvas.setStyleName("processing");

		panel.add(canvas);

		initWidget(panel);

		setStyleName(CLASSNAME);
	}

	public void setCanvasSize(int width, int height) {
		canvas.setWidth(width + "px");
		canvas.setHeight(height + "px");
	}

	/**
	 * Called when state is changed. Basically when server has changed it.
	 */
	public void stateChanged() {
		if ("".equals(processingCode)) {
			processingCode = null;
		}

		initCanvas();
	}

	public void setUId(String uid) {
		this.uid = uid;
	}

	/**
	 * Get Processing Visualization Language code.
	 * 
	 * @return String
	 */
	public String getProcessingCode() {
		return processingCode;
	}

	/**
	 * Set Processing Visualization Language code.
	 * 
	 * @return
	 */
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	int getCanvasAbsoluteTop() {
		return canvas.getAbsoluteTop();
	}

	int getCanvasAbsoluteLeft() {
		return canvas.getAbsoluteLeft();
	}

	native private static Element getCanvas(String canvasid, String canvasClass, Element root) /*-{
		if (canvasClass) {
			var targetcanvas = null;
			var cvs = root.getElementsByTagName('canvas');
			for ( var i = 0; i < cvs.length; i++ ) {
				if(cvs[i].className == canvasClass && cvs[i].id == canvasid) {
					targetcanvas = cvs[i];
				}
			}
			return targetcanvas;
		}
		return null;
	}-*/;

	native private static void initProcessingJsWithCode(VProcessing vprocessing, String canvasClass, Element root,
			String processingCode, String canvasid) /*-{
		var targetcanvas = @org.vaadin.tltv.vprocjs.client.ui.VProcessing::getCanvas(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/dom/client/Element;)(canvasid, canvasClass, root);
		targetcanvas.getContext('2d').clearRect(0, 0, targetcanvas.width, targetcanvas.height);
		
		var p =  vprocessing.@org.vaadin.tltv.vprocjs.client.ui.VProcessing::proJsObj;
		if (p) {
			try {
				p.bindCallbacks($wnd);
			} catch(e) {}
				
			p.exit();
		}
		
		try {
			p = new $wnd.Processing(targetcanvas, processingCode);
			vprocessing.@org.vaadin.tltv.vprocjs.client.ui.VProcessing::proJsObj = p;
			try {
				p.bindCallbacks($wnd);
			} catch(e) {}
				
		} catch(e) {
			alert("Failed to execute processing code!\n\nError: " + e.message);
		}
	}-*/;

	/**
	 * Initializes the canvas and also the ProcessingJsObject. Detects the
	 * changed sketch and updates the component.
	 */
	private void initCanvas() {
		if (canvas.getStyleName() == null || canvas.getStyleName().length() < 1) {
			return;
		}

		String id = uid + "_canvas";
		canvas.getElement().setAttribute("id", id);

		String codeToUse = processingCode;
		if (codeToUse == null || codeToUse.trim().isEmpty()) {
			codeToUse = DEFAULT_CODE;
		}
		initProcessingJsWithCode(this, canvas.getStyleName(), getElement(), codeToUse, id);
	}

}
