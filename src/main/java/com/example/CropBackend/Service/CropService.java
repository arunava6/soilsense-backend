package com.example.CropBackend.Service;

import com.example.CropBackend.DTO.CropResponse;
import com.example.CropBackend.DTO.SignUpRequest;
import com.example.CropBackend.Entity.Crop;
import com.example.CropBackend.Repository.CropRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepo cropRepo;

    public Page<CropResponse> getAllDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Crop> crops = cropRepo.findAll(pageable);

        return crops.map(item ->
                convertToCropResponse(item)
        );
    }

    private CropResponse convertToCropResponse(Crop item) {
        return CropResponse.builder()
                .state(item.getState())
                .district(item.getDistrict())
                .market(item.getMarket())
                .commodity(item.getCommodity())
                .variety(item.getVariety())
                .grade(item.getGrade())
                .min_price(item.getMin_price())
                .max_price(item.getMax_price())
                .modal_price(item.getModal_price())
                .price_date(item.getPrice_date())
                .build();
    }


    public Page<CropResponse> getAllByState(String state, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Crop> crops = cropRepo.findByState(state, pageable);
        return crops.map(item ->
                convertToCropResponse(item)
        );
    }

    public Page<CropResponse> getAllByBoth(String state, String district, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Crop> crops = cropRepo.findByStateAndDistrict(state, district, pageable);
        return crops.map(item ->
                convertToCropResponse(item)
        );
    }

    public List<String> getAllStates() {
        return cropRepo.findDistinctStates();
    }

    public List<String> getDistrictsByState(String state) {
        return cropRepo.findDistrictsByState(state);
    }

    public List<String> getAllCommodity() {
        return cropRepo.findByDistinctCommodity();
    }
}
