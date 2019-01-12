/*
 * Copyright (C) 2018 Alessandro Falappa.
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
package de.dev.eth0.netbeans.plugins.regex.prefs;

/**
 * Holder of preferences key constants.
 *
 * @author Alessandro Falappa
 */
public final class PrefConstants {

    public static final String PREFKEY_COLOR_0 = "regex.color.0";
    public static final String PREFKEY_COLOR_1 = "regex.color.1";
    public static final String PREFKEY_COLOR_2 = "regex.color.2";
    public static final String PREFKEY_COLOR_3 = "regex.color.3";
    public static final String PREFKEY_COLOR_4 = "regex.color.4";
    public static final String PREFKEY_COLOR_5 = "regex.color.5";
    public static final String PREFKEY_COLOR_6 = "regex.color.6";
    public static final String PREFKEY_COLOR_PREFIX = "regex.color.";
    public static final int DEFAULT_COLOR_0 = (255 << 24) | (230 << 16) | (230 << 8) | 230;
    public static final int DEFAULT_COLOR_1 = (255 << 24) | (204 << 16) | (255 << 8) | 255;
    public static final int DEFAULT_COLOR_2 = (255 << 24) | (255 << 16) | (204 << 8) | 255;
    public static final int DEFAULT_COLOR_3 = (255 << 24) | (255 << 16) | (255 << 8) | 204;
    public static final int DEFAULT_COLOR_4 = (255 << 24) | (255 << 16) | (204 << 8) | 204;
    public static final int DEFAULT_COLOR_5 = (255 << 24) | (204 << 16) | (255 << 8) | 204;
    public static final int DEFAULT_COLOR_6 = (255 << 24) | (204 << 16) | (204 << 8) | 255;

    // prevent instantiation
    private PrefConstants() {
    }

}
