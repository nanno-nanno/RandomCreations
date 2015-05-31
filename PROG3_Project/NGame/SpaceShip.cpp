#include "SpaceShip.h"

SpaceShip::SpaceShip(int x, int y, int w, int h, std::string texturePath) : MovingSprite(x, y, w, h, texturePath){}

bool SpaceShip::isDead()
{
    if (health > 0)
        return false;
    return true;
}

SpaceShip::~SpaceShip(){}