#ifndef MOVINGSPRITE_H
#define MOVINGSPRITE_H

#include "Sprite.h"
#include "Point.h"

/*
 Klassen MovingSprite har som namnet antyder
 mojligheten att forflytta sig i ett SDL_Window,
 till skillnad fran StaticSprite.
 - Subklass till Sprite
 */

namespace nengine
{
    class MovingSprite : public Sprite
    {
    public:
        static MovingSprite* getInstance(int x, int y, int w, int h, std::string texturePath);
        void draw();
        void tick();
        void create();
        void move(Point point);
        ~MovingSprite();
    protected:
        MovingSprite(int x, int y, int w, int h, std::string texturePath);
    };
}

#endif