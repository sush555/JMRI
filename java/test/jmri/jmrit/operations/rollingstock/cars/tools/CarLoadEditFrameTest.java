package jmri.jmrit.operations.rollingstock.cars.tools;

import java.awt.GraphicsEnvironment;
import jmri.jmrit.operations.OperationsTestCase;
import jmri.util.JUnitUtil;
import jmri.util.swing.JemmyUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Operations CarLoadEditFrame class
 *
 * @author	Dan Boudreau Copyright (C) 2009
 */
public class CarLoadEditFrameTest extends OperationsTestCase {

    @Test
    public void testCarLoadEditFrame() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        CarLoadEditFrame f = new CarLoadEditFrame();
        f.initComponents("Boxcar", "");
        f.addTextBox.setText("New Load");
        JemmyUtil.enterClickAndLeave(f.addButton);
        // new load should appear at start of list
        Assert.assertEquals("new load", "New Load", f.loadComboBox.getItemAt(0));
        JUnitUtil.dispose(f);
    }

    // Ensure minimal setup for log4J
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Override
    @After
    public void tearDown() {
        super.tearDown();
    }
}
