/*@author Chindhu Babu*/
package utils;

public enum BrowserVer {
    CHROME("chrome"), FIREFOX("firefox"), CHROME_HEADLESS("chrome_headless"), EDGE("edge");

    private String value;

    BrowserVer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
