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

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class LogicVariable implements IVariable
{
    
    private String name;
    private Object source_id;

    @Override
    public void setName(String name)
    {
	this.name = name;
    }

    @Override
    public String getName()
    {
	return this.name;
    }

    @Override
    public String toStrRepresentation()
    {
	return VariableStorage.getVar(this);
    }

    @Override
    public Object getSourceId()
    {
	return source_id;
    }

    @Override
    public void setSourceId(Object id)
    {
	source_id = id;
    }

    @Override
    public Boolean isEmpty()
    {
	if (this.source_id == null)
	    return true;
	else
	    return false;
    }
    
}
