package com.snakelord.clickcounter;

enum CookieDamageStates {

    DAMAGE_STATE_10_PERCENTS(100),
    DAMAGE_STATE_20_PERCENTS(200),
    DAMAGE_STATE_30_PERCENTS (300),
    DAMAGE_STATE_40_PERCENTS(400),
    DAMAGE_STATE_50_PERCENTS (500),
    DAMAGE_STATE_60_PERCENTS (600),
    DAMAGE_STATE_70_PERCENTS (700),
    DAMAGE_STATE_80_PERCENTS (800),
    DAMAGE_STATE_90_PERCENTS(900),
    DAMAGE_STATE_100_PERCENTS (1000),
    DAMAGE_STATE_COOKIE_IS_GONE (1100);

    private int damageStateValue;

    CookieDamageStates(int damageStateValue) {
        this.damageStateValue = damageStateValue;
    }

    public int getValue()
    {
        return damageStateValue;
    }
}
