#ifndef STATICSPRITE_H
#define STATICSPRITE_H

#include "Sprite.h"

/*
 En subklass till Sprite som inte kan forflytta sig
 efter att den skapats.
 
 Jag gor inga fler kommentarer i tillhorande .cpp-fil
 da MovingSprite har valdigt liknande funktionalitet.
 - Subklass till Sprite
 */

namespace nengine
{
    class StaticSprite : public Sprite
    {
    public:
        static StaticSprite* getInstance(int x, int y, int w, int h, std::string texturePath);
        void draw();
        void tick();
        void create();
        ~StaticSprite();
    protected:
        StaticSprite(int x, int y, int w, int h, std::string texturePath);
    };
}

#endif