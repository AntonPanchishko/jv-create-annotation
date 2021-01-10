package core.basesyntax.controler;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.lib.Inject;
import core.basesyntax.model.Bet;
import core.basesyntax.model.User;
import java.util.Scanner;

public class ConsoleHandler {
    private static final String EXIT = "q";
    private static final String DELIMITER = " ";
    private static final int VALUE_INDEX = 0;
    private static final int RISK_INDEX = 1;
    private static final int NAME_INDEX = 0;
    private static final int LASTNAME_INDEX = 1;
    @Inject
    private BetDao betDao;
    @Inject
    private UserDao userDao;

    public void betHandler() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase(EXIT)) {
                return;
            }
            Bet bet = null;
            try {
                String[] split = command.split(DELIMITER);
                int value = Integer.parseInt(split[VALUE_INDEX]);
                double risk = Double.parseDouble(split[RISK_INDEX]);
                if (value <= 0 || risk <= 0) {
                    System.out.println("Value and risk can not be zero or less");
                    continue;
                }
                bet = new Bet(value, risk);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Enter correct data");
            }
            if (bet != null) {
                betDao.addBet(bet);
                System.out.println(bet.toString());
            }
        }
    }

    public void userHandler() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase(EXIT)) {
                return;
            }
            User user = null;
            try {
                String[] split = command.split(DELIMITER);
                String name = split[NAME_INDEX];
                String lastname = split[LASTNAME_INDEX];
                if (name.equals("") || lastname.equals("")) {
                    System.out.println("Please enter correct name and lastname");
                    continue;
                }
                user = new User(name, lastname);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Enter correct data");
            }
            if (user != null) {
                userDao.addUser(user);
                System.out.println(user.toString());
            }
        }
    }
}
