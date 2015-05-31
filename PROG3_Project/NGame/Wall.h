#ifndef WALL_H
#define WALL_H

#include "SpaceObstacle.h"

/*
 Klass som representerar en vagg
 i spelet. Vaggen har i sig ingen
 funktion, utan det ar tankt att
 andra objekt ska hantera hur de
 beter sig kring vaggar.
 - Subklass till SpaceObstacle
 */

class Wall : public SpaceObstacle
{
public:
    Wall(int x, int y, int w, int h, std::string texturePath);
    void tick();
    ~Wall();
};

#endif