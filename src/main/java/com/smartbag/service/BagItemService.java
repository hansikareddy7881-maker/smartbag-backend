
package com.smartbag.service;

import com.smartbag.model.BagItem;
import com.smartbag.model.Subject;
import com.smartbag.repository.BagItemRepository;
import com.smartbag.repository.SubjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BagItemService {

    private final BagItemRepository bagItemRepository;
    private final SubjectRepository subjectRepository;

    public BagItemService(
            BagItemRepository bagItemRepository,
            SubjectRepository subjectRepository) {
        this.bagItemRepository = bagItemRepository;
        this.subjectRepository = subjectRepository;
    }

    public BagItem createBagItem(BagItem bagItem) {

        // Assign Digital Electronics (ID 1) when no subject is selected
        if (bagItem.getSubject() == null
                || bagItem.getSubject().getId() == null) {

            Subject subject = subjectRepository.findById(1L)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Subject with ID 1 not found"));

            bagItem.setSubject(subject);
        }

        return bagItemRepository.save(bagItem);
    }

    public List<BagItem> getAllBagItems() {
        return bagItemRepository.findAll();
    }

    public Optional<BagItem> getBagItemById(Long id) {
        return bagItemRepository.findById(id);
    }

    public Optional<BagItem> updateBagItem(
            Long id, BagItem updatedBagItem) {

        return bagItemRepository.findById(id).map(existingItem -> {
            existingItem.setName(updatedBagItem.getName());
            existingItem.setCategory(updatedBagItem.getCategory());
            existingItem.setWeight(updatedBagItem.getWeight());
            existingItem.setDescription(updatedBagItem.getDescription());

            if (updatedBagItem.getSubject() != null
                    && updatedBagItem.getSubject().getId() != null) {

                Subject subject = subjectRepository
                        .findById(updatedBagItem.getSubject().getId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Subject not found"));

                existingItem.setSubject(subject);
            }

            return bagItemRepository.save(existingItem);
        });
    }

    public void deleteBagItem(Long id) {
        bagItemRepository.deleteById(id);
    }
}