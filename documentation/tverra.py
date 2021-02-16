import random as rnd

GOLD_LIMIT = 7

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
    actives = len(active_players(players))
    worst_player = None
    worst_shot = 0.9
    first = actives == len(players)
    winner = actives == 1
    for i, p in enumerate(players):
        p.out = p.out and not first
        if p.out:
            continue
        print "Neste deltager er", p
        shot = input("  Hoyde: ")/100.0
        print "  Skudd fra", p, ":" , "%.2f" %shot
        if shot < worst_shot:
            worst_shot = shot
            worst_player = p
        if shot < 0.2 or winner: # you're out if shoot out or alone
            p.out = True
        if shot > 0.9 or winner:
            p.points += (shot > 0.9) + winner
            while i > 0 and p.points > players[i-1].points: #advance in the shooting order
                players[i], players[i-1] = players[i-1], players[i]
                i -= 1
            if(p.points >= GOLD_LIMIT):
                print "Der var gullet sikret!"
                print "Resultater:"
                for j, p in enumerate(players):
                    print " ", j+1, p.name, p.points
                return players
    if worst_player:
        worst_player.out = True
    else:
        print "Alle traff tverra"
    return players

def hovedrunde(players, r):
    i = 0
    while len(active_players(players)) > 0 and max_score(players) < GOLD_LIMIT:
        i += 1
        print " Delrunde",  str(r)+"."+str(i)
        #print(players)
        players = period(players)
        #raw_input()
    #print(players)
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
        #print (max_score(players))
        for p in players:
            p.out = False
        players = hovedrunde(players, i)

if __name__ == "__main__":
    aplayers = [Player(p) for p in ["Sigurd", "Peder", "Sindre", "Kiple", "Halvorsen", "Tom", "Brana", "Jervell", "Lilleeng", "Hakon"]]
    rnd.shuffle(aplayers)
    game(aplayers)


