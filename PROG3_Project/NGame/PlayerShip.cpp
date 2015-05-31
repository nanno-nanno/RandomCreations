#include "PlayerShip.h"
#include "Label.h"

PlayerShip::PlayerShip(int x, int y, int w, int h, std::string texturePath) : SpaceShip(x, y, w, h, texturePath){}

/*
 For varje varv i NGameEngines loop sa
 tittas det efter ifall PlayerShip har
 krockat med nagot annat objekt och i
 sa fall sa tas objektet bort och pa
 skarmen laggs det till tva Labels som
 visar att spelet ar slut.
 */
void PlayerShip::tick()
{
    for (Sprite* s : getEngine()->getSprites())
        if (s == this)
            continue;
        else if (isCollidingWith(s->getRectangle()))
            setHealth(0);
    
    if (isDead() == true)
    {
        nengine::Label* gameOver = nengine::Label::getInstance(100, 200, 400, 100, "GAME OVER");
        nengine::Label* gameOver2 = nengine::Label::getInstance(150, 280, 300, 75, "Press Escape to quit");
        getEngine()->add(gameOver);
        getEngine()->add(gameOver2);
        getEngine()->remove(this);
    }
    else
    {
        move(getPoint());
    }
}

/*
 Nar anvandaren trycker ner en tangent sa tittas
 det har efter ifall denna tangent antingen ar
 uppil, nerpil eller mellanslag. Om anvandaren
 har tryckt uppil sa flyttas objektet uppat och
 vice versa. Om mellanslags har tryckts ner sa
 avfyrar PlayerShip ett skott med funktionen
 shoot().
 */
void PlayerShip::keyDown(SDL_Event& event)
{
    switch (event.key.keysym.sym)
    {
        case SDLK_UP:
            if (getPointY() <= Rows::ROW1)
                break;
            setPointY(getPointY() - 50);
            break;
        case SDLK_DOWN:
            if (getPointY() >= Rows::ROW5)
                break;
            setPointY(getPointY() + 50);
            break;
        case SDLK_SPACE:
            shoot();
            break;
        default:
            break;
    }
}

/*
 Denna funktion skapar ett nytt ShipShot-objekt och
 placerar detta vid PlayerShip-objektets hogerkant.
 Sedan laggs objektet till NGameEngines handelseloop.
 */
void PlayerShip::shoot()
{
    ShipShot* shot = new ShipShot(getPointX() + 60, getPointY() + 15, 10, 10, "ShipShot.bmp");
    getEngine()->add(shot);
}

PlayerShip::~PlayerShip(){}