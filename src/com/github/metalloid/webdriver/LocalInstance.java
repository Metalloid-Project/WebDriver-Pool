package com.github.metalloid.webdriver;

@Deprecated
interface LocalInstance extends BrowserInstance {
    BrowserInstance createLocalInstance();
}
