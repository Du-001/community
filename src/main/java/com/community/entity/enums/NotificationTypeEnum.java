package com.community.entity.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了提问"),
    REPLY_COMMENT(2,"回复了评论"),
    ;
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getName(int type) {
        for (NotificationTypeEnum  notificationTypeEnum:NotificationTypeEnum.values()) {
            if(notificationTypeEnum.type==type){
                return notificationTypeEnum.name;
            }
        }
        return null;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
