#include "EnemyShip.h"

EnemyShip::EnemyShip(int x, int y, int w, int h, std::string texturePath) : SpaceShip(x, y, w, h, texturePath)
{
    
}

/*
 Denna funktion beter sig mycket likt Meteroites,
 med tva undantag. Det ena ar objektet reagerar
 pa att bli skjuten av ShipShots, i vilket fall
 de tas bort och placeras i hogerkanten av fonstret.
 Detta istallet for att konstant skapa nya objekt av
 EnemyShip sa fort ett forstors. Istallet anvands
 samma objekt flera ganger, men med samma resultat
 for spelet. Vid beskjutning sa ber aven EnemyShip-
 objektet en given ScoreCounter att rakna upp 
 spelarens poang.
 */
void EnemyShip::tick()
{    
    srand(time(NULL));
    int randomROW = rand() % 5 + 1;
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
    }
    
    if (getPointX() < 0)
    {
        setPointY(yPos);
        setPointX(getEngine()->getWindowWidth());
        move(getPoint());
    }
    else if (isDead())
    {
        if (score != nullptr)
            score->setScore(1);
        setHealth(1);
        setPointY(yPos);
        setPointX(getEngine()->getWindowWidth());
        move(getPoint());
    }
    else
    {
        setPointX(getPointX() - 5);
        move(getPoint());
    }
}

EnemyShip::~EnemyShip()
{
    
}