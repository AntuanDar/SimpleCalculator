package common;

import java.util.function.Supplier;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class ObjectFactory<T> implements Supplier<T> {

    private final Supplier<T> factory;
    private T object;

    public ObjectFactory(T object) {
        this.factory = () -> object;
    }

    public ObjectFactory(Supplier<T> factory) {
        this.factory = factory;
    }

    @Override
    public final T get() {
        if (object == null)
            object = factory.get();
        return object;
    }

    public void clear() {
        object = null;
    }

}
