package com.github.metalloid.webdriver;

interface RemoteInstance extends BrowserInstance {
    BrowserInstance createRemoteInstance();
}
