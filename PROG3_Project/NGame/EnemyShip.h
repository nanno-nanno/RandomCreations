#ifndef ENEMYSHIP_H
#define ENEMYSHIP_H

#include "SpaceShip.h"
#include "ScoreCounter.h"
#include "Rows.h"

/*
 Denna klass beter sig mycket likt Meteroite,
 men med skillnaden att objekt av denna typ 
 kan forstoras och ger poang i sadant fall. 
 Till skillnad fran PlayerShip sa kan dessa
 inte styras av anvandaren och de skjuter inga
 skott.
 - Subklass till SpaceShip
 */

class ScoreCounter;

class EnemyShip : public SpaceShip
{
public:
    EnemyShip(int x, int y, int w, int h, std::string texturePath);
    void tick();
    void setScoreCounter(ScoreCounter* counter){ score = counter; }
    ~EnemyShip();
private:
    ScoreCounter* score;
};

#endif