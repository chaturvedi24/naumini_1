package Utilities;

import managers.PageObjectMgr;

public class AutomationContext {
    PageObjectMgr pageObjectMgr;

    public AutomationContext() {
        pageObjectMgr = new PageObjectMgr();
    }

    public PageObjectMgr getPageObjectMgr() {
        return pageObjectMgr;
    }

}
