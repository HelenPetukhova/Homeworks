package elements;

public enum MainMenuItemEnum {
    CATALOG("Каталог"),
    NEWS("Новости"),
    AUTO_MARKET("Автобарахолка"),
    HOUSES_FLATS("Дома и квартиры"),
    SERVICES("Услуги"),
    FLEA_MARKET("Барахолка"),
    FORUM("Форум");


    private String value;

    MainMenuItemEnum(String value) {
        this.value = value;
    }

    public String getValue() {

        return value;
    }

}

