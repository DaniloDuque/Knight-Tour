import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnightTour {





    // List of possible moves for a knight
    private static final int[][] MOVES = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};






    // Check if a position is valid on the board
    private static boolean isValid(int[] pos, int f, int c, int[][] matrix) {
        return 0 <= pos[0] && pos[0] < f && 0 <= pos[1] && pos[1] < c && matrix[pos[0]][pos[1]] == 0;
    }






    // Sum two coordinate vectors
    private static int[] sumCoordinates(int[] v, int[] w) {
        return new int[]{v[0] + w[0], v[1] + w[1]};
    }





    // Convert 2D coordinates to a linear index
    private static int locateIndex(int[] i, int c) {
        return i[0] * c + i[1];
    }









    // This function marks the route in the matrix
    private static void mark(int[][] matrix, List<int[]> route, int n) {
        for (int[] i : route) {
            matrix[i[0]][i[1]] = n;
        }
    }







    // QuickSort function to order the children from a route, from most accessible to less accessible
    // This implements a heuristic, in which the algorithm prefers less accessible children
    private static List<List<int[]>> sortRoutes(List<List<int[]>> routes, int f, int c) {
        if (routes.isEmpty()) return new ArrayList<>();
        List<List<int[]>> highest = new ArrayList<>();
        List<List<int[]>> lowest = new ArrayList<>();
        int h = countChildren(routes.get(0), f, c);
        for (List<int[]> i : routes.subList(1, routes.size())) {
            if (countChildren(i, f, c) >= h) {
                highest.add(i);
            } else {
                lowest.add(i);
            }
        }
        List<List<int[]>> result = new ArrayList<>();
        result.addAll(sortRoutes(highest, f, c));
        result.add(routes.get(0));
        result.addAll(sortRoutes(lowest, f, c));
        return result;
    }








    // Returns the number of children a given route has; fewer children mean less accessible
    private static int countChildren(List<int[]> route, int f, int c) {

        int k = 0;
        for (int[] move : MOVES) {

            int[] s = sumCoordinates(route.get(route.size() - 1), move);

            if (isValid(s, f, c, M)) k++;
            
        }return k;

    }








    // Returns all the possible unvisited children a route has
    private static List<List<int[]>> getChildren(List<int[]> route, int f, int c) {

        mark(M, route, 1);
        List<List<int[]>> k = new ArrayList<>();
        for (int[] move : MOVES) {
            int[] s = sumCoordinates(route.get(route.size() - 1), move);
            if (isValid(s, f, c, M)) {
                List<int[]> child = new ArrayList<>(route);
                child.add(s);
                k.add(child);
            }
        }

        List<List<int[]>> sortedRoutes = sortRoutes(k, f, c);

        if (sortedRoutes.isEmpty()) mark(M, route, 0);
        
        return sortedRoutes;
    }





    // Returns a route that traverses all the positions on the board from a given start (i, j)
    private static List<int[]> knightTour(int f, int c, int i, int j) {

        Stack<List<int[]>> stack = new Stack<>();
        stack.push(Arrays.asList(new int[]{i, j}));

        while (!stack.isEmpty()) {
            List<int[]> route = stack.pop();
            if (route.size() == f * c) return route;
            
            for (List<int[]> child : getChildren(route, f, c)) stack.push(child);
            
        }return new ArrayList<>(); // Should not happen if a solution exists

    }



    private static void printKnightTour(List<int[]> route, int c) {

        for (int idx = 0; idx < route.size(); idx++) {
            if (idx % c == 0) System.out.println();
            int[] i = route.get(idx);
            int k = locateIndex(i, c) + 1;
            System.out.printf("%04d ", k);
        }
        System.out.println();
    }


    private static int[][] M;



    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = reader.readLine().split(" ");
        int f = Integer.parseInt(inputs[0]);
        int c = Integer.parseInt(inputs[1]);
        int i = Integer.parseInt(inputs[2]);
        int j = Integer.parseInt(inputs[3]);
        M = new int[f][c];

        if ((f * c) % 2 == 1 && (i + j) % 2 == 1 || f * c < 20) {
            System.out.println("Impossible Knight Tour");
        } else {
            printKnightTour(knightTour(f, c, i, j), c);
        }
        
    }
}
