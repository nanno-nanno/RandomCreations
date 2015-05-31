#ifndef SPACEOBSTACLE_H
#define SPACEOBSTACLE_H

#include "StaticSprite.h"

/*
 Denna klass ar en superklass tankt
 for olika hinder som man placerar i
 spelvarlden. I mitt spel anvander jag
 den endast for att placera vaggar som
 avskarmning av spelplanen.
 - Subklass till StaticSprite
 */

class SpaceObstacle : public nengine::StaticSprite
{
public:
    ~SpaceObstacle();
protected:
    SpaceObstacle(int x, int y, int w, int h, std::string texturePath);
};

#endif