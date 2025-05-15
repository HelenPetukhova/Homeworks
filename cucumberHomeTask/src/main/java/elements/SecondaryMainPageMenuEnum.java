package elements;

public enum SecondaryMainPageMenuEnum {
    TIRES("Автомобильные шины"),
    VIDEO_CARDS("Видеокарты"),
    HEADPHONES("Наушники и гарнитуры"),
    MOBILES("Мобильные телефоны"),
    NOTEBOOKS("Ноутбуки"),
    MOTOR_OILS("Моторные масла"),
    TVS("Телевизоры"),
    PROCESSORS("Процессоры"),
    MONITORS("Мониторы"),
    REFRIGERATORS("Холодильники");

    private String value;

    SecondaryMainPageMenuEnum(String value){
        this.value = value;
    }

        public String getValue(){
            return value;
        }

}



