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
public interface IVariable
{
    public void setName(String name);
    
    public String getName();
    
    /**
     * Returns id of word in the source text (Conll, etc)
     * Interger for Conll
     * @return 
     */
    public Object getSourceId();
    
    /**
     * Sets id from source text
     * @param id 
     */
    public void setSourceId(Object id);
    
    /**
     * Returns id of word in the source text (Conll, etc)
     * Interger for Conll
     * @return 
     */
    public Object getLinkedId();
    
    /**
     * Sets id from source text
     * @param id 
     */
    public void setLinkedId(Object id);
    
    /**
     * Produces string representation of logic variable
     * @return string
     */
    public String toStrRepresentation();
    
    public Boolean isEmpty();
}
