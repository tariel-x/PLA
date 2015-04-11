/*
 * Copyright 2015 Nikita Gerasimov <n@tariel.ru>.
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
package org.tariel.pla;

import org.tariel.pla.logics.ConjunctionContainer;
import org.tariel.pla.logics.IFunction;
import org.tariel.pla.logics.VariableStorage;
import org.tariel.pla.sentence.ConllWord;
import org.tariel.pla.sentence.IWord;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class PLA
{
    private ConjunctionContainer func;
    
    public PLA()
    {
	func = new ConjunctionContainer();
    }
    
    public void fromConll(String conll)
    {
	VariableStorage.clear();
	func = new ConjunctionContainer();
	IWord word = new ConllWord();
	word.fromConll(conll);
	func.addSub(word.toPLA());
    }
    
    public String toLogicsString()
    {
	if (this.func.getSub().size() == 1)
	    return this.func.getSub().get(0).toStrRepresentation();
	else if(this.func.getSub().size() > 1)
	    return this.func.toStrRepresentation();
	else
	    return new String();
    }
    
    public void addConll(String conll)
    {
	IWord word = new ConllWord();
	word.fromConll(conll);
	func.addSub(word.toPLA());
    }
}
