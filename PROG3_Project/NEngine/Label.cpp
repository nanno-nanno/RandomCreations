#include "Label.h"

namespace nengine
{
    /* Returnerar en pekare till ett nytt Label-objekt */
    Label* Label::getInstance(int x, int y, int w, int h, std::string labelText)
    {
        return new Label(x, y, w, h, labelText);
    }
    
    Label::Label(int x, int y, int w, int h, std::string labelText) : Component(x, y, w, h), text(labelText){}
    
    /* Ritar ut objektet */
    void Label::draw()
    {
        SDL_RenderCopy(getEngine()->getRenderer(), texture, NULL, &rectangle);
    }
    
    /* Initierar alla SDL-medlemmar */
    void Label::create()
    {
        SDL_Color white = { 255, 255, 255 };
        SDL_Surface* tempPicture = TTF_RenderText_Solid(getEngine()->getFont(), text.c_str(), white);
        texture = SDL_CreateTextureFromSurface(getEngine()->getRenderer(), tempPicture);
        SDL_FreeSurface(tempPicture);
    }
    
    /* Ritar om objektet med den nya angivna texten */
    void Label::setLabelText(std::string newLabelText)
    {
        text = newLabelText;
        SDL_DestroyTexture(texture);
        SDL_Color white = { 255, 255, 255 };
        SDL_Surface* tempPicture = TTF_RenderText_Solid(getEngine()->getFont(), text.c_str(), white);
        texture = SDL_CreateTextureFromSurface(getEngine()->getRenderer(), tempPicture);
        SDL_FreeSurface(tempPicture);
    }
    
    Label::~Label()
    {
        SDL_DestroyTexture(texture);
    }
}