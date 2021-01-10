package core.basesyntax;

import core.basesyntax.controler.ConsoleHandler;
import core.basesyntax.dao.BetDao;
import core.basesyntax.factory.Factory;
import core.basesyntax.lib.Injector;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConsoleHandler consoleHandler = (ConsoleHandler) Injector
                .getInstance(ConsoleHandler.class);
        System.out.println("Enter value and risk");
        consoleHandler.betHandler();
        BetDao betDao = Factory.getBetDao();
        System.out.println("All bets: " + betDao.getAllBets());
    }
}
