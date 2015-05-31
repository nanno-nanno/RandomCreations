#include "MovingSprite.h"
#include <iostream>

namespace nengine
{
    /* Returnerar en pekare till ett nytt MovingSprite-objekt */
    MovingSprite* MovingSprite::getInstance(int x, int y, int w, int h, std::string texturePath)
    {
        return new MovingSprite(x, y, w, h, texturePath);
    }
    
    /* Konstruktor, sparar undan sokvagen till texturen och aven information om placering */
    MovingSprite::MovingSprite(int x, int y, int w, int h, std::string texturePath) : Sprite(x, y, w, h, texturePath){}
    
    /* 
     Denna funktion kallas likt for Component-objekt av NGameEngine
     draw() gor i sig inte sa mycket mer, nar det galler Sprite-objekt,
     an att rita. Dock kallar den på tick() dar det ar tankt att 
     information for objektet skall uppdateras.
     */
    void MovingSprite::draw()
    {
        tick();
        SDL_RenderCopy(getEngine()->getRenderer(), getTexture(), NULL, &rectangle);
    }
    
    /* En funktion som har inte gor nagot, utan det ar i tillampningen den kommer till nytta */
    void MovingSprite::tick(){}
    
    /* Likt Components create sa initieras har objektets alla SDL-relaterade medlemmar */
    void MovingSprite::create()
    {
        SDL_Surface* textureToLoad = SDL_LoadBMP(getTexturePath().c_str());
        if (textureToLoad == nullptr)
            throwException("Picture File missing! ", SDL_GetError);
        Uint32 transp = *(Uint32*)textureToLoad->pixels;
        SDL_SetColorKey(textureToLoad, SDL_RLEACCEL, transp);
        setTexture(SDL_CreateTextureFromSurface(getEngine()->getRenderer(), textureToLoad));
        SDL_FreeSurface(textureToLoad);
    }
    
    /* 
     MovingSprites unika egenskap att forflytta sig enkelt
     syns har. Som forklarat i Sprite sa anvander jag mig 
     av en egengjord struct kallad Point for att forenkla
     anvandandet av move.
     */
    void MovingSprite::move(Point point)
    {
        rectangle.x = point.x;
        rectangle.y = point.y;
    }
    
    MovingSprite::~MovingSprite()
    {
        SDL_DestroyTexture(getTexture());
    }
}