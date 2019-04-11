package SpringMySQL.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ROBOTS")

public class Robot {


        @Id
        @Column(name  ="ROBOT_ID")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column
        private String model;

        @Temporal(TemporalType.DATE)
        private Date date;

        @Column
        private Double price;

        public Robot() {
        }

        public Robot(String model, Date date, Double price) {
            this.model = model;
            this.date = date;
            this.price = price;
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public Double getPrice() {
            return price;
        }
        public void setPrice(Double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "id=" + id +
                    ", model='" + model + '\'' +
                    ", date=" + date +
                    ", price=" + price +
                    '}';
        }
    }


