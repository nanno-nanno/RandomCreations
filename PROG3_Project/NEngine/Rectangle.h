#ifndef RECTANGLE_H
#define RECTANGLE_H

#include <SDL2/SDL.h>

/*
 Klassen Rectangle anvands av bade Component
 och Sprite for att markera ett objekts omrade
 i ett SDL_Window. Detta for att lattare hantera
 kollision mellan tva objekt och for att ha en
 ram att rita ut objektet i. 
 - Subklass till SDL_Rect
 */

namespace nengine
{
    struct Rectangle : public SDL_Rect
    {
        Rectangle();
        Rectangle(int newX, int newY, int newW, int newH);
        bool contains(int xx, int yy) const;
        bool overlaps(const Rectangle& other);
        ~Rectangle();
    };
}

#endif