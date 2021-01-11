package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.exception.WrongAnnotationException;
import core.basesyntax.factory.Factory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

public class Injector implements Inject {

    public static Object getInstance(Class clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = clazz.getDeclaredConstructor();
        Object instance = constructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field: declaredFields) {
            if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                Predicate predicate = o -> !(Factory.getBetDao().getClass()
                        .isAnnotationPresent(Dao.class)
                        || !(Factory.getUserDao().getClass()
                        .isAnnotationPresent(Dao.class)));
                if (predicate.test(field)) {
                    throw new WrongAnnotationException("No such class annotation");
                }
                if (field.getType() == UserDao.class) {
                    field.set(instance, Factory.getUserDao());
                } else if (field.getType() == BetDao.class) {
                    field.set(instance, Factory.getBetDao());
                }
            }
        }
        return instance;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
