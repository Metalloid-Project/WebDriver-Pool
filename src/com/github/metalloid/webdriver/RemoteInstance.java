package com.github.metalloid.webdriver;

@Deprecated
interface RemoteInstance extends BrowserInstance {
    BrowserInstance createRemoteInstance();
}
