package ua.goit.java.dao.readyDish;

import ua.goit.java.model.ReadyDish;

import java.util.List;

public interface ReadyDishDao {
    public void addReadyDish(ReadyDish readyDish);
    public List<ReadyDish> getReadyDishes();
}
