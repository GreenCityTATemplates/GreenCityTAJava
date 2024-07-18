package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EcoNewsOrdersEntityFields {
    ID(0),
    COMMENT(1),
    DELIVER_FROM(2),
    DELIVER_TO(3),
    NOTE(4),
    ORDER_DATE(5),
    ORDER_STATUS(6),
    POINTS_TO_USE(7),
    RECEIVING_STATION(8),
    UBS_USER_ID(9),
    USERS_ID(10),
    ORDER_PAYMENT_STATUS(11),
    CANCELLATION_REASON(12),
    CANCELLATION_COMMENT(13),
    REASON_NOT_TAKING_BAG_DESCRIPTION(14),
    IMAGE_REASON_NOT_TAKING_BAGS(15),
    DATE_OF_EXPORT(16),
    EMPLOYEE_ID(17),
    BLOCKED(18),
    ADMIN_COMMENT(19),
    COUNTER_ORDER_PAYMENT_ID(20),
    COURIER_LOCATIONS_ID(21),
    SUM_TOTAL_AMOUNT_WITHOUT_DISCOUNTS(22);


    private final int number;

    EcoNewsOrdersEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class EcoNewsOrdersEntity {
    public static final String SELECT_BY_FIELD = "SELECT * FROM orders WHERE %s = '%s';";

    private Long id;
    private String comment;
    private String deliverFrom;
    private String deliverTo;
    private String note;
    private String orderDate;
    private String orderStatus;
    private Integer pointsToUse;
    private String receivingStation;
    private Long ubsUserId;
    private Long usersId;
    private String orderPaymentStatus;
    private String cancellationReason;
    private String cancellationComment;
    private String reasonNotTakingBagDescription;
    private String imageReasonNotTakingBags;
    private String dateOfExport;
    private Long employeeId;
    private Boolean blocked;
    private String adminComment;
    private Long counterOrderPaymentId;
    private Long courierLocationsId;
    private Long sumTotalAmountWithoutDiscount;

    public EcoNewsOrdersEntity(Long id, String comment, String deliverFrom, String deliverTo, String note, String orderDate, String orderStatus, Integer pointsToUse, String receivingStation, Long ubsUserId, Long usersId, String orderPaymentStatus, String cancellationReason, String cancellationComment, String reasonNotTakingBagDescription, String imageReasonNotTakingBags, String dateOfExport, Long employeeId, Boolean blocked, String adminComment, Long counterOrderPaymentId, Long courierLocationsId, Long sumTotalAmountWithoutDiscount) {
        this.id = id;
        this.comment = comment;
        this.deliverFrom = deliverFrom;
        this.deliverTo = deliverTo;
        this.note = note;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.pointsToUse = pointsToUse;
        this.receivingStation = receivingStation;
        this.ubsUserId = ubsUserId;
        this.usersId = usersId;
        this.orderPaymentStatus = orderPaymentStatus;
        this.cancellationReason = cancellationReason;
        this.cancellationComment = cancellationComment;
        this.reasonNotTakingBagDescription = reasonNotTakingBagDescription;
        this.imageReasonNotTakingBags = imageReasonNotTakingBags;
        this.dateOfExport = dateOfExport;
        this.employeeId = employeeId;
        this.blocked = blocked;
        this.adminComment = adminComment;
        this.counterOrderPaymentId = counterOrderPaymentId;
        this.courierLocationsId = courierLocationsId;
        this.sumTotalAmountWithoutDiscount = sumTotalAmountWithoutDiscount;
    }

    public EcoNewsOrdersEntity() {
        this.id = null;
        this.comment = null;
        this.deliverFrom = null;
        this.deliverTo = null;
        this.note = null;
        this.orderDate = null;
        this.orderStatus = null;
        this.pointsToUse = 0;
        this.receivingStation = null;
        this.ubsUserId = null;
        this.usersId = null;
        this.orderPaymentStatus = null;
        this.cancellationReason = null;
        this.cancellationComment = null;
        this.reasonNotTakingBagDescription = null;
        this.imageReasonNotTakingBags = null;
        this.dateOfExport = null;
        this.employeeId = null;
        this.blocked = false;
        this.adminComment = null;
        this.counterOrderPaymentId = null;
        this.courierLocationsId = null;
        this.sumTotalAmountWithoutDiscount = null;
    }

    public static EcoNewsOrdersEntity getEcoNewsOrdersEntity(List<String> row) {
        return new EcoNewsOrdersEntity()
                .setId(Long.parseLong(row.get(EcoNewsOrdersEntityFields.ID.getNumber())))
                .setComment(row.get(EcoNewsOrdersEntityFields.COMMENT.getNumber()))
                .setDeliverFrom(row.get(EcoNewsOrdersEntityFields.DELIVER_FROM.getNumber()))
                .setDeliverTo(row.get(EcoNewsOrdersEntityFields.DELIVER_TO.getNumber()))
                .setNote(row.get(EcoNewsOrdersEntityFields.NOTE.getNumber()))
                .setOrderDate(row.get(EcoNewsOrdersEntityFields.ORDER_DATE.getNumber()))
                .setOrderStatus(row.get(EcoNewsOrdersEntityFields.ORDER_STATUS.getNumber()))
                .setPointsToUse(Integer.parseInt(row.get(EcoNewsOrdersEntityFields.POINTS_TO_USE.getNumber())))
                .setReceivingStation(row.get(EcoNewsOrdersEntityFields.RECEIVING_STATION.getNumber()))
                .setUbsUserId(Long.parseLong(row.get(EcoNewsOrdersEntityFields.UBS_USER_ID.getNumber())))
                .setUsersId(Long.parseLong(row.get(EcoNewsOrdersEntityFields.USERS_ID.getNumber())))
                .setOrderPaymentStatus(row.get(EcoNewsOrdersEntityFields.ORDER_PAYMENT_STATUS.getNumber()))
                .setCancellationReason(row.get(EcoNewsOrdersEntityFields.CANCELLATION_REASON.getNumber()))
                .setCancellationComment(row.get(EcoNewsOrdersEntityFields.CANCELLATION_COMMENT.getNumber()))
                .setReasonNotTakingBagDescription(row.get(EcoNewsOrdersEntityFields.REASON_NOT_TAKING_BAG_DESCRIPTION.getNumber()))
                .setImageReasonNotTakingBags(row.get(EcoNewsOrdersEntityFields.IMAGE_REASON_NOT_TAKING_BAGS.getNumber()))
                .setDateOfExport(row.get(EcoNewsOrdersEntityFields.DATE_OF_EXPORT.getNumber()))
                .setEmployeeId(Long.parseLong(row.get(EcoNewsOrdersEntityFields.EMPLOYEE_ID.getNumber())))
                .setBlocked(Boolean.parseBoolean(row.get(EcoNewsOrdersEntityFields.BLOCKED.getNumber())))
                .setAdminComment(row.get(EcoNewsOrdersEntityFields.ADMIN_COMMENT.getNumber()))
                .setCounterOrderPaymentId(Long.parseLong(row.get(EcoNewsOrdersEntityFields.COUNTER_ORDER_PAYMENT_ID.getNumber())))
                .setCourierLocationsId(Long.parseLong(row.get(EcoNewsOrdersEntityFields.COURIER_LOCATIONS_ID.getNumber())))
                .setSumTotalAmountWithoutDiscount(Long.parseLong(row.get(EcoNewsOrdersEntityFields.SUM_TOTAL_AMOUNT_WITHOUT_DISCOUNTS.getNumber())));
    }

    public static List<EcoNewsOrdersEntity> getListEcoNewsOrdersEntity(List<List<String>> rows) {
        List<EcoNewsOrdersEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEcoNewsOrdersEntity(currentRow));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public EcoNewsOrdersEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public EcoNewsOrdersEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getDeliverFrom() {
        return deliverFrom;
    }

    public EcoNewsOrdersEntity setDeliverFrom(String deliverFrom) {
        this.deliverFrom = deliverFrom;
        return this;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public EcoNewsOrdersEntity setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
        return this;
    }

    public String getNote() {
        return note;
    }

    public EcoNewsOrdersEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public EcoNewsOrdersEntity setOrderDate(String orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public EcoNewsOrdersEntity setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public int getPointsToUse() {
        return pointsToUse;
    }

    public EcoNewsOrdersEntity setPointsToUse(int pointsToUse) {
        this.pointsToUse = pointsToUse;
        return this;
    }

    public String getReceivingStation() {
        return receivingStation;
    }

    public EcoNewsOrdersEntity setReceivingStation(String receivingStation) {
        this.receivingStation = receivingStation;
        return this;
    }

    public long getUbsUserId() {
        return ubsUserId;
    }

    public EcoNewsOrdersEntity setUbsUserId(long ubsUserId) {
        this.ubsUserId = ubsUserId;
        return this;
    }

    public long getUsersId() {
        return usersId;
    }

    public EcoNewsOrdersEntity setUsersId(long usersId) {
        this.usersId = usersId;
        return this;
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public EcoNewsOrdersEntity setOrderPaymentStatus(String orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
        return this;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public EcoNewsOrdersEntity setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
        return this;
    }

    public String getCancellationComment() {
        return cancellationComment;
    }

    public EcoNewsOrdersEntity setCancellationComment(String cancellationComment) {
        this.cancellationComment = cancellationComment;
        return this;
    }

    public String getReasonNotTakingBagDescription() {
        return reasonNotTakingBagDescription;
    }

    public EcoNewsOrdersEntity setReasonNotTakingBagDescription(String reasonNotTakingBagDescription) {
        this.reasonNotTakingBagDescription = reasonNotTakingBagDescription;
        return this;
    }

    public String getImageReasonNotTakingBags() {
        return imageReasonNotTakingBags;
    }

    public EcoNewsOrdersEntity setImageReasonNotTakingBags(String imageReasonNotTakingBags) {
        this.imageReasonNotTakingBags = imageReasonNotTakingBags;
        return this;
    }

    public String getDateOfExport() {
        return dateOfExport;
    }

    public EcoNewsOrdersEntity setDateOfExport(String dateOfExport) {
        this.dateOfExport = dateOfExport;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public EcoNewsOrdersEntity setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public EcoNewsOrdersEntity setBlocked(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public EcoNewsOrdersEntity setAdminComment(String adminComment) {
        this.adminComment = adminComment;
        return this;
    }

    public Long getCounterOrderPaymentId() {
        return counterOrderPaymentId;
    }

    public EcoNewsOrdersEntity setCounterOrderPaymentId(Long counterOrderPaymentId) {
        this.counterOrderPaymentId = counterOrderPaymentId;
        return this;
    }

    public long getCourierLocationsId() {
        return courierLocationsId;
    }

    public EcoNewsOrdersEntity setCourierLocationsId(long courierLocationsId) {
        this.courierLocationsId = courierLocationsId;
        return this;
    }

    public Long getSumTotalAmountWithoutDiscount() {
        return sumTotalAmountWithoutDiscount;
    }

    public EcoNewsOrdersEntity setSumTotalAmountWithoutDiscount(Long sumTotalAmountWithoutDiscount) {
        this.sumTotalAmountWithoutDiscount = sumTotalAmountWithoutDiscount;
        return this;
    }

    @Override
    public String toString() {
        return "EcoNewsOrdersEntity{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", deliverFrom='" + deliverFrom + '\'' +
                ", deliverTo='" + deliverTo + '\'' +
                ", note='" + note + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", pointsToUse=" + pointsToUse +
                ", receivingStation='" + receivingStation + '\'' +
                ", ubsUserId=" + ubsUserId +
                ", usersId=" + usersId +
                ", orderPaymentStatus='" + orderPaymentStatus + '\'' +
                ", cancellationReason='" + cancellationReason + '\'' +
                ", cancellationComment='" + cancellationComment + '\'' +
                ", reasonNotTakingBagDescription='" + reasonNotTakingBagDescription + '\'' +
                ", imageReasonNotTakingBags='" + imageReasonNotTakingBags + '\'' +
                ", dateOfExport='" + dateOfExport + '\'' +
                ", employeeId=" + employeeId +
                ", blocked=" + blocked +
                ", adminComment='" + adminComment + '\'' +
                ", counterOrderPaymentId=" + counterOrderPaymentId +
                ", courierLocationsId=" + courierLocationsId +
                ", sumTotalAmountWithoutDiscount=" + sumTotalAmountWithoutDiscount +
                '}';
    }
}
