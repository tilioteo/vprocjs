package org.vaadin.tltv.vprocjs.client.ui.test;

import org.vaadin.tltv.vprocjs.client.ui.ProcessingCode;
import org.vaadin.tltv.vprocjs.client.ui.processingcode.ProcessingCodeConnector;
import org.vaadin.tltv.vprocjs.test.server.CustomProcessingCodeExtension;

import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(CustomProcessingCodeExtension.class)
public class CustomProcessingCodeConnector extends ProcessingCodeConnector {

    @Override
    public ProcessingCode getProcessingJavaCode(String codeClass) {
        if (ProcessingCodeImplTest.class.getName().equals(codeClass)) {
            return new ProcessingCodeImplTest();

        } else if (ProcessingJavaCode1.class.getName().equals(codeClass)) {
            return new ProcessingJavaCode1();

        } else if (ProcessingJavaCode2.class.getName().equals(codeClass)) {
            return new ProcessingJavaCode2();
        }
        return null;
    }
}
