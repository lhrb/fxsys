package example.effects;

import fx.data.Effect;
import fx.data.FxId;

public class CreateUserInputFx implements Effect {
    public static FxId id = new FxId("createUserInputFx");

    @Override
    public FxId id() {
        return id;
    }

    @Override
    public Object payload() {
        return null;
    }
}
