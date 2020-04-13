package Utilities;

import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;

public class WebElementMgr {

    public static Object getWebElement(Object pageObj, String elementNm) {
        Object foundElement = null;
        Class<?> validationClass = pageObj.getClass();
        Field[] fields = validationClass.getDeclaredFields();
        for(Field field: fields) {
            if(field.getType() == WebElement.class || field.getType() == List.class) {
                field.setAccessible(true);
                if(field.getName().equals(elementNm)) {
                    try {
                        foundElement = field.get(pageObj);
                    } catch (IllegalAccessException e) {
                        System.out.println("error found:"+e.getMessage());
                    }
                }
            }
        }


        return foundElement;
    }

}
