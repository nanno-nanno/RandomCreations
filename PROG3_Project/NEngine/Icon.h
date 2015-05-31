#ifndef ICON_H
#define ICON_H

#include "Component.h"
#include <string>

/*
 En klass som placerar en ikon i form av en .bmp
 i ett SDL_Window.
 - Subklass till Component
 */

namespace nengine
{
    class Icon : public Component
    {
    public:
        static Icon* getInstance(int x, int y, int w, int h, std::string texturePath);
        void draw();
        void create();
        SDL_Texture* getTexture(){ return texture; }
        std::string getImagePath(){ return imagePath; }
        ~Icon();
    protected:
        Icon(int x, int y, int w, int h, std::string texturePath);
    private:
        SDL_Texture* texture;
        std::string imagePath;
    };
}

#endif