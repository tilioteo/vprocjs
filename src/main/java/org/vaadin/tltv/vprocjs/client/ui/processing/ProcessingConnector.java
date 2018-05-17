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

package org.vaadin.tltv.vprocjs.client.ui.processing;

import org.vaadin.tltv.vprocjs.client.ui.VProcessing;
import org.vaadin.tltv.vprocjs.shared.ui.processing.ProcessingState;
import org.vaadin.tltv.vprocjs.ui.Processing;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(Processing.class)
public class ProcessingConnector extends AbstractComponentConnector {

	@Override
	protected void init() {
		super.init();

		VProcessing widget = getWidget();
		widget.setUId(getConnectorId());
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		ProcessingState state = getState();
		VProcessing widget = getWidget();

		if (stateChangeEvent.hasPropertyChanged("processingCode")) {
			widget.setProcessingCode(state.processingCode);
			widget.stateChanged();
		}
	}

	@Override
	public VProcessing getWidget() {
		return (VProcessing) super.getWidget();
	}

	@Override
	public ProcessingState getState() {
		return (ProcessingState) super.getState();
	}

}
