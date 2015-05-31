#include <iostream>
#include "NGameEngine.h"
#include "MenuButton.h"
#include "Label.h"

int main(int argc, const char * argv[]) {

    /* Spelmotor - initiering */
    nengine::NGameEngine* n = new nengine::NGameEngine("NGame - Spacy Shooter");
    n->setFPS(60);
    
    /* Huvudmeny */
    nengine::Label* gameTitle = nengine::Label::getInstance(0, 0, 500, 30, "Spacy Shooter");
    MenuButton* m1 = new MenuButton(300, 250, 300, 50, "START", "upIcon.bmp", "downIcon.bmp");
    MenuButton* m2 = new MenuButton(300, 350, 300, 50, "QUIT", "upIcon.bmp", "downIcon.bmp");
    
    /* Adderar huvudmenyobjekt till spelmotorn */
    n->add(gameTitle);
    n->add(m1); /* Här i finns alla spelobjekt */
    n->add(m2);
    
    /* Spelets start */
    n->run();
    
    delete n;
}