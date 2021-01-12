package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.model.Bet;
import java.util.List;

public class BetDaoImpl implements BetDao {
    @Override
    public void addBet(Bet bet) {
        Storage.storage.add(bet);
    }

    @Override
    public List<Bet> getAllBets() {
        return Storage.storage;
    }
}
