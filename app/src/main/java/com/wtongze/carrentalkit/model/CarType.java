package com.wtongze.carrentalkit.model;

import androidx.annotation.DrawableRes;

import com.wtongze.carrentalkit.R;

/**
 * Based on Hertz's car type.
 */
public enum CarType {
    MANAGER_SPECIAL("A6", "EXAR", "Manager Special", R.drawable.exar),
    ECONOMY("A", "ECAR", "Chevrolet Spark or similar", R.drawable.ecar),
    COMPACT("B", "CCAR", "Ford Focus or similar", R.drawable.ccar),
    INTERMEDIATE("C", "ICAR", "Mazda 3 or similar", R.drawable.icar),
    STANDARD("D", "SCAR", "VW Jetta or similar", R.drawable.scar),
    FULL_SIZE("F", "FCAR", "Chevrolet Malibu or similar", R.drawable.fcar),
    PREMIUM("G", "PCAR", "Nissan Maxima or similar", R.drawable.pcar),
    SMALL_SUV("Q4", "IFAR", "Nissan Rogue or similar", R.drawable.ifar),
    MEDIUM_SUV("L", "SFAR", "Chevrolet Equinox or similar", R.drawable.sfar),
    MEDIUM_CONVERTIBLE("U", "STAR", "Ford Mustang convertible or similar", R.drawable.star),
    MEDIUM_ELITE("P4", "RCAR", "Infiniti Q50 or similar", R.drawable.rcar),
    MINIVAN("R", "MVAR", "Chrysler pacifica or similar", R.drawable.mvar),
    LARGE_LUXURY("I", "LCAR", "Chrysler 300 or similar", R.drawable.lcar),
    SMALL_ELITE("S6", "DCAR", "Mercedes CLA250 / Audi A3 or similar", R.drawable.dcar),
    MEDIUM_LUXURY_SUV("T4", "RFAR", "Cadillac XT5 or similar", R.drawable.rfar),
    ELITE_SPORTS("V4", "RSAR", "Mustang GT / Camaro SS or similar", R.drawable.rsar),
    PASSENGER_VAN("M", "FVAR", "Ford Transit 12 passenger or similar", R.drawable.fvar),
    TESLA_MODEL_3_LONG_RANGE("E8", "JCAC", "Tesla Model 3 Long Range", R.drawable.jcac),
    TESLA_MODEL_3_STANDARD_RANGE("E7", "JCAE", "Tesla Model 3 Standard Range", R.drawable.jcae),
    LARGE_ELITE("K", "GCAR", "BMW 530 or similar", R.drawable.gcar),
    INTERMEDIATE_ELECTRIC_ELITE("C4", "JDAE", "Polestar 2 LR Dual Motor or similar", R.drawable.jdae),
    MERCEDES_C63_AMG("H", "DSAR", "Mercedes C63 AMG", R.drawable.dsar),
    MERCEDES_CLA45_AMG("H6", "GSAR", "Mercedes CLA45 AMG", R.drawable.gsar),
    SHELBY_GT_H_FASTBACK("U2", "XSAR", "Shelby GT-H Fastback", R.drawable.xsar),
    POLESTAR_1("E3", "XEAI", "Polestar 1", R.drawable.xeai),
    PREMIUM_SPORTS_CONVERTIBLE("U4", "RTAR", "Chevy Camaro SS Convertible or similar", R.drawable.rtar),
    SHELBY_GT_H_CONVERTIBLE("U3", "XTAR", "Shelby GT-H Convertible", R.drawable.xtar),
    COMPACT_SUV("B4", "CFAR", "Buick Encode or similar", R.drawable.cfar),
    SMALL_LUXURY_SUV("H4", "JFDR", "Infiniti QX50 or similar", R.drawable.jfdr),
    TESLA_MODEL_Y("E9", "RFAC", "Tesla Model Y", R.drawable.rfac),
    LARGE_SUV("T", "FFAR", "GMC Yukon or similar", R.drawable.ffar),
    EXTRA_CAPACITY_LARGE_SUV("T6", "PFAR", "Chevrolet Suburban or similar", R.drawable.pfar),
    LARGE_ELITE_SUV("P6", "LFAR", "Infiniti QX80 or similar", R.drawable.lfar),
    LUXURY_PASSENGER_SUV("Z4", "RGDR", "Infiniti QX60 or similar", R.drawable.rgdr),
    MEDIUM_PASSENGER_SUV("L4", "FRAR", "Nissan Pathfinder or similar", R.drawable.frar),
    SMALL_PICKUP_TRUCK("O6", "IPAR", "Nissan Frontier Crew Cab or similar", R.drawable.ipar),
    MEDIUM_PICKUP_TRUCK("S", "SPAR", "Dodge Ram 1500 or similar", R.drawable.spar);

    public final String group;
    public final String code;
    public final String description;

    @DrawableRes
    public final int res;

    CarType(String group, String code, String description, int res) {
        this.group = group;
        this.code = code;
        this.description = description;
        this.res = res;
    }

    public static CarType findByCode(String code) {
        String c = code.toUpperCase();
        for (CarType carType : CarType.values()) {
            if (carType.code.equals(c)) {
                return carType;
            }
        }
        return null;
    }

    public static CarType findByGroup(String group) {
        String g = group.toUpperCase();
        for (CarType carType : CarType.values()) {
            if (carType.group.equals(g)) {
                return carType;
            }
        }
        return null;
    }
}
