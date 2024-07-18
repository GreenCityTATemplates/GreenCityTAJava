package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EcoNewsUserActionsEntityFields {
    ID(0),
    USER_ID(1),
    ACHIEVEMENT_CATEGORY_ID(2),
    COUNT(3);

    private final int number;

    EcoNewsUserActionsEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class EcoNewsUserActionsEntity {
    public static final String SELECT_ALL = "SELECT * FROM user_actions;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM user_actions WHERE %s = '%s';";
    public static final String DELETE_BY_ID = "DELETE FROM user_actions WHERE id = %s;";

    private long id;
    private long userId;
    private long achievementCategoryId;
    private int count;

    public EcoNewsUserActionsEntity(long id, long userId, long achievementCategoryId, int count) {
        this.id = id;
        this.userId = userId;
        this.achievementCategoryId = achievementCategoryId;
        this.count = count;
    }

    public EcoNewsUserActionsEntity() {
        this.id = 0;
        this.userId = 0;
        this.achievementCategoryId = 0;
        this.count = 0;
    }

    public static EcoNewsUserActionsEntity getEcoNewsUserActionsEntity(List<String> row) {
        return new EcoNewsUserActionsEntity()
                .setId(Long.parseLong(row.get(EcoNewsUserActionsEntityFields.ID.getNumber())))
                .setUserId(Long.parseLong(row.get(EcoNewsUserActionsEntityFields.USER_ID.getNumber())))
                .setAchievementCategoryId(Long.parseLong(row.get(EcoNewsUserActionsEntityFields.ACHIEVEMENT_CATEGORY_ID.getNumber())))
                .setCount(Integer.parseInt(row.get(EcoNewsUserActionsEntityFields.COUNT.getNumber())));
    }

    public static List<EcoNewsUserActionsEntity> getListEcoNewsUserActionsEntity(List<List<String>> rows) {
        List<EcoNewsUserActionsEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEcoNewsUserActionsEntity(currentRow));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public EcoNewsUserActionsEntity setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public EcoNewsUserActionsEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getAchievementCategoryId() {
        return achievementCategoryId;
    }

    public EcoNewsUserActionsEntity setAchievementCategoryId(long achievementCategoryId) {
        this.achievementCategoryId = achievementCategoryId;
        return this;
    }

    public int getCount() {
        return count;
    }

    public EcoNewsUserActionsEntity setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "EcoNewsUserActionsEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", achievementCategoryId=" + achievementCategoryId +
                ", count=" + count +
                '}';
    }
}
