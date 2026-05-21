package com.example.CropBackend.Entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "market_price")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long slNo;

    @Column(name = "State")
    @CsvBindByName(column = "STATE")
    private String state;

    @Column(name = "District_Name")
    @CsvBindByName(column = "District_Name")
    private String district;

    @Column(name = "Market_Name")
    @CsvBindByName(column = "Market_Name")
    private String market;

    @Column(name = "Commodity")
    @CsvBindByName(column = "Commodity")
    private String commodity;

    @Column(name = "Variety")
    @CsvBindByName(column = "Variety")
    private String variety;

    @Column(name = "Grade")
    @CsvBindByName(column = "Grade")
    private String grade;

    @Column(name = "Min_Price")
    @CsvBindByName(column = "Min_Price")
    private double min_price;

    @Column(name = "Max_Price")
    @CsvBindByName(column = "Max_Price")
    private double max_price;

    @Column(name = "Modal_Price")
    @CsvBindByName(column = "Modal_Price")
    private double modal_price;

    @Column(name = "Price_Date")
    @CsvBindByName(column = "Price_Date")
    private String price_date;

}
