#include "MenuButton.h"
#include "ScoreCounter.h"
#include "PlayerShip.h"
#include "EnemyShip.h"
#include "Wall.h"
#include "Meteorite.h"
#include "Label.h"

MenuButton::MenuButton(int x, int y, int w, int h, std::string buttonText, std::string upIconPath, std::string downIconPath) : Button(x, y, w, h, buttonText, upIconPath, downIconPath){}

/*
 I denna overskoggning av perform sa tittar
 man forst pa vad det ar som star pa knappen.
 I detta fall har vi tva vagar att ga:
 
 Om det star "START", dvs knappen med texten
 "START" pa har tryckts in och slappts upp,
 sa kommer det som star inom if-satsen 
 genomforas. Det som hander da ar att objektet
 skapar alla de spelobjekt som ska inga.
 Darafter laggs dessa till NGameEngine-
 objektet. Sedan tar knappen bort sig sjalv
 och andra instanser av samma typ. 
 
 Ifall man har tryckt pa knappen med texten
 "QUIT" sa stannas spelloopen i NGameEngine
 och programmet avslutas. 
 */
void MenuButton::perform(nengine::Button* button)
{
    if (button->getText() == "START")
    {
        ScoreCounter* score = new ScoreCounter(10, 380, 80, 80, "0");
        PlayerShip* p1 = new PlayerShip(70, 150, 50, 30, "PlayerShip.bmp");
        EnemyShip* e1 = new EnemyShip(640, Rows::ROW1, 50, 30, "EnemyShip.bmp");
        EnemyShip* e2 = new EnemyShip(590, Rows::ROW4, 50, 30, "EnemyShip.bmp");
        e1->setScoreCounter(score);
        e2->setScoreCounter(score);
        Meteorite* m1 = new Meteorite(630, Rows::ROW3, 40, 40, "Meteroite.bmp");
        Meteorite* m2 = new Meteorite(570, Rows::ROW5, 40, 40, "Meteroite.bmp");
        Wall* w1 = new Wall(0, 0, 640, 30, "wall.bmp");
        Wall* w2 = new Wall(0, 450, 640, 30, "wall.bmp");
        
        getEngine()->add(score);
        getEngine()->add(p1);
        getEngine()->add(e1);
        getEngine()->add(e2);
        getEngine()->add(m1);
        getEngine()->add(m2);
        getEngine()->add(w1);
        getEngine()->add(w2);
        
        for (Component* c : getEngine()->getComponents())
        {
            MenuButton* m = dynamic_cast<MenuButton*>(c);
            if (m != 0)
                getEngine()->remove(m);
        }
        
    }
    else if (button->getText() == "QUIT")
    {
        getEngine()->stop();
    }
}

MenuButton::~MenuButton()
{
    
}