package com.RyanJeffares.lox;

import java.util.HashMap;
import java.util.Map;

public class LoxInstance {
    private LoxClass cls;
    private final Map<String, Object> fields = new HashMap<>();

    LoxInstance(LoxClass cls) {
        this.cls = cls;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        var method = cls.findMethod(name.lexeme);
        if (method != null) {
            return method.bind(this);
        }

        throw new RuntimeError(name, "Property " + name.lexeme + " not found for instance of class " + cls.name + ".");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString() {
        return "<instance " + cls.toString() + ">";
    }
}
