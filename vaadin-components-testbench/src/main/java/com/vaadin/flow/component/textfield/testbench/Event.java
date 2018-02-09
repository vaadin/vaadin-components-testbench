package com.vaadin.flow.component.textfield.testbench;

import com.vaadin.testbench.TestBenchElement;

public class Event {

    /**
     * Dispatches (fires) a custom event of the given type on the element.
     * <p>
     * The event is created without any parameters.
     *
     * @param eventType
     *            the type of custom event to dispatch
     */
    public static void dispatchEvent(TestBenchElement element,
            String eventType) {
        element.getCommandExecutor().executeScript(
                "arguments[0].dispatchEvent(new CustomEvent(arguments[1]));",
                element, eventType);
    }

}
