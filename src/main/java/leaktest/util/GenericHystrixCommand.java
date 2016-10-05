package leaktest.util;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import rx.Observable;

import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class GenericHystrixCommand<T> extends HystrixCommand<T> {
    private Supplier<T> method;
    private Function<Throwable, T> fallback;

    public static <T> T execute(String commandKey, String groupKey, Supplier<T> method,
                                Function<Throwable, T> fallback) {
        GenericHystrixCommand<T> command = new GenericHystrixCommand<>(commandKey, groupKey, method, fallback);
        return command.execute();
    }

    public static <T> Observable<T> toObservable(String commandKey, String groupKey, Supplier<T> methodSupplier,
                                                 Function<Throwable, T> fallbackSupplier) {
        GenericHystrixCommand<T> command = new GenericHystrixCommand<>(commandKey, groupKey, methodSupplier,
                fallbackSupplier);
        return command.toObservable();
    }

    public GenericHystrixCommand(Setter setter, Supplier<T> method, Function<Throwable, T> fallback) {
        super(requireNonNull(setter, "setter is required"));
        this.method = requireNonNull(method, "method supplier is required");
        this.fallback = fallback;
    }

    public GenericHystrixCommand(String commandKey, String groupKey, Supplier<T> method,
                                 Function<Throwable, T> fallback) {
        this(buildSetterWithGroupAndCommandKeys(groupKey, commandKey), method, fallback);
    }

    @Override
    protected T run() throws Exception {
        return method.get();
    }

    @Override
    protected T getFallback() {
        return fallback != null ? fallback.apply(getExecutionException()) : super.getFallback();
    }

    public static Setter buildSetterWithGroupAndCommandKeys(String groupKey, String commandKey) {
        Setter setter = Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(requireNonNull(groupKey, "groupKey is required")));
        if (commandKey != null) {
            setter = setter.andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
        }
        return setter;
    }

}
