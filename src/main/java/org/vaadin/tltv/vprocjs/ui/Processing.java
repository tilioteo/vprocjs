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

package org.vaadin.tltv.vprocjs.ui;

import org.vaadin.tltv.vprocjs.shared.ui.processing.ProcessingState;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;

/**
 * Server side Vaadin component wrapping processing.js javascript library. </br>
 * </br>
 * 
 * Requires that processing.min.js file exist in the org.vaadin.tltv.vprocjs.ui
 * package.
 * 
 * @author Tltv
 * 
 */
@JavaScript({ "processing.min.js" })
@SuppressWarnings("serial")
public class Processing extends CustomComponent {

	private AbsoluteLayout mainLayout;

	public Processing() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private void buildMainLayout() {
		mainLayout = new AbsoluteLayout();
	}

	@Override
	public ProcessingState getState() {
		return (ProcessingState) super.getState();
	}

	public String getProcessingCode() {
		return getState().processingCode;
	}

	public void setProcessingCode(String code) {
		getState().processingCode = code != null ? code.trim() : "";
	}

}
