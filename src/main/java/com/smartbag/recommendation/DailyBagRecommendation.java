package com.smartbag.recommendation;

import com.smartbag.model.BagItem;
import com.smartbag.model.Subject;

import java.util.List;

public class DailyBagRecommendation {

    private Subject subject;
    private List<BagItem> requiredItems;

    public DailyBagRecommendation(Subject subject, List<BagItem> requiredItems) {
        this.subject = subject;
        this.requiredItems = requiredItems;
    }

    public Subject getSubject() {
        return subject;
    }

    public List<BagItem> getRequiredItems() {
        return requiredItems;
    }
}