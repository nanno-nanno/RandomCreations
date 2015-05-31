#include "Icon.h"
#include "NGameEngine.h"

namespace nengine
{
    /* Returnerar en pekare till ett nytt Icon-objekt */
    Icon* Icon::getInstance(int x, int y, int w, int h, std::string texturePath)
    {
        return new Icon(x, y, w, h, texturePath);
    }
    
    Icon::Icon(int x, int y, int w, int h, std::string texturePath) : Component(x, y, w, h), imagePath(texturePath){}
    
    /* Ritar ut objektet */
    void Icon::draw()
    {
        SDL_RenderCopy(getEngine()->getRenderer(), texture, NULL, &rectangle);
    }
    
    /* Initierar alla SDL-medlemmar */
    void Icon::create()
    {
        SDL_Surface* textureToLoad = SDL_LoadBMP(imagePath.c_str());
        if (textureToLoad == nullptr)
            throwException("Picture File missing! ", SDL_GetError);
        Uint32 transp = *(Uint32*)textureToLoad->pixels;
        SDL_SetColorKey(textureToLoad, SDL_RLEACCEL, transp);
        texture = SDL_CreateTextureFromSurface(getEngine()->getRenderer(), textureToLoad);
        SDL_FreeSurface(textureToLoad);
    }
    
    Icon::~Icon()
    {
        SDL_DestroyTexture(texture);
    }
}