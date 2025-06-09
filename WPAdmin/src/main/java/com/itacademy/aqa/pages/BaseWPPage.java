package com.itacademy.aqa.pages;

/**
 * Base class for all WordPress pages. All pages have to implement isPageOpened() method
 */

public abstract class BaseWPPage {

    protected BaseWPPage(){

    }

    public abstract boolean isPageOpened();

}
