#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>  // Added for setw and setfill

// List of possible moves for a knight
std::vector<std::pair<int, int>> MOVES = {
    {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
    {1, 2}, {2, 1}, {2, -1}, {1, -2}
};




int f, c;
std::vector<std::vector<int>> M;




// Check if a position is valid on the board
bool isValid(std::pair<int, int> pos) {
    return 0 <= pos.first && pos.first < f && 0 <= pos.second && pos.second < c && M[pos.first][pos.second] == 0;
}

// Sum two coordinate vectors
std::pair<int, int> sumCoordinates(std::pair<int, int> v, std::pair<int, int> w) {
    return {v.first + w.first, v.second + w.second};
}

// Convert 2D coordinates to a linear index
int locateIndex(std::pair<int, int> i) {
    return i.first * c + i.second;
}

// Mark positions in the matrix
void mark(std::vector<std::vector<int>>& matrix, std::vector<std::pair<int, int>>& route, int n) {
    for (auto& i : route) {
        matrix[i.first][i.second] = n;
    }
}



// Returns the number of children a given route has, fewer children means less accessible
int countChildren(std::pair<int, int>& route) {

    int k = 0;
    for (auto& move : MOVES) {
        auto s = sumCoordinates(route, move);
        if (isValid(s)) {
            k++;
        }
    }
    return k;
}





// QuickSort function to order the children from a route, from most accessible to less accessible
// This implements a heuristic, in which the algorithm prefers less accessible children
std::vector<std::pair<int, int>> sortRoutes(std::vector<std::pair<int, int>>& route) {
    if (route.empty()) return {};
    
    std::vector<std::pair<int, int>> highest, lowest;
    int h = countChildren(route[0]);
    
    for (auto it = route.begin() + 1; it != route.end(); ++it) {
        if (countChildren(*it) >= h) {
            highest.push_back(*it);
        } else {
            lowest.push_back(*it);
        }
    }
    auto sortedHighest = sortRoutes(highest);
    auto sortedLowest = sortRoutes(lowest);
    sortedHighest.push_back(route[0]);
    sortedHighest.insert(sortedHighest.end(), sortedLowest.begin(), sortedLowest.end());
    return sortedHighest;

}






// Returns all the possible unvisited children a route has
std::vector<std::pair<int, int>> getChildren(std::vector<std::pair<int, int>>& route) {

    mark(M, route, 1);
    
    std::vector<std::pair<int, int>> k;
    for (auto& move : MOVES) {
        auto s = sumCoordinates(route.back(), move);
        if (isValid(s)) {
            k.push_back(s);
        }
    }
    
    auto sortedChildren = sortRoutes(k);
    
    if (sortedChildren.empty()) {
        mark(M, route, 0);
    }
    return sortedChildren;

}





// Returns a route that traverses all the positions on the board from a given start(i, j)
std::vector<std::pair<int, int>> knightTour(int i, int j) {
    std::vector<std::vector<std::pair<int, int>>> stack = {{{i, j}}};
    
    while (!stack.empty()) {
        auto route = stack.back();
        stack.pop_back();
        
        if (route.size() == f * c) {
            return route;
        }
        
        for (auto& child : getChildren(route)) {
            stack.push_back(route);
            stack.back().push_back(child);
        }
    }
    
    return {};  // Return an empty vector if no solution is found
}





// Print the Knight Tour route
void printKnightTour(const std::vector<std::pair<int, int>>& route) {
    for (size_t idx = 0; idx < route.size(); ++idx) {
        if (idx % c == 0) {
            std::cout << std::endl;
        }
        
        int k = locateIndex(route[idx]) + 1;
        std::cout << std::setw(4) << std::setfill('0') << k << " ";
    }
    std::cout << std::endl;
}




int main() {
    int i, j;
    std::cin >> f >> c >> i >> j;
    M = std::vector<std::vector<int>>(f, std::vector<int>(c, 0));
    
    if ((f * c) & 1 && (i + j) & 1 || f * c < 20) {
        std::cout << "Impossible Knight Tour" << std::endl;
    } else {
        printKnightTour(knightTour(i, j));
    }
    return 0;
}
