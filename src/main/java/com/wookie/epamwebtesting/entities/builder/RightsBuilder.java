/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.Rights;


public class RightsBuilder {
    private Rights instance = new Rights();
    
    public RightsBuilder setId(int id) {
        instance.setId(id);
        return this;
    }

    public RightsBuilder setName(String name) {
        instance.setName(name);
        return this;
    }
    
    public Rights build() {
        return instance;
    }
}
