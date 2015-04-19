/*
 * Copyright 2015 Nikita Gerasimov <tariel-x@ya.ru>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tariel.pla.logics.classic;

import java.util.ArrayList;
import java.util.List;
import org.tariel.pla.logics.IVariable;
import org.tariel.pla.logics.LogicVariable;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class CQuantifer implements ICFunction
{
    private IVariable var;
    
    public CQuantifer()
    {
	this.var = new LogicVariable();
	this.var.setName(java.util.UUID.randomUUID().toString());
    }
    
    public IVariable getVar()
    {
	return this.var;
    }
    
    public void setVar(IVariable newvar)
    {
	this.var = newvar;
    }

    @Override
    public List<ICFunction> getSub()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSub(ICFunction sub)
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cleanSubs()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toStrRepresentation()
    {
	String ret = "∃" + this.getVar().toStrRepresentation();
	return ret;
    }

    @Override
    public void resolveAnaphora()
    {
	throw new UnsupportedOperationException("Not supported.");
    }
}
