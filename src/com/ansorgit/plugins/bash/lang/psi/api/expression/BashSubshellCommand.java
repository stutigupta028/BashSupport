/*
 * Copyright 2009 Joachim Ansorg, mail@ansorg-it.com
 * File: BashSubshellCommand.java, Class: BashSubshellCommand
 * Last modified: 2009-12-04
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ansorgit.plugins.bash.lang.psi.api.expression;

import com.ansorgit.plugins.bash.lang.psi.api.BashPsiElement;

/**
 * User: jansorg
 * Date: 21.05.2009
 * Time: 14:01:59
 */
public interface BashSubshellCommand extends BashPsiElement {
    String getCommandText();
}
