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

package org.tariel.pla.logics;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class LogicFunction implements IFunction
{

    @Override
    public List<IFunction> getSub()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addSub(IFunction sub)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toStrRepresentation()
    {
	String result = "";
	List<String> sub_strs = new ArrayList<>();
	for (IFunction sub: this.getSub())
	{
	    sub_strs.add(sub.toStrRepresentation());
	}
	result = String.join(" ⋀ ", sub_strs);
	return result;
    }

    @Override
    public IFunction toClassicLogic()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cleanSubs()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
