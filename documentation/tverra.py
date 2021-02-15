import random as rnd

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

players = [Player(p) for p in ["Sigurd", "Herman", "Peder", "Sindre", "Kiple", "Halvorsen", "Tom"]]
rnd.shuffle(players)

def active_players(players):
    return [p for p in players if not p.out]

def period(players):
    active = len(active_players(players))
    worst_player = None
    worst_shot = 1
    first = active == len(players)
    winner = active == 1
    for i, p in enumerate(players):
        p.out = p.out and not first
        if p.out:
            continue
        shot = p.shoot()
        if shot < worst_shot:
            worst_shot = shot
            worst_player = p
        if shot < 0.2:
            p.out = True
        if shot > 0.9 or winner:
            p.points += (shot > 0.9) + winner
            while i > 0 and p.points > players[i-1].points:
                players[i], players[i-1] = players[i-1], players[i]
                i -= 1
    worst_player.out = True
    return players

def round(players):
    while len(active_players(players)) > 0:
        print(players)
        period(players)
    print(players)
