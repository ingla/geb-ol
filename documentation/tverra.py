import random as rnd

GOLD_LIMIT = 10
SHOT_COUNT = 0
HIT_THRESHOLD = 0.9
MISS_THRESHOLD = 0.15

class Player:
    def __init__(self, name):
        self.name = name
        self.points = 0
        self.out = False

    def shoot(self):
        return rnd.random()
    
    def __repr__(self):
        s = self.name + ' (' + str(self.points) + ')'
        if self.out:
            s = '[' + s + ']'
        return s

def active_players(players):
    return [p for p in players if not p.out]

def period(players):
    global SHOT_COUNT, HIT_THRESHOLD
    worst_player = None
    worst_shot = HIT_THRESHOLD
    bonus_shot = (len(active_players(players)) == 1)
    winner = False #no winner yet
    for i, p in enumerate(players):
        event = False #if something commentable happened
        if p.out:
            continue
        
        shot = p.shoot() # input("  Hoyde: ")/100.0
        SHOT_COUNT += 1
        headStr = "  Skudd fra %-13s %.2f" %(str(p), shot)
        print "%-30s" %headStr,
        if shot < worst_shot or bonus_shot: # or bonus_shot since you should be out after the bonus shot
            worst_shot = shot
            worst_player = p
        if shot < MISS_THRESHOLD: 
            if(shot < MISS_THRESHOLD):
                print p.name, "skyter seg ut"
                event = True
            p.out = True #all players that miss are out
        if shot > HIT_THRESHOLD or bonus_shot: #ways of gaining points
            if(shot > HIT_THRESHOLD):
                print p.name, "treffer tverra!"
                event = True
            p.points += (shot > HIT_THRESHOLD) + bonus_shot
            while i > 0 and p.points > players[i-1].points: #advance in the shooting order
                players[i], players[i-1] = players[i-1], players[i]
                i -= 1
            if(p.points >= GOLD_LIMIT):
                winner = True
                break
        if not event:
            print
    if winner:
        if not event:
            print   
        printResults(players)
        return players
    if worst_player or bonus_shot: #if someone did not hit or only one player left
        endString = " Delrunden er over"
        if worst_player and not worst_player.out: #if they are not already out
            worst_player.out = True
            
            print "%-30s %s ryker ut" %(endString,worst_player.name)
        else:
            print endString
    else:
        print "Alle traff tverra"
    return players

def printResults(players):
    print "Der var gullet sikret!"
    print "Resultater:"
    printStandings(players)
    
def printStandings(players):
    for j, p in enumerate(players):
        print "%2d %10s %2d" %(j+1,p.name,p.points)

def hovedrunde(players, r):
    i = 0
    while len(active_players(players)) > 0 and max_score(players) < GOLD_LIMIT:
        i += 1
        fstString = " Delrunde %d.%d." %(r,i)
        print "%-31s%d igjen" %(fstString,len(active_players(players)))
        players = period(players)
        
    return players

def max_score(players):
    max_points = 0
    for p in players:
        max_points = max(p.points, max_points)
    return max_points
            
def game(players):
    i = 0
    while(max_score(players) < GOLD_LIMIT):
        i += 1
        print "Hovedrunde", i
        print "Stilling:"
        for p in players:
            p.out = False
        players = hovedrunde(players, i)
        
if __name__ == "__main__":
    actual_players = [Player(p) for p in ["Sigurd", "Peder", "Sindre", "Kiple", "Halvorsen", "Tom", "Brana", "Jervell", "Lilleeng", "Hakon", "Ingvild", "Herman"]]
    rnd.shuffle(actual_players)
    game(actual_players)
    print "Antall skudd var", SHOT_COUNT, "som i snitt blir", SHOT_COUNT/len(actual_players), "per deltager"


