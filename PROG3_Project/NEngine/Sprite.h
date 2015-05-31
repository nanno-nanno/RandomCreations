#ifndef SPRITE_H
#define SPRITE_H

#include <SDL2/SDL.h>
#include <string>
#include <vector>
#include "Rectangle.h"
#include "Point.h"
#include "NGameEngine.h"

/*
 Klassen Sprite fungerar i mycket likt klassen
 Component, sa jag tanker fokusera pa det specifika
 for just Sprite.
 
 Det tre saker som skiljer ut Sprite fran Component
 mest ar funktionen tick() och det att alla Sprite-
 objekt maste innehalla en SDL_Texture. Det tredje
 ar att inget Sprite objekt kan kopieras fran
 en till en annan.
 
 Om annat fungerar Sprite valdigt likt, det finns
 funktioner for tangent- och mustryckningar, de har
 en omgivande rektangel och ritas ut pa ett 
 SDL_Window.
 
 Sprite-objekt har aven en egen typ kallas Point.
 Point ar endast en bekvämlighet for anvandaren sa
 att istallet for att fraga efter objektets
 rektangels vanstra ovre horn. Sa kan man enklare
 be om objektets point, des punkt i fonstret.
 */

namespace nengine
{
    class NGameEngine;
    
    class Sprite
    {
    public:
        virtual void draw() = 0;
        virtual void tick() = 0;
        virtual void create() = 0;
        virtual void mouseDown(SDL_Event& event){}
        virtual void mouseUp(SDL_Event& event){}
        virtual void keyDown(SDL_Event& event){}
        virtual void keyUp(SDL_Event& event){}
        void setEngineInstance(NGameEngine& engineInstance);
        Rectangle getRectangle(){ return rectangle; }
        NGameEngine* getEngine(){ return engine; }
        Point getPoint(){ return point; }
        int getPointX(){ return point.x; }
        int getPointY(){ return point.y; }
        SDL_Texture* getTexture(){ return texture; }
        std::string getTexturePath(){ return texturePath; }
        void setTexture(SDL_Texture* newTexture){ texture = newTexture; }
        void setPointX(int newX){ point.x = newX; }
        void setPointY(int newY){ point.y = newY; }
        virtual ~Sprite();
    protected:
        Rectangle rectangle;
        Sprite(int x, int y, int w, int h, std::string texturePath);
        bool isCollidingWith(const Rectangle& other);
    private:
        NGameEngine* engine;
        Point point;
        SDL_Texture* texture;
        std::string texturePath;
        Sprite(const Sprite& other);
        const Sprite& operator=(const Sprite& other);
    };
}

#endif