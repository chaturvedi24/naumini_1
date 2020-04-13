package PageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class CourseSearchPage {

    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Category:')]/parent::div//button")
    public static WebElement category;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Software Testing')]")
    public static WebElement softWareTesting;

    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Author:')]/parent::div//button")
    public static WebElement author;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), \"Let's Kode It\")]")
    public static WebElement letsKodeIt;

    @FindBy(how = How.XPATH, using = "//input[@id='search-courses']")
    public static WebElement searchCourses;

    @FindBy(how = How.XPATH, using = "//i[@title='Search']")
    public static WebElement search;

    @FindBy(how = How.XPATH, using = "//img[@class='course-box-image']")
    public static List<WebElement> courses;

}
