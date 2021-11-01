package com.RyanJeffares.lox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoxClass implements LoxCallable {
    final String name;
    private final Map<String, LoxFunction> methods;
    private final List<String> properties = new ArrayList<>();

    LoxClass(String name, Map<String, LoxFunction> methods) {
        this.name = name;
        this.methods = methods;
    }

    LoxFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        return null;
    }

    @Override
    public String toString() {
        return "<class " + name + ">";
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        var instance = new LoxInstance(this);
        var initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, args);
        }

        return instance;
    }

    @Override
    public int arity() {
        var initializer = findMethod("init");
        if (initializer == null) {
            return 0;
        }
        return initializer.arity();
    }
}
