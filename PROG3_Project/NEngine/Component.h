#ifndef COMPONENT_H
#define COMPONENT_H

#include "Rectangle.h"
#include "NGameEngine.h"

/*
 Component ar superklass till Button, Label och Icon
 Denna klass funkar som en "mall" (OBS inte template)
 for objekt som man vill skapa och lagga till i ett 
 SDL_Window. Fonstret finns hos ett NGameEngine-objekt.
 Varje Component maste ha en del funktioner som behovs
 for att de ska kunna ritas ut i fonstret: 
 - draw()
    kallas pa for att det faktiska ritandet. Funktionen
 - create() 
    anvands for att initiera alla de medlemmar
 som kraver information fran ett NGameEngine-objekt.
 
 Istallet for att tilldela ett NGameEngine-objekt vid
 skapandet av objekt, sa har jag valt att implementera
 det sa att ett Component-objekt inte ges ett NGameEngine-
 objekt innan det faktiskt ligger hos ett sadant, dvs i
 dess lista av Components.

 Alla Components har aven en rektangel av typen SDL_Rect
 for att enklare kunna bestamma plats att rita ut pa,
 och ifall man vill kolla om tva Components krockar.
 
 For alla Components finns aven mojligheten att hantera
 knapp- och mustryckningar.
 
 Component har en protected konstruktor for att hindra
 att tillampningen skapar objekt av den. Istallet gor
 man subklasser till den som sedan i sin tur alla har
 en statisk getInstance()-metod som returnerar en
 pekare till en subklass av Component. Detta for att
 subklasserna enbart ska kunna skapas dynamiskt.
 */

namespace nengine
{
    class NGameEngine;
    
    class Component
    {
    public:
        virtual void draw() = 0;
        virtual void create() = 0;
        virtual void mouseDown(SDL_Event& event){}
        virtual void mouseUp(SDL_Event& event){}
        virtual void keyDown(SDL_Event& event){}
        virtual void keyUp(SDL_Event& event){}
        void setEngineInstance(NGameEngine& engineInstance);
        NGameEngine* getEngine(){ return engine; }
        Rectangle getRectangle(){ return rectangle; }
        virtual ~Component();
    protected:
        Rectangle rectangle;
        Component(int x, int y, int w, int h);
        bool isCollidingWith(const Rectangle& other);
        
        Component(const Component&);
        const Component& operator=(const Component&);
    private:
        NGameEngine* engine;
    };
}

#endif