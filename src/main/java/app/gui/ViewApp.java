/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of GameOfLife.
 * <p>
 * GameOfLife is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import app.Config;
import app.Translate;
import app.animation.Animator;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.Vector;

public class ViewApp extends JFrame {

    private ControllerViewApp controller;
    private Vector<JMenuItem> menuItems;
    private Vector<JButton> buttons;
    private Vector<JSpinner> sppiners;
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private JMenuItem menuItemShowConfig;
    private JMenuItem menuItemClose;
    private JMenu menuHelp;
    private JMenuItem menuItemAbout;
    private JPanel pnlSouth;
    private JTextArea tarConsole;
    private JPanel pnlLateral;
    private JLabel lblFps;
    private JButton btnStart;
    private JButton btnNext;
    private JButton btnClear;
    private JButton btnPause;
    private JButton btnRestart;
    private JButton btnClose;
    private JSpinner spinDelay;
    private Animator animator;
    private JButton btnResume;

    public ViewApp() {
        menuItems = new Vector<JMenuItem>();
        buttons = new Vector<JButton>();
        sppiners = new Vector<JSpinner>();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        init();
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void init() {
        setLayout(new BorderLayout());
        setSize(800, 600);
        setTitle(Config.get("APP_NAME"));

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuOptions = new JMenu(Translate.get("GUI_OPTIONS"));
        menuBar.add(menuOptions);

        menuItemShowConfig = new JMenuItem(Translate.get("GUI_SHOWCONFIG"));
        menuOptions.add(menuItemShowConfig);

        menuItemClose = new JMenuItem(Translate.get("GUI_CLOSE"));
        menuOptions.add(menuItemClose);

        menuHelp = new JMenu(Translate.get("GUI_HELP"));
        menuBar.add(menuHelp);

        menuItemAbout = new JMenuItem(Translate.get("GUI_ABOUT"));
        menuHelp.add(menuItemAbout);

        pnlLateral = new JPanel();
        pnlLateral.setLayout(new MigLayout());
        add(pnlLateral, BorderLayout.WEST);

        lblFps = new JLabel(Translate.get("GUI_DELAY"));
        pnlLateral.add(lblFps, "width 50%");
        spinDelay = new JSpinner();
        spinDelay.setModel(new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_DELAY")), 1, 1000, 1));
        pnlLateral.add(spinDelay, "width  50%, wrap");

        btnStart = new JButton(Translate.get("GUI_START"));
        pnlLateral.add(btnStart, "grow, height 25, span 2, wrap 20");

        btnPause = new JButton(Translate.get("GUI_PAUSE"));
        pnlLateral.add(btnPause, "grow, height 25, span 2, wrap");

        btnNext = new JButton(Translate.get("GUI_NEXT"));
        pnlLateral.add(btnNext, "grow, height 25, span 2, wrap");

        btnResume = new JButton(Translate.get("GUI_RESUME"));
        pnlLateral.add(btnResume, "grow, height 25, span 2, wrap 20");

        btnClear = new JButton(Translate.get("GUI_CLEAR"));
        pnlLateral.add(btnClear, "grow, height 25, span 2, wrap");

        btnRestart = new JButton(Translate.get("GUI_RESTART"));
        pnlLateral.add(btnRestart, "grow, height 25, span 2, wrap");

        btnClose = new JButton(Translate.get("GUI_CLOSE"));
        pnlLateral.add(btnClose, "grow, height 25, span 2, wrap");

        pnlSouth = new JPanel();
        pnlSouth.setLayout(new MigLayout());
        add(pnlSouth, BorderLayout.SOUTH);

        JScrollPane scrollPanelConsole = new JScrollPane();
        pnlSouth.add(scrollPanelConsole, "width 100%, height 100");
        tarConsole = new JTextArea();
        DefaultCaret caret = (DefaultCaret) tarConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollPanelConsole.setViewportView(tarConsole);

        animator = new Animator();
        animator.setBackground(Color.WHITE);
        add(animator, BorderLayout.CENTER);

        menuItems.add(menuItemShowConfig);
        menuItems.add(menuItemClose);
        menuItems.add(menuItemAbout);
        buttons.add(btnClear);
        buttons.add(btnClose);
        buttons.add(btnNext);
        buttons.add(btnPause);
        buttons.add(btnRestart);
        buttons.add(btnStart);
        buttons.add(btnResume);
        sppiners.add(spinDelay);
    }

    public JMenuItem getMenuItemShowConfig() {
        return menuItemShowConfig;
    }

    public JMenuItem getMenuItemClose() {
        return menuItemClose;
    }

    public JMenuItem getMenuItemAbout() {
        return menuItemAbout;
    }

    public JTextArea getTarConsole() {
        return tarConsole;
    }

    public JSpinner getSpinDelay() {
        return spinDelay;
    }

    public ControllerViewApp getController() {
        return controller;
    }

    public void setController(ControllerViewApp controller) {
        this.controller = controller;
        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).addActionListener(controller);
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(controller);
        }
        for (int i = 0; i < sppiners.size(); i++) {
            sppiners.get(i).addChangeListener(controller);
        }
        this.addWindowListener(controller);
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public JButton getBtnNext() {
        return btnNext;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JButton getBtnPause() {
        return btnPause;
    }

    public JButton getBtnRestart() {
        return btnRestart;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public Animator getAnimator() {
        return animator;
    }

    public JButton getBtnResume() {
        return btnResume;
    }

}
