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
package de.dev.eth0.netbeans.plugins.regex.ui;

import de.dev.eth0.netbeans.plugins.regex.RegexEvaluator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component for testing regular expressions.
 *
 * @author deveth0
 * @author Alessandro Falappa
 */
@ConvertAsProperties(
        dtd = "-//de.dev.eth0.netbeans.plugins.regex.ui//Ui//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "UiTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "de.dev.eth0.netbeans.plugins.regex.ui.UiTopComponent")
@ActionReference(path = "Menu/Window", position = 1275)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_UiAction",
        preferredID = "UiTopComponent"
)
@Messages({
    "CTL_UiAction=Regex Tester",
    "CTL_UiTopComponent=Regex Tester"
})
public final class UiTopComponent extends TopComponent {

    private static final String TXT_REGEX_VALID = "Regular expression is valid.";
    private static final String TXT_REPLEX_VALID = "Replacement expression is valid.";
    private static final String TXT_NO_REGEX = org.openide.util.NbBundle.getMessage(UiTopComponent.class, "VeryNewUiTopComponent.lStatusRegex.text"); // NOI18N;
    private static final String TXT_NO_REPLACEMENT = org.openide.util.NbBundle.getMessage(UiTopComponent.class, "VeryNewUiTopComponent.lStatusReplex.text"); // NOI18N;
    private static final Style STY_NORMAL = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    private static final int THROTTLE_DELAY = 300;
    private static final String PROPKEY_HIST_SIZE = "regex.history.size";
    private static final String PROPKEY_HIST_PREFIX = "regex.history.";
    private static final String PROPKEY_TEXT = "regex.sample.text";
    private static final String PROPKEY_REPL_EXP = "regex.replace.expression";
    private final Color COL_NORMAL;
    private final Color COL_SUCCESS;
    private final Color COL_ERROR;
    private final RegexEvaluator evaluator = new RegexEvaluator();
    private DefaultComboBoxModel<String> dcbmRegExs = new DefaultComboBoxModel<>();
    private Timer throttleUpdates = new Timer(THROTTLE_DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateRegexp();
        }
    });
    private Timer throttleUpdateHighlight = new Timer(THROTTLE_DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateRegexp();
            updateHighlight();
        }
    });
    private final DocumentListener dlUpdate = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            throttleUpdates.restart();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            throttleUpdates.restart();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            throttleUpdates.restart();
        }
    };
    private final DocumentListener dlUpdateHiglight = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            throttleUpdateHighlight.restart();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            throttleUpdateHighlight.restart();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            throttleUpdateHighlight.restart();
        }
    };

    public UiTopComponent() {
        initComponents();
        setName(Bundle.CTL_UiTopComponent());
        COL_NORMAL = lStatusRegex.getForeground();
        COL_SUCCESS = new Color(51, 153, 0);
        COL_ERROR = new Color(204, 51, 0);
        addStyles(tpText);
        addStyles(tpGroups);
// --------------------------------------------------
//        code to initially expand the left section
// --------------------------------------------------
//        try {
//            jSplitPane1.setDividerLocation(999999);
//            Field m = BasicSplitPaneUI.class.getDeclaredField("keepHidden");
//            m.setAccessible(true);
//            m.set(jSplitPane1.getUI(), true);
//        } catch (IllegalArgumentException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (IllegalAccessException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (NoSuchFieldException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (SecurityException ex) {
//            Exceptions.printStackTrace(ex);
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        splitter = new javax.swing.JSplitPane();
        pMatch = new javax.swing.JPanel();
        lGroups = new javax.swing.JLabel();
        lRegex = new javax.swing.JLabel();
        cbRegex = new javax.swing.JComboBox<>();
        bSave = new javax.swing.JButton();
        lStatusRegex = new javax.swing.JLabel();
        lText = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpGroups = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tpText = new javax.swing.JTextPane();
        pReplace = new javax.swing.JPanel();
        lReplace = new javax.swing.JLabel();
        txReplace = new javax.swing.JTextField();
        lReplaced = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taReplaced = new javax.swing.JTextArea();
        lStatusReplex = new javax.swing.JLabel();

        jTextArea3.setColumns(20);
        jTextArea3.setRows(3);
        jScrollPane4.setViewportView(jTextArea3);

        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setPreferredSize(new java.awt.Dimension(100, 60));

        jTextPane2.setPreferredSize(new java.awt.Dimension(100, 50));
        jScrollPane5.setViewportView(jTextPane2);

        setMinimumSize(new java.awt.Dimension(600, 185));
        setLayout(new java.awt.BorderLayout());

        splitter.setResizeWeight(0.7);
        splitter.setContinuousLayout(true);
        splitter.setOneTouchExpandable(true);

        org.openide.awt.Mnemonics.setLocalizedText(lGroups, org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.lGroups.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lRegex, org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.lRegex.text")); // NOI18N

        cbRegex.setEditable(true);
        cbRegex.setModel(dcbmRegExs);
        cbRegex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegexActionPerformed(evt);
            }
        });

        bSave.setIcon(org.kordamp.ikonli.swing.FontIcon.of(org.kordamp.ikonli.fontawesome.FontAwesome.SAVE,javax.swing.UIManager.getColor("Button.foreground")));
        bSave.setToolTipText(org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.bSave.toolTipText")); // NOI18N
        bSave.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        lStatusRegex.setFont(lStatusRegex.getFont().deriveFont(lStatusRegex.getFont().getSize()-2f));
        org.openide.awt.Mnemonics.setLocalizedText(lStatusRegex, org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.lStatusRegex.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lText, org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.lText.text")); // NOI18N

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 60));

        tpGroups.setPreferredSize(new java.awt.Dimension(100, 50));
        jScrollPane1.setViewportView(tpGroups);

        tpText.setPreferredSize(new java.awt.Dimension(100, 50));
        jScrollPane6.setViewportView(tpText);

        javax.swing.GroupLayout pMatchLayout = new javax.swing.GroupLayout(pMatch);
        pMatch.setLayout(pMatchLayout);
        pMatchLayout.setHorizontalGroup(
            pMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMatchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lRegex)
                    .addComponent(lText)
                    .addComponent(lGroups))
                .addGap(11, 11, 11)
                .addGroup(pMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(pMatchLayout.createSequentialGroup()
                        .addComponent(cbRegex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSave))
                    .addComponent(lStatusRegex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pMatchLayout.setVerticalGroup(
            pMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMatchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lRegex)
                    .addComponent(cbRegex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lStatusRegex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pMatchLayout.createSequentialGroup()
                        .addComponent(lText)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lGroups)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pMatchLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bSave, cbRegex});

        splitter.setLeftComponent(pMatch);

        org.openide.awt.Mnemonics.setLocalizedText(lReplace, org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.lReplace.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lReplaced, org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.lReplaced.text")); // NOI18N

        taReplaced.setEditable(false);
        taReplaced.setColumns(10);
        taReplaced.setRows(3);
        jScrollPane3.setViewportView(taReplaced);

        lStatusReplex.setFont(lStatusReplex.getFont().deriveFont(lStatusReplex.getFont().getSize()-2f));
        org.openide.awt.Mnemonics.setLocalizedText(lStatusReplex, org.openide.util.NbBundle.getMessage(UiTopComponent.class, "UiTopComponent.lStatusReplex.text")); // NOI18N

        javax.swing.GroupLayout pReplaceLayout = new javax.swing.GroupLayout(pReplace);
        pReplace.setLayout(pReplaceLayout);
        pReplaceLayout.setHorizontalGroup(
            pReplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReplaceLayout.createSequentialGroup()
                .addGroup(pReplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pReplaceLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pReplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lReplaced)
                            .addComponent(lReplace))
                        .addGap(5, 5, 5)
                        .addGroup(pReplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(txReplace)))
                    .addGroup(pReplaceLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(lStatusReplex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pReplaceLayout.setVerticalGroup(
            pReplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReplaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pReplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lReplace)
                    .addComponent(txReplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lStatusReplex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pReplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lReplaced)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        splitter.setRightComponent(pReplace);

        add(splitter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        Object regex = cbRegex.getSelectedItem();
        if (regex != null) {
            dcbmRegExs.insertElementAt(regex.toString(), 0);
        }
    }//GEN-LAST:event_bSaveActionPerformed

    private void cbRegexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRegexActionPerformed
        // force deselection of text
        JTextField tf = (JTextField) cbRegex.getEditor().getEditorComponent();
        tf.setCaretPosition(tf.getText().length());
    }//GEN-LAST:event_cbRegexActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox<String> cbRegex;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JLabel lGroups;
    private javax.swing.JLabel lRegex;
    private javax.swing.JLabel lReplace;
    private javax.swing.JLabel lReplaced;
    private javax.swing.JLabel lStatusRegex;
    private javax.swing.JLabel lStatusReplex;
    private javax.swing.JLabel lText;
    private javax.swing.JPanel pMatch;
    private javax.swing.JPanel pReplace;
    private javax.swing.JSplitPane splitter;
    private javax.swing.JTextArea taReplaced;
    private javax.swing.JTextPane tpGroups;
    private javax.swing.JTextPane tpText;
    private javax.swing.JTextField txReplace;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        throttleUpdates.setRepeats(false);
        throttleUpdateHighlight.setRepeats(false);
        // trigger an initial update (useful if state read from properties)
        updateRegexp();
        updateHighlight();
        // listen to regex edits
        JTextField tf = (JTextField) cbRegex.getEditor().getEditorComponent();
        tf.getDocument().addDocumentListener(dlUpdateHiglight);
        // listen to sample text edits
        tpText.getDocument().addDocumentListener(dlUpdate);
        // listen to replex edits
        txReplace.getDocument().addDocumentListener(dlUpdate);
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty(PROPKEY_TEXT, tpText.getText());
        p.setProperty(PROPKEY_REPL_EXP, txReplace.getText());
        p.setProperty(PROPKEY_HIST_SIZE, String.valueOf(dcbmRegExs.getSize()));
        for (int i = 0; i < dcbmRegExs.getSize(); i++) {
            String regex = dcbmRegExs.getElementAt(i);
            p.setProperty(genHistKey(i), regex);
        }
    }

    private String genHistKey(int i) {
        return String.format("%s%d", PROPKEY_HIST_PREFIX, i);
    }

    void readProperties(java.util.Properties p) {
        tpText.setText(p.getProperty(PROPKEY_TEXT, ""));
        txReplace.setText(p.getProperty(PROPKEY_REPL_EXP, ""));
        dcbmRegExs.removeAllElements();
        int histSize = Integer.parseInt(p.getProperty(PROPKEY_HIST_SIZE, "0"));
        for (int i = 0; i < histSize; i++) {
            final String key = genHistKey(i);
            if (p.containsKey(key)) {
                dcbmRegExs.addElement(p.getProperty(key));
            }
        }
    }

    private void updateHighlight() {
        StyledDocument txtDoc = (StyledDocument) tpText.getDocument();
        txtDoc.removeDocumentListener(dlUpdate);
        txtDoc.setCharacterAttributes(0, txtDoc.getLength(), STY_NORMAL, true);
        if (evaluator.groups.length == 0) {
            return;
        }
        if (evaluator.validRegex) {
            RegexEvaluator.MatcherGroup group = evaluator.groups[0];
            txtDoc.setCharacterAttributes(group.start, group.end - group.start, tpText.getStyle("grp0"), true);
            for (int i = 1; i < evaluator.groups.length; i++) {
                group = evaluator.groups[i];
                String styleName = String.format("grp%d", 1 + (i % 6));
                txtDoc.setCharacterAttributes(group.start, group.end - group.start, tpText.getStyle(styleName), true);
            }
        }
        txtDoc.addDocumentListener(dlUpdate);
    }

    private void updateRegexp() {
        JTextField tf = (JTextField) cbRegex.getEditor().getEditorComponent();
        String regex = tf.getText();
        String replacement = txReplace.getText();
        String input = tpText.getText();
        evaluator.update(regex, getFlags(), replacement, input);

        if (!regex.isEmpty()) {
            if (evaluator.validRegex) {
                lStatusRegex.setForeground(COL_SUCCESS);
                lStatusRegex.setText(TXT_REGEX_VALID);
            } else {
                lStatusRegex.setForeground(COL_ERROR);
                lStatusRegex.setText(firstLine(evaluator.regexReason));
            }
        } else {
            lStatusRegex.setForeground(COL_NORMAL);
            lStatusRegex.setText(TXT_NO_REGEX);
        }
        if (!replacement.isEmpty()) {
            if (evaluator.validReplex) {
                lStatusReplex.setForeground(COL_SUCCESS);
                lStatusReplex.setText(TXT_REPLEX_VALID);
                taReplaced.setText(evaluator.replaceAll);
            } else {
                lStatusReplex.setForeground(COL_ERROR);
                lStatusReplex.setText(evaluator.replexReason);
                taReplaced.setText("");
            }
        } else {
            lStatusReplex.setForeground(COL_NORMAL);
            lStatusReplex.setText(TXT_NO_REPLACEMENT);
            taReplaced.setText("");
        }
        // update groups
        if (!regex.isEmpty() && evaluator.validRegex) {
            updateGroups();
        } else {
            StyledDocument grpDoc = (StyledDocument) tpGroups.getDocument();
            try {
                grpDoc.remove(0, grpDoc.getLength());
            } catch (BadLocationException ex) {
                // should never happen
            }
        }
    }

    private void updateGroups() {
        StyledDocument grpDoc = (StyledDocument) tpGroups.getDocument();
        try {
            grpDoc.remove(0, grpDoc.getLength());
        } catch (BadLocationException ex) {
            // should never happen
        }
        if (evaluator.groups.length > 0) {
            RegexEvaluator.MatcherGroup group = evaluator.groups[0];
            try {
                grpDoc.insertString(grpDoc.getLength(), String.format("%d [%d,%d]", 0, group.start, group.end), tpGroups.getStyle("grp0"));
                grpDoc.insertString(grpDoc.getLength(), String.format(": %s\n", group.group), null);
            } catch (BadLocationException ex) {
                // should never happen
            }
            for (int i = 1; i < evaluator.groups.length; i++) {
                try {
                    group = evaluator.groups[i];
                    String styleName = String.format("grp%d", 1 + (i % 6));
                    grpDoc.insertString(grpDoc.getLength(), String.format("%d [%d,%d]", i, group.start, group.end), tpGroups.getStyle(styleName));
                    grpDoc.insertString(grpDoc.getLength(), String.format(": %s", group.group), null);
                    if (i + 1 < evaluator.groups.length) {
                        grpDoc.insertString(grpDoc.getLength(), "\n", null);
                    }
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    private int getFlags() {
        int flags = 0;
//    if (caseInsensitiveCheckBox.isSelected()) {
//      flags |= Pattern.CASE_INSENSITIVE;
//    }
//    if (multilineCheckBox.isSelected()) {
//      flags |= Pattern.MULTILINE;
//    }
//    if (dotAllCheckBox.isSelected()) {
//      flags |= Pattern.DOTALL;
//    }
//    if (unicodeCaseCheckBox.isSelected()) {
//      flags |= Pattern.UNICODE_CASE;
//    }
//    if (canonEqCheckBox.isSelected()) {
//      flags |= Pattern.CANON_EQ;
//    }
//    if (unixLinesCheckBox.isSelected()) {
//      flags |= Pattern.UNIX_LINES;
//    }
//    if (literalCheckBox.isSelected()) {
//      flags |= Pattern.LITERAL;
//    }
//    if (commentsCheckBox.isSelected()) {
//      flags |= Pattern.COMMENTS;
//    }
//    if (unicodeCharacterClassCheckBox.isSelected()) {
//      flags |= Pattern.UNICODE_CHARACTER_CLASS;
//    }
        return flags;
    }

    private void addStyles(JTextPane tpText) {
        StyleConstants.setBackground(tpText.addStyle("grp0", null), new Color(204, 255, 255));
        StyleConstants.setBackground(tpText.addStyle("grp1", null), new Color(255, 204, 255));
        StyleConstants.setBackground(tpText.addStyle("grp2", null), new Color(255, 255, 204));
        StyleConstants.setBackground(tpText.addStyle("grp3", null), new Color(255, 204, 204));
        StyleConstants.setBackground(tpText.addStyle("grp4", null), new Color(204, 255, 204));
        StyleConstants.setBackground(tpText.addStyle("grp5", null), new Color(204, 204, 255));
        StyleConstants.setBackground(tpText.addStyle("grp6", null), new Color(204, 204, 204));
    }

    private String firstLine(String str) {
        final int idx = str.indexOf('\n');
        if (idx >= 0) {
            return str.substring(0, idx);
        } else {
            return str;
        }
    }
}
