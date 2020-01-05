package com.gzy.bitwise;

public class Permission {
    private final static int PERMISSION_SELECT = 1 << 0; //当前用户拥有查询权限0001
    private final static int PERMISSION_INSERT = 1 << 1; //当前用户拥有增加权限0010
    private final static int PERMISSION_UPDATE = 1 << 2; //当前用户拥有修改权限0100
    private final static int PERMISSION_DELETE = 1 << 3; //当前用户拥有删除权限1000

    private int flag; //当前用户拥有的权限

    //设置拥有的权限
    public void setPermisson(int per){
        flag = per;
    }
    //新增权限
    public void enablePermission(int per){
        flag = flag | per;
    }
    //取消权限
    public void disablePermission(int per){
        flag = flag & ~per;
    }
    //判断是否拥有权限
    public boolean isAllow(int per){
        return (flag & per) == per;
    }
    //判断没有的权限
    public boolean isNotAllow(int per){
        return (flag & per) == 0;
    }

    public static void main(String[] args) {
        int flag = 0;
        Permission permission = new Permission();
        permission.setPermisson(flag);
        permission.enablePermission(PERMISSION_INSERT);
        permission.enablePermission(PERMISSION_UPDATE);
        System.out.println("insert="+permission.isAllow(PERMISSION_INSERT));
        System.out.println("update="+permission.isAllow(PERMISSION_UPDATE));
        System.out.println("select="+permission.isAllow(PERMISSION_SELECT));
        System.out.println("delete="+permission.isAllow(PERMISSION_DELETE));
    }
}
