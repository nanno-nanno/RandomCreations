#ifndef MENUBUTTON_H
#define MENUBUTTON_H

#include "Button.h"

/*
 Denna klass representerar en knapp som ritas
 ut i spelfonstret nar spelet startar. Knappar
 som skapas av denna typ overskoggar perform
 for att ha funktionalitet.
 - Subklass till Button
 */

class MenuButton : public nengine::Button
{
public:
    MenuButton(int x, int y, int w, int h, std::string buttonText, std::string upIconPath, std::string downIconPath);
    void perform(nengine::Button* button);
    ~MenuButton();
};

#endif