package com.hp.score.lang.compiler.transformers;
/*
 * Licensed to Hewlett-Packard Development Company, L.P. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/

/*
 * Created by orius123 on 05/11/14.
 */

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NavigateTransformer implements Transformer<Map<String, Object>, Map<String, String>> {

    @Override
    public Map<String, String> transform(Map<String, Object> rawData) {
        if (MapUtils.isEmpty(rawData)){
            return new LinkedHashMap<>();
        }
        Map<String, String> navigationData = new HashMap<>();
        for (Map.Entry<String, Object> entry : rawData.entrySet()) {
            //todo currently we support only string navigation
            // - SUCCESS: some_task
            // the value of the navigation is the step to go to
            if (entry.getValue() instanceof String){
                navigationData.put(entry.getKey(), (String) entry.getValue());
            }
        }

        return navigationData;
    }

    @Override
    public List<Scope> getScopes() {
        return Arrays.asList(Scope.AFTER_TASK);
    }

    @Override
    public String keyToTransform() {
        return null;
    }

    @Override
    public String keyToRegister() {
        return null;
    }
}

