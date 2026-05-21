package com.example.CropBackend.Controller;

import com.example.CropBackend.Service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class CropController {

    private final CropService cropService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllDetails(page, size));
    }

    @GetMapping("/state")
    public ResponseEntity<?> getAllStates() {
        return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllStates());
    }

    @GetMapping("/commodity")
    public ResponseEntity<?> getAllCommodity() {
        return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllCommodity());
    }

    @GetMapping("/district/{state}")
    public ResponseEntity<?> getAllDistrictByState(@PathVariable String state) {
        return ResponseEntity.status(HttpStatus.OK).body(cropService.getDistrictsByState(state));
    }

    @GetMapping("/{state}")
    public ResponseEntity<?> getPriceByState(
            @PathVariable String state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllByState(state, page, size));
    }

    @GetMapping("/{state}/{district}")
    public ResponseEntity<?> getAllByBoth(
            @PathVariable String state,
            @PathVariable String district,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllByBoth(state, district, page, size));
    }
}


