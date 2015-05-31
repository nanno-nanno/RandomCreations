#include "Button.h"
#include "NGameEngine.h"

namespace nengine
{
    /* Skapar ett nytt Button-objekt på heapen och returnerar dess pekare */
    Button* Button::getInstance(int x, int y, int w, int h, std::string buttonText, std::string upIconPath, std::string downIconPath)
    {
        return new Button(x, y, w, h, buttonText, upIconPath, downIconPath);
    }
    
    Button::Button(int x, int y, int w, int h, std::string buttonText, std::string upIconPath, std::string downIconPath) : Component(x, y, w, h), text(buttonText), upPath(upIconPath), downPath(downIconPath), isDown(false){}
    
    /* draw() anvands för att rita ut knappen i ett SDL_Window genom dess SDL_Renderer */
    void Button::draw()
    {
        if (isDown)
            SDL_RenderCopy(getEngine()->getRenderer(), downIcon, NULL, &rectangle);
        else
            SDL_RenderCopy(getEngine()->getRenderer(), upIcon, NULL, &rectangle);
        SDL_RenderCopy(getEngine()->getRenderer(), texture, NULL, &rectangle);
    }
    
    /* Initierar alla de medlemmar som behover ett SDL_Renderer for att kunna anvandas */
    void Button::create()
    {
        SDL_Color black = { 0, 0, 0 };
        SDL_Surface* tempPicture = TTF_RenderText_Solid(getEngine()->getFont(), text.c_str(), black);
        texture = SDL_CreateTextureFromSurface(getEngine()->getRenderer(), tempPicture);
        SDL_FreeSurface(tempPicture);
        SDL_Surface* upSurface = SDL_LoadBMP(upPath.c_str());
        if (upSurface == nullptr)
            throwException("Picture for upIcon missing! ", SDL_GetError);
        upIcon = SDL_CreateTextureFromSurface(getEngine()->getRenderer(), upSurface);
        SDL_FreeSurface(upSurface);
        SDL_Surface* downSurface = SDL_LoadBMP(downPath.c_str());
        if (downSurface == nullptr)
            throwException("Picture for downIcon missing! ", SDL_GetError);
        downIcon = SDL_CreateTextureFromSurface(getEngine()->getRenderer(), downSurface);
        SDL_FreeSurface(downSurface);
    }
    
    /* Ritar om knappen med den nya angivna texten */
    void Button::setText(std::string newText)
    {
        SDL_DestroyTexture(texture);
        SDL_Color black = { 0, 0, 0 };
        SDL_Surface* tempPicture = TTF_RenderText_Solid(getEngine()->getFont(), text.c_str(), black);
        texture = SDL_CreateTextureFromSurface(getEngine()->getRenderer(), tempPicture);
        SDL_FreeSurface(tempPicture);
    }
    
    /* Knappen trycks ner */
    void Button::mouseDown(SDL_Event& event)
    {
        if (rectangle.contains(event.button.x, event.button.y))
            isDown = true;
    }
    
    /* Knappen slapps upp, perform() kallas */
    void Button::mouseUp(SDL_Event& event)
    {
        if (rectangle.contains(event.button.x, event.button.y))
            perform(this);
        isDown = false;
    }
    
    /* En funktion vars mening ar att overskoggas av tillampningen, for att hantera anvandarens val */
    void Button::perform(Button* knappen){}
    
    Button::~Button()
    {
        SDL_DestroyTexture(texture);
        SDL_DestroyTexture(downIcon);
        SDL_DestroyTexture(upIcon);
    }
}