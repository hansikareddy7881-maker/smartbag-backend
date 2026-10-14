
package com.smartbag.recommendation;

import com.smartbag.model.BagItem;
import com.smartbag.model.Subject;
import com.smartbag.repository.BagItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class DailyBagService {

    private final BagItemRepository bagItemRepository;

    public DailyBagService(BagItemRepository bagItemRepository) {
        this.bagItemRepository = bagItemRepository;
    }

    public DailyBagRecommendation getRecommendation(Subject subject) {

        if (subject == null || subject.getId() == null) {
            throw new IllegalArgumentException(
                "A valid subject is required."
            );
        }

        List<BagItem> items =
            bagItemRepository.findBySubject_Id(subject.getId());

        // Remove repeated items using their names, ignoring case
        Map<String, BagItem> uniqueItems = new LinkedHashMap<>();

        for (BagItem item : items) {
            if (item != null && item.getName() != null) {
                String key = item.getName().trim().toLowerCase();

                if (!key.isEmpty()) {
                    uniqueItems.putIfAbsent(key, item);
                }
            }
        }

        return new DailyBagRecommendation(
            subject,
            List.copyOf(uniqueItems.values())
        );
    }
}
