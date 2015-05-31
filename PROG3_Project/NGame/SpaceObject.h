#ifndef SPACEOBJECT_H
#define SPACEOBJECT_H

#include "MovingSprite.h"

/*
 Klass for att placera rorliga objekt
 som inte reagerar pa anvandarens input.
 Men till skillnad fran objekt av SpaceShip
 sa kan inte dessa forstoras av andra objekt.
 Sjalvklart skulle det ga att implementera, 
 men i mitt spel har jag denna klass som mall
 for objekt som agerar rorliga hinder.
 - Subklass till MovingSprite
 */

class SpaceObject : public nengine::MovingSprite
{
public:
    ~SpaceObject();
protected:
    SpaceObject(int x, int y, int w, int h, std::string texturePath);
};

#endif