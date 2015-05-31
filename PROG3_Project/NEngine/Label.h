#ifndef LABEL_H
#define LABEL_H

#include "Component.h"
#include <string>
#include <SDL2_ttf/SDL_ttf.h>

/*
 En klass som placerar en textstrang i ett SDL_Window.
 Anvander sig av NGameEngine-objektets givna typsnitt
 och en SDL_Texture att rita med.
 - Subklass till Component
 */

namespace nengine
{
    class Label : public Component
    {
    public:
        static Label* getInstance(int x, int y, int w, int h, std::string labelText);
        void draw();
        void create();
        std::string getLabelText() const { return text; }
        SDL_Texture* getTexture(){ return texture; }
        void setLabelText(std::string newLabelText);
        ~Label();
    protected:
        Label(int x, int y, int w, int h, std::string labelText);
    private:
        SDL_Texture* texture;
        std::string text;
    };
}

#endif