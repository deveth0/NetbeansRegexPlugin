/*
 * Copyright (C) 2016 dev-eth0.de
 * Modifications copyright (C) 2018 Alessandro Falappa.
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
package de.dev.eth0.netbeans.plugins.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Evaluates a regular expression string keeping track of validation errors.
 *
 * @author deveth0
 * @author Alessandro Falappa
 */
public class RegexEvaluator {

    private String regex = "";
    private int flags = 0;
    private Pattern pattern;

    private Matcher matcher;

    public boolean validRegex;
    public boolean validReplex;
    public String regexReason;
    public String replexReason;
    public boolean matches;
    public String replaceFirst;
    public String replaceAll;
    public boolean lookingAt;
    public boolean find;
    public MatcherGroup[] groups = new MatcherGroup[0];

    /**
     * Update the evaluator with new input
     *
     * @param regex
     * @param replacement
     * @param input
     */
    public void update(String regex, int flags, String replacement, String input) {
        try {
            //TODO: catch empty/null exceptions
            if (!this.regex.equals(regex) || this.flags != flags) {
                pattern = Pattern.compile(regex, flags);
                this.regex = regex;
                this.flags = flags;
                this.validRegex = true;
                this.validReplex = true;
            }
            if (pattern != null) {
                matcher = pattern.matcher(input);
                matches = matcher.matches();
                matcher.reset();
                if (isNotEmpty(replacement)) {
                    try {
                        replaceFirst = matcher.replaceFirst(replacement);
                        replaceAll = matcher.replaceAll(replacement);
                        validReplex = true;
                    } catch (IllegalArgumentException iae) {
                        validReplex = false;
                        replexReason = iae.getMessage();
                        replaceAll = "";
                        replaceFirst = "";
                    } catch (IndexOutOfBoundsException ioe) {
                        validReplex = false;
                        replexReason = ioe.getMessage();
                        replaceAll = "";
                        replaceFirst = "";
                    }
                }
                lookingAt = matcher.lookingAt();
                matcher.reset();
                find = matcher.find();
                if (find) {
                    groups = new MatcherGroup[matcher.groupCount() + 1];
                    for (int i = 0; i < matcher.groupCount() + 1; i++) {
                        groups[i] = new MatcherGroup(matcher.start(i), matcher.end(i), matcher.group(i));
                    }
                } else {
                    groups = new MatcherGroup[0];
                }
            }
        } catch (PatternSyntaxException pse) {
            this.validRegex = false;
            this.regexReason = pse.getMessage();
        }
    }

    private boolean isNotEmpty(String toTest) {
        return toTest != null && !toTest.isEmpty();
    }

    /**
     * A single matcher group
     */
    public static class MatcherGroup {

        public final int start;
        public final int end;
        public final String group;

        /**
         *
         * @param start
         * @param end
         * @param group
         */
        public MatcherGroup(int start, int end, String group) {
            this.start = start;
            this.end = end;
            this.group = group;
        }

    }
}
