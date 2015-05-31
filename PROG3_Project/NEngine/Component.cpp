#include "Component.h"

namespace nengine
{
    Component::Component(int x, int y, int w, int h) : rectangle(x, y, w, h){}
    
    /* Denna metod satter vilken instans av NGameEngine som den tillhor */
    void Component::setEngineInstance(NGameEngine& engineInstance)
    {
        engine = &engineInstance;
    }
    
    /* Enkel kollisionskoll, kan se om tva objekt krockar */
    bool Component::isCollidingWith(const Rectangle& other)
    {
        if (rectangle.overlaps(other))
            return true;
        return false;
    }
    
    Component::~Component(){}
}