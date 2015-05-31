#include "Sprite.h"

namespace nengine
{
    Sprite::Sprite(int x, int y, int w, int h, std::string texturePath) : rectangle(x, y, w, h), point(x, y), texturePath(texturePath){}
    
    /* Anger vilket NGameEngine objekt som det skall tillhora */
    void Sprite::setEngineInstance(NGameEngine& engineInstance)
    {
        engine = &engineInstance;
    }
    
    /* Enkel kollisionskoll, ser om tva Sprite-objekt kolliderar */
    bool Sprite::isCollidingWith(const Rectangle& other)
    {
        if (rectangle.overlaps(other))
            return true;
        return false;
    }
    
    Sprite::~Sprite(){}
}