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

import java.util.List;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public interface IFunction
{
    public List<IFunction> getSub();
    
    public void addSub(IFunction sub);
    
    public void cleanSubs();
    
    /**
     * Produces string representation of logic formula
     * @return string
     */
    public String toStrRepresentation();
    
    /**
     * Returns current formula transformed from PLA to classic logic
     * @return formula
     */
    public IFunction toClassicLogic();
}
