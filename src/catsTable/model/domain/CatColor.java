package catsTable.model.domain;

public enum CatColor {
    RED("Red"), BLACK("Black"), WHITE("White"), STRIPED("Striped");
    private String name;

    CatColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static catsTable.model.domain.CatColor findColor(String color) {
        for (catsTable.model.domain.CatColor catColor : values()) {
            if (catColor.getName().equals(color)) {
                return catColor;
            }
        }
        throw new RuntimeException("Cat color not found");
    }
}
