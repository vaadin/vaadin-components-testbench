package com.vaadin.testbench;

import java.util.Map;

public class EventsUtil {

    private EventsUtil() {
        // Util methods only
    }

    /**
     * Dispatches (fires) a custom event of the given type on the element with
     * the given properties
     *
     * @param element
     *            element that will dispatch the event
     * @param eventType
     *            the type of custom event to dispatch
     * @param customEventInit
     *            map with properties and values that will be used to initialize
     *            the event
     */
    public static void dispatchEvent(TestBenchElement element, String eventType,
            Map<String, Object> customEventInit) {
        element.executeScript(
                "arguments[0].dispatchEvent(new CustomEvent(arguments[1], arguments[2]));",
                element, eventType, customEventInit);
    }
}
