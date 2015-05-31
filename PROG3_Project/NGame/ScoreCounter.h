#ifndef SCORECOUNTER_H
#define SCORECOUNTER_H

#include "Label.h"
#include "PlayerShip.h"

/*
 Denna klass visas i fonstret som en siffra
 som raknas upp varje gang ett EnemyShip
 traffas av ett ShipShot.
 - Subklass till Label
 */

class ScoreCounter : public nengine::Label
{
public:
    ScoreCounter(int x, int y, int w, int h, std::string labelText);
    void draw();
    void setScore(int newScore){ score += newScore; }
    ~ScoreCounter();
private:
    int score = 0;
};

#endif