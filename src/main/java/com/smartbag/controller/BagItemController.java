
package com.smartbag.controller;

import com.smartbag.model.BagItem;
import com.smartbag.model.Subject;
import com.smartbag.repository.SubjectRepository;
import com.smartbag.service.BagItemService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bag-items")
public class BagItemController {

    private final BagItemService bagItemService;
    private final SubjectRepository subjectRepository;

    public BagItemController(
            BagItemService bagItemService,
            SubjectRepository subjectRepository) {
        this.bagItemService = bagItemService;
        this.subjectRepository = subjectRepository;
    }

    @PostMapping
    public ResponseEntity<BagItem> createBagItem(
            @Valid @RequestBody BagItem bagItem,
            @RequestParam(required = false) Long subjectId) {

        Long selectedSubjectId = subjectId;

        if (selectedSubjectId == null
                && bagItem.getSubject() != null) {
            selectedSubjectId = bagItem.getSubject().getId();
        }

        if (selectedSubjectId != null) {
            Subject subject = subjectRepository
                    .findById(selectedSubjectId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Subject not found"));

            bagItem.setSubject(subject);
        }

        return ResponseEntity.ok(
                bagItemService.createBagItem(bagItem));
    }

    @GetMapping
    public List<BagItem> getAllBagItems() {
        return bagItemService.getAllBagItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagItem> getBagItemById(
            @PathVariable Long id) {
        return bagItemService.getBagItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BagItem> updateBagItem(
            @PathVariable Long id,
            @Valid @RequestBody BagItem bagItem) {
        return bagItemService.updateBagItem(id, bagItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBagItem(
            @PathVariable Long id) {
        bagItemService.deleteBagItem(id);
        return ResponseEntity.noContent().build();
    }
}
