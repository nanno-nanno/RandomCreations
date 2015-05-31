#ifndef METEORITE_H
#define METEORITE_H

#include "SpaceObject.h"

/*
 Ett objekt vars beteende liknar ett
 EnemyShips, med skillnad att det inte
 reagerar pa skott fran spelaren.
 -Subklass till SpaceObject
 */

class Meteorite : public SpaceObject
{
public:
    Meteorite(int x, int y, int w, int h, std::string texturePath);
    void tick();
    ~Meteorite();
};

#endif