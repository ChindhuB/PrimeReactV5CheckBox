/*@author Chindhu Babu*/
package utils;

public enum Browser {
    CHROME("chrome"), FIREFOX("firefox"), CHROME_HEADLESS("chrome_headless"), EDGE("edge");

    private String value;

    Browser(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
