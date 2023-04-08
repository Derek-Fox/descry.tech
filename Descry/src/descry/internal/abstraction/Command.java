package descry.internal.abstraction;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public record Command(Method method, Object... args) implements Serializable {

    public Object invoke(Object target) throws Throwable {
        return method.invoke(target, args);
    }

    public static abstract class Dispatcher<T> {

        private final Class<T> _interfaceType;

        public Dispatcher(Class<T> interfaceType) {
            if (!interfaceType.isInterface()) {
                throw new RuntimeException();
            }
            _interfaceType = interfaceType;
        }

        public abstract Object dispatch(Command command, T target);

        /**
         * Creates a filter proxy for T.
         * Invocations on the proxy are passed as commands to {@link Dispatcher#dispatch(Command, Object)}.
         *
         * @param target Provides the underlying implementation for the proxy.
         *               Supplies the {@code target} parameter in {@link Dispatcher#dispatch(Command, Object)}.
         * @return The filter proxy.
         */
        public T newProxyInstance(Supplier<T> target) {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class<?>[] interfaces = {_interfaceType};
            InvocationHandler handler = (proxy, method, args) -> dispatch(new Command(method, args), target.get());
            Object proxy = Proxy.newProxyInstance(classLoader, interfaces, handler);
            return _interfaceType.cast(proxy);
        }
    }
}
