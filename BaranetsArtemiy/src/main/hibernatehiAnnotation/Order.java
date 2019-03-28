package hibernatehiAnnotation;

import hibernatehiAnnotation.types.ProductType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {

    /* Для любой сущности Hibernate - обязательно наличие
     *  уникального ID
     *
     *  @Id - аннотация которой мы отмечаем ключевое поле
     *        в классе
     *  @Column - позволяет нам отметить параметр класса
     *            как столбец в БД
     *  @GeneratedValue(strategy = GenerationType.AUTO)
     *            позволяет нам сделать генерацию ID
     *            1 ... n (INTEGER) - +1*/
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* @Enumerated - позволяет нам сохранить класс
                     перечесление в БД, в нем есть
                     2 параметра:
                     EnumType.STRING  - видим в ввиде текста
                     EnumType.ORDINAL - видим ID*/
    @Column(name = "PRODUCT")
    @Enumerated(EnumType.STRING)
    private ProductType product;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "PRICE")
    private Double price;

    /* @Temporal(TemporalType.DATE) - помогает решить конфликт
     *                       типов при сохранение Дат в БД*/
    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    public Order(ProductType product, Integer amount, Double price, Date orderDate) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.orderDate = orderDate;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ProductType getProduct() {
        return product;
    }
    public void setProduct(ProductType product) {
        this.product = product;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", price=" + price +
                ", orderDate=" + orderDate +
                '}';
    }
}
