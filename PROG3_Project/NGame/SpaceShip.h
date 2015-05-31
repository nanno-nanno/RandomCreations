#ifndef SPACESHIP_H
#define SPACESHIP_H

#include "MovingSprite.h"

/*
 Denna klass ska representera rymdskepp
 i spelet. Klassen i sig innehaller inget
 matigt mer an funktionen isDead() som 
 ser efter som health ar 0.
 - Subklass till MovingSprite
 */

class SpaceShip : public nengine::MovingSprite
{
public:
    bool isDead();
    int getHeatlth(){ return health; }
    void setHealth(int newHealth){ health = newHealth; }
    ~SpaceShip();
protected:
    SpaceShip(int x, int y, int w, int h, std::string texturePath);
private:
    int health = 1;
};

#endif