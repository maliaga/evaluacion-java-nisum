package dev.mario.evaluacionjavanisum.domain.model;

public enum ACTIVE {
    YES(true),
    NO(false);

    private final boolean value;

    ACTIVE(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
