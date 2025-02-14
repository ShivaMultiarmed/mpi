package mikhail.shell.commis.voyager;

import java.util.ArrayList;
import java.util.List;

// Travelling Salesman Problem

public class TSPBruteForce {
    static int n;
    static int[][] dist;
    static int bestCost = Integer.MAX_VALUE;
    static List<Integer> bestRoute = new ArrayList<>();

    public static void main(String[] args) {
        n = 4;
        dist = new int[][]{
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 60},
                {20, 25, 60, 0}
        };

        List<Integer> cities = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            cities.add(i);
        }

        List<Integer> currentRoute = new ArrayList<>();
        currentRoute.add(0);

        permute(cities, currentRoute, 0);

        System.out.println("Минимальная стоимость маршрута: " + bestCost);
        System.out.println("Оптимальный маршрут: " + bestRoute);
    }
    private static void permute(List<Integer> cities, List<Integer> currentRoute, int currentCost) {
        if (cities.isEmpty()) {
            int totalCost = currentCost + dist[currentRoute.get(currentRoute.size() - 1)][0];
            if (totalCost < bestCost) {
                bestCost = totalCost;
                bestRoute = new ArrayList<>(currentRoute);
                bestRoute.add(0); // завершаем маршрут возвратом в начальный город
            }
        } else {
            for (int i = 0; i < cities.size(); i++) {
                int city = cities.get(i);
                int newCost = currentCost + dist[currentRoute.get(currentRoute.size() - 1)][city];
                if (newCost < bestCost) {
                    List<Integer> newRoute = new ArrayList<>(currentRoute);
                    newRoute.add(city);
                    List<Integer> remaining = new ArrayList<>(cities);
                    remaining.remove(i);
                    permute(remaining, newRoute, newCost);
                }
            }
        }
    }
}
