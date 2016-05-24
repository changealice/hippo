package com.change.domain;

/**
 * User: change.long
 * Date: 16/5/23
 * Time: 上午12:45
 * 服务标签
 * 例如
 */
public class AccountOptions {
    /**
     * 人民币账户
     */
    private static final long RMB_ACCOUNT = (long) Math.pow(2, 0);
    /**
     * 美元账户
     */
    private static final long DOLLAR_ACCOUNT = (long) Math.pow(2, 1);

    /**
     * 积分账户
     */
    private static final long INTEGRATION_ACCOUNT = (long) Math.pow(2, 2);

    /**
     * 二进制中的属性名称,可以直接对应数据库中的字段
     */
    private Long options;

    public static void main(String[] args) {
        AccountOptions accountOptions = new AccountOptions();
        accountOptions.appendOptions(RMB_ACCOUNT);
        accountOptions.appendOptions(DOLLAR_ACCOUNT);
        accountOptions.appendOptions(INTEGRATION_ACCOUNT);

        System.out.println("option value:" + accountOptions.options);
        accountOptions.removeOptions(RMB_ACCOUNT);
        accountOptions.removeOptions(INTEGRATION_ACCOUNT);
        System.out.println("移除第 rmb 位和第 积分,options 的值:" + accountOptions.options);

        System.out.println("是否包含美元账户:" + accountOptions.containOptions(AccountOptions.DOLLAR_ACCOUNT));
        System.out.println("是否包含人民币账户:" + accountOptions.containOptions(AccountOptions.RMB_ACCOUNT));

    }

    public void appendOptions(long targetProperty) {
        if (null == options) {
            options = 0L;
        }
        this.options |= targetProperty;
    }

    /**
     * 在 options 中移除特定的位数,判断是否包含
     * @param targetProperty
     */
    public void removeOptions(long targetProperty) {
        if (null == options) {
            options = 0L;
        }
        if (this.containOptions(targetProperty)) {
            this.options &= (this.options - targetProperty);
        }
    }

    public boolean containOptions(long targetProperty) {
        if (null == options) {
            return false;
        }
        return ((this.options & targetProperty) == targetProperty);
    }

}
