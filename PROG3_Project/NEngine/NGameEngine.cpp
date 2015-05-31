#include "NGameEngine.h"

namespace nengine
{
    /* Ett generellt Exception som kastas vid diverse fel som kan uppsta under initiering */
    void throwException(std::string message, const char* (*errorFunction)()){
        message += errorFunction();
        throw std::runtime_error(message.c_str());
    }
    
    /* Konstrukor, till skillnad fran resten av klasserna inom NEngine-namespacet sa initieras SDL-relaterade objekt direkt i konstruktorn */
    NGameEngine::NGameEngine(std::string title)
    {
        /* SDL initieras */
        if (SDL_Init(SDL_INIT_EVERYTHING) == -1)
            throwException("SDL_Init-error: ", SDL_GetError);
        
        /* Ett SDL_Window, dvs fonster, skapas */
        window = SDL_CreateWindow(title.c_str(), SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, windowWidth, windowHeight, SDL_WINDOW_RESIZABLE);
        if (window == nullptr)
            throwException("SDL_CreateWindow-error: ", SDL_GetError);
        
        /* fonstret far en SDL_Renderer */
        renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);
        if (renderer == nullptr)
            throwException("SDL_CreateRenderer-error: ", SDL_GetError);
        
        /* Typsnittshantering for SDL initieras */
        if (TTF_Init() == -1)
            throwException("TTF_Init-error: ", TTF_GetError);
        
        /* Typsnittet "Times New Roman" hamtas */
        font = TTF_OpenFont("Times New Roman.ttf", 12);
        if (font == nullptr)
            throwException("Font file missing: ", TTF_GetError);
        
        /* Bakgrundsfargen for fonstret satts */
        if (SDL_SetRenderDrawColor(renderer, 0, 0, 0, 255) < 0)
            throwException("SetRenderColor-error: ", SDL_GetError);
    }
    
    /* Funktion som lagger till en Component i vectorn for just dem */
    void NGameEngine::add(Component* component)
    {
        component->setEngineInstance(*this);
        component->create();
        components.push_back(component);
    }
    
    /* Samma som funktionen ovan, fast for Sprites */
    void NGameEngine::add(Sprite* sprite)
    {
        sprite->setEngineInstance(*this);
        sprite->create();
        sprites.push_back(sprite);
    }
    
    /* Funktion som tar bort ett givet Component-objekt fran NGameEngine-objektet */
    void NGameEngine::remove(Component* component)
    {
        for (Component* c : components)
            if (c == component)
            {
                components.erase(std::remove(components.begin(), components.end(), c), components.end());
                delete c;
            }
    }
    
    /* Samma som ovan, fast for en given Sprite */
    void NGameEngine::remove(Sprite* sprite)
    {
        for (Sprite* s : sprites)
            if (s == sprite)
            {
                sprites.erase(std::remove(sprites.begin(), sprites.end(), s), sprites.end());
                delete s;
            }
    }
    
    /* 
     run()-funktionen gommer denna SDL-loop som beskrivet i .h-filen
     plockar en SDL_Event och fragar sedan alla installerade objekt
     hur de vill reagera pa eventen. Sedan uppdateras och ritas alla
     objekt ut i fonstret. Langst ner i loopen efter ovannamnda sa 
     framkallas fordrojningen med hjalp av SDL_Delay, som anvandaren
     kan satta med funktionen setFPS(). 
     
     Det finns tva satt att stanna loopen. Ena ar ifall anvandaren
     stanger fonstret och det andra ifall anvandaren trycker på escape.
     Da satts en boolean (quit) till true, som stoppar den omgivande
     while-loopen.
     */
    void NGameEngine::run()
    {
        bool quit = false;
        int lasttick = 0;
        
        while (!quit){
            SDL_Event currentEvent;
            event = &currentEvent;
            while (SDL_PollEvent(&currentEvent)){
                switch (currentEvent.type){
                    case SDL_QUIT:
                        quit = true;
                        break;
                    case SDL_KEYDOWN:
                        if (currentEvent.key.keysym.sym == SDLK_ESCAPE)
                        {
                            quit = true;
                            break;
                        }
                        for (Component* c : components)
                            c->keyDown(currentEvent);
                        for (Sprite* s : sprites)
                            s->keyDown(currentEvent);
                        break;
                    case SDL_KEYUP:
                        for (Component* c : components)
                            c->keyUp(currentEvent);
                        for (Sprite* s : sprites)
                            s->keyUp(currentEvent);
                        break;
                    case SDL_MOUSEBUTTONDOWN:
                        for (Component* c : components)
                            c->mouseDown(currentEvent);
                        for (Sprite* s : sprites)
                            s->mouseDown(currentEvent);
                        break;
                    case SDL_MOUSEBUTTONUP:
                        for (Component* c : components)
                            c->mouseUp(currentEvent);
                        for (Sprite* s : sprites)
                            s->mouseUp(currentEvent);
                        break;
                }
            }
            SDL_RenderClear(renderer);
            for (Component* c : components)
                c->draw();
            for (Sprite* s : sprites)
                s->draw();
            SDL_RenderPresent(renderer);
            
            int delay=1000/fps-SDL_GetTicks()+lasttick;
            
            if(delay>0)
                SDL_Delay(delay);
            
            lasttick = SDL_GetTicks();
            
            if (stopRun == true)
                quit = true;
        }
    }
    
    /* Satter en boolean till true som avbryter loopen run(), vilket leder till att NGameEngine nu kan tas bort sakert */
    void NGameEngine::stop()
    {
        stopRun = true;
    }
    
    /* 
     Dekonstrukor, gar igenom vectorn for installerade Components
     respektive Sprites och tar bort dem. Sedan stangs TTF foljt
     av SDL av.
     */
    NGameEngine::~NGameEngine()
    {
        for (Component* c : components)
            delete c;
        for (Sprite* s : sprites)
            delete s;
        TTF_CloseFont(font);
        TTF_Quit();
        SDL_DestroyRenderer(renderer);
        SDL_DestroyWindow(window);
        SDL_Quit();
    }
}