#include "Meteorite.h"
#include "Rows.h"

Meteorite::Meteorite(int x, int y, int w, int h, std::string texturePath) : SpaceObject(x, y, w, h, texturePath)
{
    
}

/*
 Objekt av denna typ ror sig en bestamd hastighet
 at vanster, men y-ledet bestams av en random int
 som i sin tur anvands for att valja en av 5 banor
 som objektet ska aka i. Banan vaxlas varje gang
 da objektet nar vansterkanten pa spelplanen.
 */
void Meteorite::tick()
{
    srand(time(NULL));
    int randomROW = rand() % 10 + 1;
    int yPos = 0;
    switch (randomROW)
    {
        case 1:
            yPos = Rows::ROW1;
            break;
        case 2:
            yPos = Rows::ROW2;
            break;
        case 3:
            yPos = Rows::ROW3;
            break;
        case 4:
            yPos = Rows::ROW4;
            break;
        case 5:
            yPos = Rows::ROW5;
            break;
        case 6:
            yPos = Rows::ROW3;
            break;
        case 7:
            yPos = Rows::ROW4;
            break;
        case 8:
            yPos = Rows::ROW5;
            break;
        case 9:
            yPos = Rows::ROW2;
            break;
        case 10:
            yPos = Rows::ROW1;
            break;
    }
    
    if (getPointX() < 0)
    {
        setPointY(yPos);
        setPointX(getEngine()->getWindowWidth());
        move(getPoint());
    }
    else
    {
        setPointX(getPointX() - 3);
        move(getPoint());
    }
}

Meteorite::~Meteorite()
{
    
}