def place2Points(n):
    if n > 8:
        return 0
    if n > 4:
        return 1
    if n > 3:
        return 2
    if n > 2:
        return 4
    if n > 1:
        return 7
    if n > 0:
        return 12
    return -1

for i in range(20):
    print i,  place2Points(i)
