/*
 *  Copyright 2009 root.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package pt.uc.dei.wsvd.bench.tpcc.object;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author root
 */
@XmlType(name = "NewOrderInput", namespace = "http://tpcc.bench.wsvd.dei.uc.pt/")
public class NewOrderLongList {

    @XmlElement(namespace = "http://tpcc.bench.wsvd.dei.uc.pt/", type = Long.class, required = true)
    public long[] longList;
}
