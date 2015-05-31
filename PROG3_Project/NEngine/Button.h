#ifndef BUTTON_H
#define BUTTON_H

#include "Component.h"
#include <SDL2_ttf/SDL_ttf.h>
#include <string>

/*
 En klass som placerar en tryckbar knapp i ett SDL_Window
 - Subklass till Component
 */

namespace nengine
{
    class Button : public Component
    {
    public:
        static Button* getInstance(int x, int y, int w, int h, std::string buttonText, std::string upIconPath, std::string downIconPath);
        void draw();
        void create();
        std::string getText() const { return text; }
        void setText(std::string newText);
        void mouseDown(SDL_Event& event);
        void mouseUp(SDL_Event& event);
        virtual void perform(Button* button);
        ~Button();
    protected:
        Button(int x, int y, int w, int h, std::string buttonText, std::string upIconPath, std::string downIconPath);
    private:
        SDL_Texture* texture, *upIcon, *downIcon;
        std::string text, upPath, downPath;
        bool isDown;
    };
}

#endif