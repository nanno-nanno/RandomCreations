#ifndef POINT_H
#define POINT_H

/*
 En enkel struct med endast tva
 int:ar for att halla reda pa
 en Sprites placering i fonstret
 */

namespace nengine
{
    struct Point
    {
        Point(int xCoordinate, int yCoordinate);
        int x, y;
        ~Point();
    };
}

#endif
