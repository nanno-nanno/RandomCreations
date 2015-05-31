#include "Rectangle.h"

namespace nengine
{
    Rectangle::Rectangle()
    {
        x = y = w = h = 0;
    }
    
    Rectangle::Rectangle(int newX, int newY, int newW, int newH)
    {
        x = newX;
        y = newY;
        w = newW;
        h = newH;
    }
    
    /* contains() ser efter om en given punkt finns inom ramen for en rektangel */
    bool Rectangle::contains(int xx, int yy) const
    {
        return xx >= x && xx <= x + w && yy >= y && yy <= y + h;
    }
    
    /*
     Denna metod ser efter om tva rektanglar kolliderar med varandra
     Det gors genom att forst ta fram de olika rektanglarnas vaggar
     och sedan se om nagon av de motsvarande (exempelvis toppen mot
     botten) ar utom rackhall for varandra, varav det returneras 
     false, om annat returneras true, da rektanglarna fran nagon 
     vinkel kolliderar.
     */
    bool Rectangle::overlaps(const Rectangle& other)
    {
        int left1 = x;
        int left2 = other.x;
        int right1 = x + w;
        int right2 = other.x + other.w;
        int top1 = y;
        int top2 = other.y;
        int bottom1 = y + h;
        int bottom2 = other.y + other.h;
        
        if (bottom1 < top2) return false;
        if (top1 > bottom2) return false;
        
        if (right1 < left2) return false;
        if (left1 > right2) return false;
        
        return true;
    }
    
    Rectangle::~Rectangle()
    {
        
    }
}