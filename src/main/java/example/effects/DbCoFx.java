package example.effects;

import example.DB;
import fx.data.CoFxId;
import fx.data.Coeffect;

public class DbCoFx implements Coeffect<DB> {
    public static final CoFxId id = new CoFxId("db-coeffect");
    private final DB db;

    public DbCoFx(DB db) {
        this.db = db;
    }

    @Override
    public CoFxId id() {
        return id;
    }

    @Override
    public DB payload() {
        return db;
    }
}
