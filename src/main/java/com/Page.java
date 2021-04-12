package com;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Page {

    public static final Logger LOGGER = LoggerFactory.getLogger(Page.class);

    /**
     * Helper method to Initialize page object.
     *
     * @return page Object instance
     */
    public static <T> T on(Class<T> klass) {
        return new Page().get(klass);
    }

    /**
     * Initialize page object.
     *
     * @return page object instance
     */
    private <T> T get(Class<T> klass) {
        try {
            Constructor<T> constructor = klass.getConstructor();
            T page = constructor.newInstance();
            PageFactory.initElements(new ExtendedFieldDecorator(new DefaultElementLocatorFactory(DriverProvider.webDriver())), page);
            return page;
        } catch (Exception e) {
            LOGGER.error("PageObject of type {" + klass.getName() + "} cannot be created", e);
        }
        return null;
    }

    public static class ExtendedFieldDecorator extends DefaultFieldDecorator {
        public ExtendedFieldDecorator(ElementLocatorFactory factory) {
            super(factory);
        }

        @Override
        public Object decorate(ClassLoader loader, Field field) {
            if (field.getType().equals(Select.class)) {
                ElementLocator locator = factory.createLocator(field);
                if (locator == null) {
                    return null;
                }
                return new Select(proxyForLocator(loader, locator));
            }
            return super.decorate(loader, field);
        }
    }
}

