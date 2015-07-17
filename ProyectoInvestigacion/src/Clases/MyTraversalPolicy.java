/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;

/**
 *
 * @author ADOLFO
 */
public class MyTraversalPolicy extends FocusTraversalPolicy {
    ArrayList<Component> components = null;
    
    public MyTraversalPolicy(ArrayList<Component> list){
        components = new ArrayList<>();
        components.addAll(list);
    }

    @Override
    public Component getComponentAfter(Container focusCycleRoot, Component focused){
        int idx = components.indexOf(focused);
        if (idx >= components.size()-1){
            idx = -1;
        }
        return components.get(idx+1);
    }

    @Override
    public Component getComponentBefore(Container c, Component focused){
        int idx = components.indexOf(focused);
        if (idx == 0){
            idx = components.size();
        }
        return components.get(idx-1);
    }

    @Override
    public Component getFirstComponent(Container c){
        return components.get(0);
    }
    
    @Override
    public Component getLastComponent(Container c){
        return components.get(components.size()-1);
    }

    @Override
    public Component getDefaultComponent(Container aContainer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
