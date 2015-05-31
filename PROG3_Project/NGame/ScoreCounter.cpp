#include "ScoreCounter.h"
#include <sstream>

ScoreCounter::ScoreCounter(int x, int y, int w, int h, std::string labelText) : Label(x, y, w, h, labelText)
{
    
}

/*
 Denna funktion omvandlar en int till
 en strang och renderar denna i fonstret.
 */
void ScoreCounter::draw()
{
    std::string str;
    std::ostringstream os;
    os << score;
    str = os.str();
    setLabelText(str);
    
    SDL_RenderCopy(getEngine()->getRenderer(), getTexture(), NULL, &rectangle);
}

ScoreCounter::~ScoreCounter()
{
    
}