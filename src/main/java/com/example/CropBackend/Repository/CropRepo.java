package com.example.CropBackend.Repository;

import com.example.CropBackend.Entity.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepo extends JpaRepository<Crop, Long> {
    Page<Crop> findByState(String state, Pageable pageable);

    Page<Crop> findByStateAndDistrict(String state, String district, Pageable pageable);

    @Query("SELECT DISTINCT c.state FROM Crop c ORDER BY c.state")
    List<String> findDistinctStates();

    @Query("""
            SELECT DISTINCT c.district FROM Crop c
            WHERE c.state = :state
            ORDER BY c.district
            """)
    List<String> findDistrictsByState(String state);

    @Query("SELECT DISTINCT c.commodity FROM Crop c ORDER BY c.commodity")
    List<String> findByDistinctCommodity();

}
