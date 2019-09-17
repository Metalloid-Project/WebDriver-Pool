package com.github.metalloid.webdriver;

interface LocalInstance extends BrowserInstance {
    BrowserInstance createLocalInstance();
}
