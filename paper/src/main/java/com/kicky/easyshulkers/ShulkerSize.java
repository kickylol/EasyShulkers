package com.kicky.easyshulkers;

public enum ShulkerSize {
    SMALL(9, "Shulker Box"),
    MEDIUM(18, "Shulker Box"),
    LARGE(27, "Shulker Box");

    private final int value;
    private final String type;

    ShulkerSize(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int toInt() {
        return value;
    }

    public String getSize() {
        return type;
    }

    public static ShulkerSize valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case 9 -> SMALL;
            case 18 -> MEDIUM;
            case 27 -> LARGE;
            default -> null;
        };

    }

    public static ShulkerSize fromString(String type) {
        if (type == null) {
            return null;
        }

        return switch (type) {
            case "small" -> SMALL;
            case "medium" -> MEDIUM;
            case "large" -> LARGE;
            default -> null;
        };

    }
}
