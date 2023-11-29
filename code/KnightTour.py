# List of possible moves for a knight
MOVES = [(-1, -2), (-2, -1), (-2, 1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2)]







# Check if a position is valid on the board
def is_valid(pos, f, c, matrix):
    return 0 <= pos[0] < f and 0 <= pos[1] < c and not matrix[pos[0]][pos[1]]








# Sum two coordinate vectors
def sum_coordinates(v, w):
    return [v[0] + w[0], v[1] + w[1]]








# Convert 2D coordinates to a linear index
def locate_index(i, c):
    return i[0] * c + i[1]








#this function the route in the matrix
def mark(matrix, route, n):
    for i in route:
        matrix[i[0]][i[1]] = n











#QuickSort function to order the children from a route, from most accesible to less accesible
#This implements a heuristic, in which the algorithm prefers less accesible children
def sort_routes(routes):
    
    if(not routes): return []
    highest = []
    lowest = []
    h = count_children(routes[0])
    for i in routes[1:]:
        if count_children(i) >= h:
            highest.append(i)
        else:
            lowest.append(i)
    return sort_routes(highest) + [routes[0]] + sort_routes(lowest)







#returns the number of children a given route has, less children means less accesible
def count_children(route):
    
    k = 0
    for move in MOVES:
        s = sum_coordinates(route[-1], move)
        if is_valid(s, f, c, M):
            k += 1
    return k







#returns all the possible unvisited children a route has
def get_children(route):
    
    mark(M, route, 1)
    k = [route + [sum_coordinates(route[-1], move)] for move in MOVES if is_valid(sum_coordinates(route[-1], move), f, c, M)]
    S = sort_routes(k)
    if not S:
        mark(M, route, 0)
    return S









#return a route that traverses all the positions in the board from a given start(i, j)
def knight_tour(f, c, i, j):
    
    stack = [[[i, j]]]
    while stack:
        route = stack.pop()
        if len(route) == f * c:  #if the route traverses f*c positions, then it traverses all the board
            return route
        for child in get_children(route):
            stack.append(child)











def print_Knight_Tour(route):
    
    for idx, i in enumerate(route, start=1):
        if not (idx - 1) % c:
            print()
        k = locate_index(i, c) + 1
        print(f"{k:04d}", end=" ")
    print()









f, c, i, j = map(int, input().split())
M = [[0] * c for _ in range(f)]

if (f * c) & 1 and (i + j) & 1 or f * c < 20:  #knight tour only possible if f*c >= 20 and ((f*c)%2 = 0 or (i+j)%2 = 0)
    print("Impossible Knight Tour")
else:
    print_Knight_Tour(knight_tour(f, c, i, j))
