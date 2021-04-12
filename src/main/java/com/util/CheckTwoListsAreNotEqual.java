package com.util;

import java.util.ArrayList;
import java.util.Collections;

public class CheckTwoListsAreNotEqual {

    public static boolean checkTwoListsAreNotEqual(ArrayList<String> listOne, ArrayList<String> listTwo) {
        Collections.sort(listOne);
        Collections.sort(listTwo);
        return !listOne.equals(listTwo);
    }
}
