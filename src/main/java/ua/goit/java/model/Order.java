package ua.goit.java.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee idEmployee;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JoinTable(
            name = "dish_list",
            joinColumns = @JoinColumn(name = "id_customer_order"),
            inverseJoinColumns = @JoinColumn(name = "id_dish")
    )
    private List<Dish> dishList;



    @Column(name = "number_table")
    private int numberTable;

    @Column(name = "order_date")
    private String date;

    @Column(name = "state")
    private boolean state = true;

    public boolean isState() {
        return state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idEmployee=" + idEmployee +
                ", numberTable=" + numberTable +
               // ", date='" + date + '\'' +
                ", state=" + state +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }
    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }
}
