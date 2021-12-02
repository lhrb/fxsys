package example.effects;

import example.DB;
import fx.data.Effect;
import fx.data.FxId;

public class AddUserFx implements Effect<DB.User>
{
    public static FxId id = new FxId("addUser");
    private final DB.User user;

    public AddUserFx(DB.User user) {
        this.user = user;
    }

    @Override
    public FxId id() {
        return id;
    }

    @Override
    public DB.User payload() {
        return user;
    }
}
