package test.java.metalloid.mocks;

import com.github.metalloid.webdriver.browsers.Browser;

public class FakeBrowser extends Browser<FakeWebDriver, FakeOptions> {

    public FakeBrowser(Class<FakeWebDriver> fakeWebDriverClass) {
        super(fakeWebDriverClass);
    }
}
