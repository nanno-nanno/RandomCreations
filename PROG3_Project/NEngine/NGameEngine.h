#ifndef NGAMEENGINE_H
#define NGAMEENGINE_H

#include <SDL2/SDL.h>
#include <SDL2_ttf/SDL_ttf.h>
#include "Component.h"
#include "Sprite.h"
#include <string>
#include <vector>

/*
 Detta ar NEngines huvudklass, det ar har som de som alla
 de andra klasserna kommer till anvandning. NGameEngine har
 hand om ett SDL_Window med tillhorande SDL_Renderer och
 aven TTF_Font(dvs typsnitt for SDL) som bla. Label och
 Button har nytta av. Alla objekt som tillampnings-
 programmeraren skapar maste installeras i ett NGameEngine-
 objekt for att komma till sin ratta. Eftersom de alla har
 en visuell representation som kraver ett SDL_Window for
 att kunna visas. 
 
 For att installera respektive avinstallera ett objekt hos
 NGameEngine-objekt sa anvands funktionerna add() resp. 
 remove(). Dessa finns overlagrade for bade Components och
 Sprites. Objekten som installeras sparas i en vector for 
 att man enkelt ska kunna iterera over de olika objekten
 och kalla pa deras draw()-funktioner for att kunna rita ut
 dem pa skarmen. 
 
 Nar man har installerat ens objekt sa kan en "handelseloop" 
 koras igang med funktionen run(). For att avbryta loopen
 kan man anvanda stop(). Loopen plockar under korning in sa
 kallade SDL_Events sa som tangent- och mustryckningar och
 fragar sedan varje installerad Component och Sprite hur de
 vill reagera pa den nyligen plockade SDL_Eventen. Sedan
 kallas alla Components och Sprites draw()-funktioner for
 att rita ut objekten pa skarmen. 
 
 For ovrigt har NGameEngine loopen mojligheten att saktas 
 ner med funktionen setFPS() for hindra att event-flodet
 gar for fort. 
 */

namespace nengine
{
    class Component;
    class Sprite;
    
    class NGameEngine
    {
    public:
        NGameEngine(std::string title);
        void add(Component* component);
        void add(Sprite* sprite);
        void remove(Component* component);
        void remove(Sprite* sprite);
        void run();
        void stop();
        std::vector<Component*> getComponents(){ return components; }
        std::vector<Sprite*> getSprites(){ return sprites; }
        int getWindowWidth(){ return windowWidth; }
        int getWindowHeight(){ return windowHeight; }
        SDL_Event* getEvent(){ return event; }
        SDL_Window* getWindow(){ return window; }
        SDL_Renderer* getRenderer(){ return renderer; }
        TTF_Font* getFont(){ return font; }
        void setFPS(int newFps){ fps = newFps; }
        void setWindowWidth(int newWidth){ windowWidth = newWidth; }
        void setWindowHeight(int newHeight){ windowHeight = newHeight; }
        ~NGameEngine();
    private:
        std::vector<Component*> components;
        std::vector<Sprite*> sprites;
        int fps = 60;
        SDL_Event* event;
        SDL_Window* window;
        SDL_Renderer* renderer;
        TTF_Font* font;
        int windowWidth = 640;
        int windowHeight = 480;
        bool stopRun = false;
    };
    
    void throwException(std::string message, const char* (*errorFunction)());
}

#endif