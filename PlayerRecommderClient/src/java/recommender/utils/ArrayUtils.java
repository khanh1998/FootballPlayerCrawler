/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.utils;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author KHANHBQSE63463
 */
public class ArrayUtils {
    public static int[] getUniqueIdList(float[][][] ids_distances, int recommendQuantity) {
        Set<Integer> ids = new TreeSet<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < recommendQuantity; j++) {
               ids.add((int)ids_distances[i][j][0]);
            }
        }
        int[] result = new int[ids.size()];
        int i = 0;
        for (Integer id: ids.toArray(new Integer[ids.size()])) {
            result[i] = id;
            i++;
        }
        return result;
    } 
}
