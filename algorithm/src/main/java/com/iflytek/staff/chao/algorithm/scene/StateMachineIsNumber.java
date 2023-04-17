package com.iflytek.staff.chao.algorithm.scene;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: 剑指 Offer 20. 表示数值的字符串
 */
public class StateMachineIsNumber {

    /**
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        Map<CharType, State> initailMap = new HashMap<CharType, State>() {{
            put(CharType.SPACE, State.INITIAL);
            put(CharType.NUMBER, State.INTEGER);
            put(CharType.POINT, State.POINT_WITHOUT_INT);
            put(CharType.SIGN, State.INT_SIGN);
        }};
        transfer.put(State.INITIAL, initailMap);

        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.INTEGER);
            put(CharType.POINT, State.POINT_WITHOUT_INT);
        }};
        transfer.put(State.INT_SIGN, intSignMap);

        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.INTEGER);
            put(CharType.EXP, State.EXP);
            put(CharType.POINT, State.POINT);
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.INTEGER, integerMap);

        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.FRACTION);
            put(CharType.EXP, State.EXP);
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.POINT, pointMap);

        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.FRACTION);
        }};
        transfer.put(State.POINT_WITHOUT_INT, pointWithoutIntMap);

        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.FRACTION);
            put(CharType.EXP, State.EXP);
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.FRACTION, fractionMap);

        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.EXP_NUMBER);
            put(CharType.SIGN, State.EXP_SIGN);
        }};
        transfer.put(State.EXP, expMap);

        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.EXP_NUMBER);
        }};
        transfer.put(State.EXP_SIGN, expSignMap);

        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.EXP_NUMBER);
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.EXP_NUMBER, expNumberMap);

        Map<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.END, endMap);

        State state = State.INITIAL;
        for (int i = 0; i < s.length(); i++) {
            CharType type = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(type)) {
                return false;
            } else {
                state = transfer.get(state).get(type);
            }
        }
        return state == State.INTEGER || state == State.POINT || state == State.FRACTION
                || state == State.EXP_NUMBER || state == State.END;
    }

    private CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.EXP;
        } else if (ch == '.') {
            return CharType.POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.SIGN;
        } else if (ch == ' ') {
            return CharType.SPACE;
        } else {
            return CharType.ILLEGAL;
        }
    }

    enum State {
        INITIAL,
        INT_SIGN,
        INTEGER,
        POINT,
        POINT_WITHOUT_INT,
        FRACTION,
        EXP,
        EXP_SIGN,
        EXP_NUMBER,
        END
    }

    enum CharType {
        NUMBER,
        EXP,
        POINT,
        SIGN,
        SPACE,
        ILLEGAL
    }
}
