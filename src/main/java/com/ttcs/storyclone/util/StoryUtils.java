package com.ttcs.storyclone.util;

import com.ttcs.storyclone.model.StoryStatus;

public class StoryUtils {
    public static String getStatusBadgeClass(StoryStatus status) {
        return switch (status) {
            case completed -> "success";
            case ongoing -> "primary";
            case hiatus -> "warning";
            case cancelled -> "danger";
        };
    }

    public static String getStatusVietnamese(StoryStatus status) {
        return switch (status) {
            case completed -> "Hoàn thành";
            case ongoing -> "Đang tiến hành";
            case hiatus -> "Tạm ngưng";
            case cancelled -> "Đã hủy";
        };
    }
}
