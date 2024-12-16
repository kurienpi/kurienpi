package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "goods_train")
public class GoodsTrain extends Train {

    @Column(name = "types_of_goods")
    private String typesOfGoods;

    // Default constructor
    public GoodsTrain() {}

    // Parameterized constructor
    public GoodsTrain(String trainName, int noOfCoach, String trainStartStation,
                      String trainEndStation, String trainWeeklyDaysSchedule,
                      double avgSpeed, double fareCharges, String typesOfGoods) {
        super(trainName, noOfCoach, trainStartStation, trainEndStation,
                "Goods", trainWeeklyDaysSchedule, avgSpeed, fareCharges);
        this.typesOfGoods = typesOfGoods;
    }

    // Getters and setters
    public String getTypesOfGoods() {
        return typesOfGoods;
    }

    public void setTypesOfGoods(String typesOfGoods) {
        this.typesOfGoods = typesOfGoods;
    }
}
