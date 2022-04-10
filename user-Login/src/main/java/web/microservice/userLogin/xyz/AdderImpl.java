package web.microservice.userLogin.xyz;

import java.util.function.Consumer;
import java.util.function.Function;

public class AdderImpl implements Adder {
	 
    @Override
    public  String add(Function<String, String> f) {
        return f.apply("Welcome ");
    }
 
    @Override
    public void add(Consumer<Integer> f) {}
}
