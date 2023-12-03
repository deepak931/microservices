package com.bookmymovie.api.bookingapp.constants;

public enum SeatType {

    RECLINER(1), PRIME(2), CLASSIC(3);
    private int type;

    SeatType(int type) {
        this.type = type;
    }

    public int type() {
        return type;
    }

    public static SeatType type(int type) {
        for (SeatType s : SeatType.values()) {
            if (s.type == type)
                return s;
        }
        return null;
    }
}
