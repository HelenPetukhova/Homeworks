@All
Feature: Submenus expanded with hovering over main menu items

  @1
  Scenario: Hovering over "Автобарахолка" menu item expands submenu with categories
    Given Main page is opened
    When User hover over "Автобарахолка" menu item
    Then Submenu titles contain "Автобарахолка" title
    And "Отзывы об авто" submenu item is displayed in "Автобарахолка" section
    And "Новые авто" submenu item is displayed in "Автобарахолка" section
    And "С пробегом" submenu item is displayed in "Автобарахолка" section
    And "Цена с НДС" submenu item is displayed in "Автобарахолка" section
    And "Авто до 4000 р." submenu item is displayed in "Автобарахолка" section
    And "Авто до 10 000 р." submenu item is displayed in "Автобарахолка" section
    And "Минск" submenu item is displayed in "Автобарахолка" section
    And "Гомель" submenu item is displayed in "Автобарахолка" section
    And "Могилев" submenu item is displayed in "Автобарахолка" section
    And "Витебск" submenu item is displayed in "Автобарахолка" section
    And "Гродно" submenu item is displayed in "Автобарахолка" section
    And "Брест" submenu item is displayed in "Автобарахолка" section
    And "Audi" submenu item is displayed in "Автобарахолка" section
    And "BMW" submenu item is displayed in "Автобарахолка" section
    And "Citroen" submenu item is displayed in "Автобарахолка" section
    And "Ford" submenu item is displayed in "Автобарахолка" section
    And "Mazda" submenu item is displayed in "Автобарахолка" section
    And "Mercedes-Benz" submenu item is displayed in "Автобарахолка" section
    And "Nissan" submenu item is displayed in "Автобарахолка" section
    And "Opel" submenu item is displayed in "Автобарахолка" section
    And "Peugeot" submenu item is displayed in "Автобарахолка" section
    And "Renault" submenu item is displayed in "Автобарахолка" section
    And "Toyota" submenu item is displayed in "Автобарахолка" section
    And "Volkswagen" submenu item is displayed in "Автобарахолка" section


  @2
  Scenario: Hovering over "Дома и квартиры" menu item expands submenu with categories
    Given Main page is opened
    When User hover over "Дома и квартиры" menu item
    Then Submenu titles contain "Продажа" title
    And Submenu titles contain "Аренда" title
    And "Минск" submenu item is displayed in "Продажа" section
    And "Брест" submenu item is displayed in "Продажа" section
    And "Витебск" submenu item is displayed in "Продажа" section
    And "Гомель" submenu item is displayed in "Продажа" section
    And "Гродно" submenu item is displayed in "Продажа" section
    And "Могилев" submenu item is displayed in "Продажа" section
    And "1-комнатные" submenu item is displayed in "Продажа" section
    And "2-комнатные" submenu item is displayed in "Продажа" section
    And "3-комнатные" submenu item is displayed in "Продажа" section
    And "4+-комнатные" submenu item is displayed in "Продажа" section
    And "До 30 000 $" submenu item is displayed in "Продажа" section
    And "30 000–80 000 $" submenu item is displayed in "Продажа" section
    And "От 80 000 $" submenu item is displayed in "Продажа" section
    And "Минск" submenu item is displayed in "Аренда" section
    And "Брест" submenu item is displayed in "Аренда" section
    And "Витебск" submenu item is displayed in "Аренда" section
    And "Гомель" submenu item is displayed in "Аренда" section
    And "Гродно" submenu item is displayed in "Аренда" section
    And "Могилев" submenu item is displayed in "Аренда" section
    And "1-комнатные" submenu item is displayed in "Аренда" section
    And "2-комнатные" submenu item is displayed in "Аренда" section
    And "3-комнатные" submenu item is displayed in "Аренда" section
    And "4+-комнатные" submenu item is displayed in "Аренда" section
    And "Комнаты" submenu item is displayed in "Аренда" section
    And "От собственника" submenu item is displayed in "Аренда" section
    And "До 250 $" submenu item is displayed in "Аренда" section
    And "250-500 $" submenu item is displayed in "Аренда" section
    And "От 500 $" submenu item is displayed in "Аренда" section


