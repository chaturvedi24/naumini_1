package Utilities;

import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

public class WebElementMgr {

    public static WebElement getWebElement(Object pageObj, String elementNm) {
        WebElement foundElement = null;
        Class<?> validationClass = pageObj.getClass();
        Field[] fields = validationClass.getDeclaredFields();
        for(Field field: fields) {
            if(field.getType() == WebElement.class) {
                field.setAccessible(true);
                if(field.getName().equals(elementNm)) {
                    try {
                        foundElement = (WebElement) field.get(pageObj);
                    } catch (IllegalAccessException e) {
                        System.out.println("error found:"+e.getMessage());
                    }
                }
            }
        }


        return foundElement;
    }

}
