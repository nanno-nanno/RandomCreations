#include "StaticSprite.h"

namespace nengine
{
    StaticSprite* StaticSprite::getInstance(int x, int y, int w, int h, std::string texturePath)
    {
        return new StaticSprite(x, y, w, h, texturePath);
    }
    
    StaticSprite::StaticSprite(int x, int y, int w, int h, std::string texturePath) : Sprite(x, y, w, h, texturePath){}
    
    void StaticSprite::draw()
    {
        tick();
        SDL_RenderCopy(getEngine()->getRenderer(), getTexture(), NULL, &rectangle);
    }
    
    void StaticSprite::tick(){}
    
    void StaticSprite::create()
    {
        SDL_Surface* textureToLoad = SDL_LoadBMP(getTexturePath().c_str());
        if (textureToLoad == nullptr)
            throwException("Picture File missing! ", SDL_GetError);
        Uint32 transp = *(Uint32*)textureToLoad->pixels;
        SDL_SetColorKey(textureToLoad, SDL_RLEACCEL, transp);
        setTexture(SDL_CreateTextureFromSurface(getEngine()->getRenderer(), textureToLoad));
        SDL_FreeSurface(textureToLoad);
    }
    
    StaticSprite::~StaticSprite()
    {
        SDL_DestroyTexture(getTexture());
    }
}