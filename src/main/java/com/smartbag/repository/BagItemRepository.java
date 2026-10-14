package com.smartbag.repository;

import com.smartbag.model.BagItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagItemRepository extends JpaRepository<BagItem, Long> {

    // Find all bag items linked to a particular subject
    List<BagItem> findBySubject_Id(Long subjectId);

}
