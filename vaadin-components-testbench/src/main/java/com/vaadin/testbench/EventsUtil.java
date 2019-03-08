package com.vaadin.testbench;

import java.util.Map;

public class EventsUtil {

    private EventsUtil() {
        // Util methods only
    }

    /**
     * Dispatches (fires) a custom event of the given type on the element with
     * the given detail
     * <p>
     *
     * @param element
     *            element that will dispatch the event
     * @param eventType
     *            the type of custom event to dispatch
     * @param details
     *            map with properties and values that will be included as event
     *            details
     */
    public static void dispatchEvent(TestBenchElement element, String eventType,
            Map<String, Object> details) {
        element.executeScript(
                "arguments[0].dispatchEvent(new CustomEvent(arguments[1], arguments[2]));",
                element, eventType, details);
    }
}
