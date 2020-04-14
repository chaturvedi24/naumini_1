package managers;

import org.openqa.selenium.support.PageFactory;

public class PageObjectMgr {

    static Object currentPage;

    public Object getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String pageNm, String pkg) throws Exception{
        try {
            currentPage = PageFactory.initElements(Driver.getDriver(), Class.forName(pkg+"."+pageNm));
        } catch (ClassNotFoundException e) {
            throw new Exception("Class not found to initialize "+pageNm);
        }
    }
}
