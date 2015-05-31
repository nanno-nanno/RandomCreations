#include "ShipShot.h"

ShipShot::ShipShot(int x, int y, int w, int h, std::string texturePath) : SpaceObject(x, y, w, h, texturePath)
{
    
}

/*
 I denna funktion sa tittar man for varje varv
 i NGameEngines loop efter om skottet har 
 traffat ett EnemyShip-objekt och sa fall doda
 det och sedan ta bort sig sjalv. Nar skottet nar
 slutet av banan, alternativt krockat med något, 
 sa tar det bort sig sjalvt. Skottet ror sig en
 bestamd fart at hoger tills det att nagon av
 ovanstaende situationer intraffar.
 */
void ShipShot::tick()
{
    for (Sprite* s : getEngine()->getSprites())
        if (s == this)
            continue;
        else if (isCollidingWith(s->getRectangle()))
        {
            hit = true;
            EnemyShip* e = dynamic_cast<EnemyShip*>(s);
            if (e != 0)
                e->setHealth(0);
        }
    
    if (hit == true || getPointX() > getEngine()->getWindowWidth())
        getEngine()->remove(this);
    else
    {
        setPointX(getPointX() + 5);
        move(getPoint());
    }
    
    
}

ShipShot::~ShipShot()
{
    
}