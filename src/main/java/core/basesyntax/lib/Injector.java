package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.exception.WrongAnnotationException;
import core.basesyntax.factory.Factory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {
    public static Object getInstance(Class clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = clazz.getDeclaredConstructor();
        Object instance = constructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field: declaredFields) {
            field.setAccessible(true);
            Object currObj = new Object();
            if (field.getType() == BetDao.class) {
                currObj = Factory.getBetDao();
            } else if (field.getType() == UserDao.class) {
                currObj = Factory.getUserDao();
            }
            if (currObj.getClass().isAnnotationPresent(Dao.class)) {
                field.set(instance, currObj);
            } else {
                throw new WrongAnnotationException("No annotation in this class ");
            }
        }
        return instance;
    }
}
