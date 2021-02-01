package app.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@NamedQuery(name = "find_all_menus_with_dish",
  query = "select c from menu_dish c where c.id_dish = ?1")
@Component
@Entity(name="menu_dish")
public class MenuDish {
  @Id
  private long id_menu;
  private long id_dish;

  public MenuDish() {
  }

  public long getId_menu() {
    return id_menu;
  }

  public void setId_menu(long id_menu) {
    this.id_menu = id_menu;
  }

  public long getId_dish() {
    return id_dish;
  }

  public void setId_dish(long id_dish) {
    this.id_dish = id_dish;
  }
}
