package descry.internal.abstraction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class InterfaceBinding implements InvocationHandler {

    private final Set<Class<?>> _interfaces;
    private final HashMap<Class<?>, Supplier<?>> _suppliers;

    public InterfaceBinding(Class<?>... interfaces) {
        _interfaces = new HashSet<>(List.of(interfaces));
        _suppliers = new HashMap<>();
    }

    public <T> void setInvocationTarget(Class<T> targetType, T target) {
        setInvocationTargetSupplier(targetType, () -> target);
    }

    public <T> void setInvocationTargetSupplier(Class<T> targetType, Supplier<T> target) {

        //TODO: Loop through and check for an exact match?
        if (!_interfaces.contains(targetType)) {
            return;
        }

        if (target == null) {
            _suppliers.remove(targetType);
        } else {
            _suppliers.put(targetType, target);
        }
    }

    public Object newProxyInstance() {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?>[] interfaces = _interfaces.toArray(new Class<?>[0]);
        return Proxy.newProxyInstance(loader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {

        Class<?> source = method.getDeclaringClass();
        Supplier<?> invocationTargetSupplier = _suppliers.getOrDefault(source, null);
        if (invocationTargetSupplier == null) {
            return null;
        }

        //TODO: May be inaccessible, etc...
        return method.invoke(invocationTargetSupplier.get(), args);
    }
}
