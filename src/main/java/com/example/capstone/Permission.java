package com.example.capstone;

public class Permission {
    private String permission_id;
    private String permission_name;

    Permission(String permission_id, String permission_name) {
        this.permission_id = permission_id;
        this.permission_name = permission_name;
    }

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }
}
