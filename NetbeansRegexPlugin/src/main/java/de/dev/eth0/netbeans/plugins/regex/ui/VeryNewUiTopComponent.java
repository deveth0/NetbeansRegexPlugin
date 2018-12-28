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
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//de.dev.eth0.netbeans.plugins.regex.ui//VeryNewUi//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "VeryNewUiTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "de.dev.eth0.netbeans.plugins.regex.ui.VeryNewUiTopComponent")
@ActionReference(path = "Menu/Window", position = 1277)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_VeryNewUiAction",
        preferredID = "VeryNewUiTopComponent"
)
@Messages({
    "CTL_VeryNewUiAction=VeryNewUi",
    "CTL_VeryNewUiTopComponent=VeryNewUi"
})
public final class VeryNewUiTopComponent extends TopComponent {

    private static final String TXT_REGEX_VALID = "Regular expression is valid.";
    private static final String TXT_REPLEX_VALID = "Replacement expression is valid.";
    private static final String TXT_NO_REGEX = org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lStatusRegex.text"); // NOI18N;
    private static final String TXT_NO_REPLACEMENT = org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lStatusReplex.text"); // NOI18N;
    private static final Style STY_NORMAL = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    private static final int THROTTLE_DELAY = 300;
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
    private final DocumentListener dlPrinter = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            processEvent(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            processEvent(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            processEvent(e);
        }

        void processEvent(DocumentEvent e) {
            System.out.print(e.getDocument().toString());
            if (e.getType().equals(DocumentEvent.EventType.INSERT)) {
                try {
                    System.out.format(" INSERT: %s%n", e.getDocument().getText(e.getOffset(), e.getLength()));
                } catch (BadLocationException ex) {
                }
            } else {
                System.out.format(" %s%n", e.getType());
            }
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

    public VeryNewUiTopComponent() {
        initComponents();
        setName(Bundle.CTL_VeryNewUiTopComponent());
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

        org.openide.awt.Mnemonics.setLocalizedText(lGroups, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lGroups.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lRegex, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lRegex.text")); // NOI18N

        cbRegex.setEditable(true);
        cbRegex.setModel(dcbmRegExs);
        cbRegex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegexActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(bSave, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.bSave.text")); // NOI18N
        bSave.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        lStatusRegex.setFont(lStatusRegex.getFont().deriveFont(lStatusRegex.getFont().getSize()-2f));
        org.openide.awt.Mnemonics.setLocalizedText(lStatusRegex, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lStatusRegex.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lText, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lText.text")); // NOI18N

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

        splitter.setLeftComponent(pMatch);

        org.openide.awt.Mnemonics.setLocalizedText(lReplace, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lReplace.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lReplaced, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lReplaced.text")); // NOI18N

        taReplaced.setEditable(false);
        taReplaced.setColumns(10);
        taReplaced.setRows(3);
        jScrollPane3.setViewportView(taReplaced);

        lStatusReplex.setFont(lStatusReplex.getFont().deriveFont(lStatusReplex.getFont().getSize()-2f));
        org.openide.awt.Mnemonics.setLocalizedText(lStatusReplex, org.openide.util.NbBundle.getMessage(VeryNewUiTopComponent.class, "VeryNewUiTopComponent.lStatusReplex.text")); // NOI18N

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
            System.out.format("Adding to history: %s%n", regex);
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
        // listen to regex edits
        JTextField tf = (JTextField) cbRegex.getEditor().getEditorComponent();
        tf.getDocument().addDocumentListener(dlPrinter);
        tf.getDocument().addDocumentListener(dlUpdateHiglight);
        // listen to sample text edits
        tpText.getDocument().addDocumentListener(dlPrinter);
        tpText.getDocument().addDocumentListener(dlUpdate);
        // listen to replex edits
        txReplace.getDocument().addDocumentListener(dlPrinter);
        txReplace.getDocument().addDocumentListener(dlUpdate);
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    private void updateHighlight() {
        System.out.println("de.dev.eth0.netbeans.plugins.regex.ui.VeryNewUiTopComponent.updateHighlight()");
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
        System.out.println("de.dev.eth0.netbeans.plugins.regex.ui.VeryNewUiTopComponent.updateRegexp()");
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
