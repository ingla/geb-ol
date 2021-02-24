from math import ceil, sqrt

k = 2 #arity
N = 6 #depth
s = None #scale

def setup():
    global s
    size(800, 800)
    textSize(10)
    textAlign(CENTER, CENTER)
    sz = min(height, width)*0.95
    s = (sz/2)/ sqrt(k**N-k+1)
    noLoop()
    
def radius(n, k):
    global s
    r = sqrt(k**n-k+1)*s
    return r

def draw():
    global k, N
    strokeCap(SQUARE);
    translate(width/2, height/2)
    drawCircleTree(N, k)
    saveFrame()
    
def drawCircleTree(n, k):
    if n == 3:
        drawCircle(n, k)
        drawHalfCircle(n-1, k, PI-0.3,TWO_PI+0.3)
        drawHalfCircle(n-1, k, 0.3, PI-0.3)
        pushMatrix()
        drawLines(n, k)
        drawLines(n-1, k)
        drawVs(n, k)
        drawVs(n-1, k)
        popMatrix()
        return
        
    drawCircle(n, k)
    if n > 1:
        pushMatrix()
        drawLines(n, k)
        drawVs(n, k)
        popMatrix()
    drawCircleTree(n-1, k)

def drawVs(n,k):
    global N
    fill(0)
    rot = 0
    m = k**(n-2)-int(k**(n-3))
    for i in range(m):
        rotate(PI/(k**(n-2))+PI/2)
        if (n>2):
            for j in range(1, 2):
                x = lerp(0, 0, j/2.0)
                y1 = lerp(radius(n,k), radius(n-1, k), j/2.0)
                y2 = lerp(-radius(n,k), -radius(n-1, k), j/2.0)
                if(i%2 != 0):
                    rot = PI
                else:
                    rot = 0
                pushMatrix()
                translate(x, y1)
                rotate(rot)                
                text("VS", 0, 0)
                translate(-x, -y1)
                rotate(rot)
                popMatrix()
                
                pushMatrix()
                translate(x, y2)
                rotate(rot)                
                text("VS", 0, 0)
                translate(-x, -y2)
                rotate(rot)
                popMatrix()
                
        if (n == 3):
            for j in range(1, 20):
                x = lerp(0, 0, j/20.0)
                y = lerp(radius(n,k), -radius(n,k), j/20.0)
                if j ==6 or j == 12:
                    text("VS", x, y)
        rotate(PI/(k**(n-2))+PI/2)
        
def drawLines(n, k):
    global N
    vsline_width = 30.0
    m = k**(n-2)-int(k**(n-3))
    for i in range(m):
        rotate(PI/(k**(n-2))+PI/2)
        line(0, radius(n,k), 0, radius(N, k))
        line(0, -radius(n,k), 0, -radius(N, k))
        rotate(PI/(k**(n-2))+PI/2)

def drawCircle(n, k):
    fill(255)
    d = radius(n, k) * 2
    circle(0, 0, d)
    
def drawHalfCircle(n, k, start, stop):
    d = radius(n, k) * 2
    arc(0, 0, d, d, start, stop, CHORD)
