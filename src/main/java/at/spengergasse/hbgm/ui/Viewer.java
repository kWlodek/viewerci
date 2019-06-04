package at.spengergasse.hbgm.ui;

import at.spengergasse.hbgm.data.IPatientRepository;
import at.spengergasse.hbgm.image.ILookupTable;
import at.spengergasse.hbgm.image.IPixelMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;


/**
 * This is the main window in which all the viewer components are wired together.
 * The components can obtain references to each other by calling the get-methods
 * of the viewer.
 */

public class Viewer extends JFrame {
    private AbstractPatientBrowser patientBrowser;
    private IPatientRepository patientRepository;
    private IPixelMapper pixelMapper;
    private ILookupTable lookupTable;
    private AbstractImagePanel imagePanel;
    private AbstractControlPanel controlPanel;
    private AbstractInfoPanel infoPanel;

    private JPanel imagePanelLocation = new JPanel(new BorderLayout());
    private JPanel patientBrowserLocation = new JPanel(new BorderLayout());
    private JPanel infoPanelLocation = new JPanel(new BorderLayout());
    private JPanel controlPanelLocation = new JPanel(new BorderLayout());

    public Viewer() {
        super("DICOM Viewer");
        initFrame();
        initMenu();
    }




    public void configure()
    {
        patientRepository.configure(this);
        pixelMapper.configure(this);
        lookupTable.configure(this);
        controlPanel.configure(this);
        imagePanel.configure(this);
        infoPanel.configure(this);
        patientBrowser.configure(this);
    }

    private void initFrame(){
        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(300);
        add(splitPane);
        JPanel left = new JPanel(new GridLayout(0, 1));
        splitPane.add(left, JSplitPane.LEFT);
        splitPane.add(imagePanelLocation, JSplitPane.RIGHT);
        left.add(patientBrowserLocation);
        left.add(infoPanelLocation);
        left.add(controlPanelLocation);
    }

    private void initMenu(){
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);
        JMenu fileMenu = new JMenu("File");
        mbar.add(fileMenu);
        JMenuItem addFolderMenu = new JMenuItem("add folder");
        fileMenu.add(addFolderMenu);
        addFolderMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFolderMenuAction(e);
            }
        });
    }

    private void addFolderMenuAction(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(startDirectory());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (jfc.showDialog(this, "choose folder") == JFileChooser.APPROVE_OPTION)
        {
            startDirectory(jfc.getSelectedFile());
            try {
                patientRepository.addDicomFolder(jfc.getSelectedFile());
              //  JOptionPane.showMessageDialog(this, "folder added: " + jfc.getSelectedFile());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private File startDirectory()
    {
        String dir = Preferences.userRoot().get("Viewer.StartDefaultDir", ".");
        return new File(dir);
    }

    private void startDirectory(File f)
    {
        Preferences.userRoot().put("Viewer.StartDefaultDir", f.getAbsolutePath());
    }




    public void setPatientBrowser(AbstractPatientBrowser patientBrowser) {
        this.patientBrowser = patientBrowser;
        patientBrowserLocation.removeAll();
        patientBrowserLocation.add(new JScrollPane(patientBrowser));
    }

    public void setPatientRepository(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void setPixelMapper(IPixelMapper pixelMapper) {
        this.pixelMapper = pixelMapper;
    }

    public void setLookupTable(ILookupTable lookupTable) {
        this.lookupTable = lookupTable;
    }

    public void setImagePanel(AbstractImagePanel imagePanel) {
        this.imagePanel = imagePanel;
        imagePanelLocation.removeAll();
        imagePanelLocation.add(new JScrollPane(imagePanel));
        imagePanel.repaint();
    }

    public void setControlPanel(AbstractControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        controlPanelLocation.removeAll();
        controlPanelLocation.add(controlPanel);
    }

    public void setInfoPanel(AbstractInfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        infoPanelLocation.removeAll();
        infoPanelLocation.add(new JScrollPane(infoPanel));
    }

    public AbstractPatientBrowser getPatientBrowser() {
        return patientBrowser;
    }

    public IPatientRepository getPatientRepository() {
        return patientRepository;
    }

    public IPixelMapper getPixelMapper() {
        return pixelMapper;
    }

    public ILookupTable getLookupTable() {
        return lookupTable;
    }

    public AbstractImagePanel getImagePanel() {
        return imagePanel;
    }

    public AbstractControlPanel getControlPanel() {
        return controlPanel;
    }

    public AbstractInfoPanel getInfoPanel() {
        return infoPanel;
    }
}
