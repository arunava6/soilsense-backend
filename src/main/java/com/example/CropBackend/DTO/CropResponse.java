package com.example.CropBackend.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CropResponse {
    private String state;
    private String district;
    private String market;
    private String commodity;
    private String variety;
    private String grade;
    private double min_price;
    private double max_price;
    private double modal_price;
    private String price_date;

}
