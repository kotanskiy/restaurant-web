package ua.goit.java.dao.menu.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.menu.MenuDao;
import ua.goit.java.model.Dish;
import ua.goit.java.model.Menu;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMenuDao implements MenuDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcMenuDao.class);

    public DataSource dataSource;

    @Transactional
    @Override
    public Menu create(int id, String name) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO menu VALUES (?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.execute();
            LOGGER.info("create successful");
        }catch (SQLException e){
            LOGGER.error("create error");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(int id) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM menu WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info("delete successful");
        }catch (SQLException e){
            LOGGER.error("delete error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void addDishInMenu(int idMenu, int idDish) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statementSetDish = connection.prepareStatement("INSERT INTO menu_dishes VALUES (?, ?, ?)");
        PreparedStatement statementGetDish = connection.prepareStatement("SELECT * FROM menu_dishes WHERE id_menu = ?");
        PreparedStatement statementIncrementCountDish = connection.prepareStatement("UPDATE menu_dishes SET count = count + 1 WHERE id_menu = ? AND id_dish = ?")) {
            statementGetDish.setInt(1, idMenu);

            boolean incrementCountOrInsert = true;

            ResultSet resultSet = statementGetDish.executeQuery();
            while (resultSet.next()){
                if (resultSet.getInt("id_dish") == idDish){
                    incrementCountOrInsert = false;
                    break;
                }
            }

            if (incrementCountOrInsert){
                statementSetDish.setInt(1, idMenu);
                statementSetDish.setInt(2, idDish);
                statementSetDish.setInt(3, 1);
                statementSetDish.execute();
                LOGGER.info("Insert new dish in menu");
            }else {
                statementIncrementCountDish.setInt(1, idMenu);
                statementIncrementCountDish.setInt(2, idDish);
                statementIncrementCountDish.execute();
                LOGGER.info("count++");
            }


            LOGGER.info("addDishInMenu successful");
        }catch (SQLException e){
            LOGGER.error("addDishMenu error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void deleteDishInMenu(int idMenu, int idDish) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statementGetDish = connection.prepareStatement("SELECT * FROM menu_dishes WHERE id_menu = ? AND id_dish = ?");
            PreparedStatement statementDeleteDish = connection.prepareStatement("DELETE FROM menu_dishes WHERE id_menu = ? AND id_dish = ?");
            PreparedStatement statementDecrementCountDish = connection.prepareStatement("UPDATE menu_dishes SET count = count - 1 WHERE id_menu = ? AND id_dish = ?")) {
            statementGetDish.setInt(1, idMenu);
            statementGetDish.setInt(2, idDish);

            ResultSet resultSet = statementGetDish.executeQuery();
            resultSet.next();

            if (resultSet.getInt("count") > 1){
                statementDecrementCountDish.setInt(1, idMenu);
                statementDecrementCountDish.setInt(2,idDish);
                statementDecrementCountDish.execute();
                LOGGER.info("count--");
            }else {
                statementDeleteDish.setInt(1, idMenu);
                statementDeleteDish.setInt(2, idDish);
                statementDeleteDish.execute();
                LOGGER.info("Delete");
            }
        }catch (SQLException e){
            LOGGER.error("deleteDishMenu error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public List<Menu> getMenu(String name) {
        List<Menu> menus = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu WHERE menu_name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            boolean flag = true;

            while (resultSet.next()){
                menus.add(create(resultSet));
                flag = false;
            }

            if (flag){
                throw new RuntimeException("this name is not present in the database");
            }else {
                LOGGER.info("get complete");
            }

        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        } catch (PropertyVetoException e) {
            LOGGER.error("invalid query");
            throw new RuntimeException(e);
        }
        return menus;
    }

    @Transactional
    @Override
    public List<Menu> getAll() {
        List<Menu> menus = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()){
            LOGGER.info("connection successful");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM menu");
            while (resultSet.next()){
                menus.add(create(resultSet));
            }
        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        } catch (PropertyVetoException e) {
            LOGGER.error("invalid query");
            throw new RuntimeException(e);
        }
        return menus;
    }

    @Transactional
    @Override
    public List<Dish> getDishesInMenu(int idMenu) {
        List<Dish> dishs = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu_dishes WHERE id_menu = ?");
            PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM dish WHERE id = ?")) {
            statement.setInt(1, idMenu);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int idDish = resultSet.getInt("id_dish");
                statement1.setInt(1, idDish);
                ResultSet resultSet1 = statement1.executeQuery();
                resultSet1.next();
                Dish dish = new Dish();
                dish.setId(idDish);
                dish.setName(resultSet1.getString("name"));
                dishs.add(dish);
            }
        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        }
        return dishs;
    }

    @Transactional
    @Override
    public int getCount(int idMenu, int idDish){
        int count;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT count FROM menu_dishes WHERE id_menu = ? AND id_dish = ?")) {

            statement.setInt(1, idMenu);
            statement.setInt(2, idDish);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        }
        return count;
    }

    @Transactional
    @Override
    public Menu getMenuById(int id){
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Menu menu = new Menu();
            menu.setId(resultSet.getInt("id"));
            menu.setName(resultSet.getString("menu_name"));
            return menu;
        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void updateMenu(Menu menu) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE menu SET menu_name = ? WHERE id = ?")) {
            statement.setString(1, menu.getName());
            statement.setInt(2, menu.getId());
            statement.execute();
        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        }
    }

    private Menu create(ResultSet resultSet) throws SQLException, PropertyVetoException {
        Menu menu = new Menu();
        menu.setId(resultSet.getInt("id"));
        menu.setName(resultSet.getString("menu_name"));
        return menu;
    }
}
