#ifndef PLAYERSHIP_H
#define PLAYERSHIP_H

#include "SpaceShip.h"
#include "ShipShot.h"
#include <vector>

/*
 Det enda objekt som anvandaren har
 kontrollen over. Skeppet kan styras
 upp och ner mellan de olika Rows
 som bestams av klassen med samma
 namn. Aven kan PlayerShip skjuta av
 sa kallade ShipShots som da placeras
 i fonstret och ror sig bestamt mot
 hoger. PlayerShip forstors om det
 krockar med nagot annat objekt och
 spelet tar slut.
 */

class ShipShot;

class PlayerShip : public SpaceShip
{
public:
    PlayerShip(int x, int y, int w, int h, std::string texturePath);
    void tick();
    void keyDown(SDL_Event& event);
    void shoot();
    ~PlayerShip();
};

#endif