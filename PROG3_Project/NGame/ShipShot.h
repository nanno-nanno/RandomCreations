#ifndef SHIPSHOT_H
#define SHIPSHOT_H

#include "SpaceObject.h"
#include "PlayerShip.h"
#include "EnemyShip.h"

/*
 Ett skott som anvands av PlayerShip.
 Skotten tittar efter om de har krockat
 med ett EnemyShip och forstor i sa fall
 detta. Om annat sa forsvinner skottet
 nar det nar spelplanens slut. 
 - Subklass till SpaceObject
 */

class PlayerShip;
class EnemyShip;

class ShipShot : public SpaceObject
{
public:
    ShipShot(int x, int y, int w, int h, std::string texturePath);
    void tick();
    bool targetHit(){ return hit; }
    ~ShipShot();
private:
    bool hit = false;
};

#endif