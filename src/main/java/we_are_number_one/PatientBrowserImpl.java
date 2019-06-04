package we_are_number_one;

import at.spengergasse.hbgm.data.Image;
import at.spengergasse.hbgm.data.Patient;
import at.spengergasse.hbgm.data.Series;
import at.spengergasse.hbgm.data.Study;
import at.spengergasse.hbgm.ui.AbstractPatientBrowser;
import at.spengergasse.hbgm.ui.Viewer;
import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;
//import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.View;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PatientBrowserImpl extends AbstractPatientBrowser {
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Patienten");
    private DefaultTreeModel model = new DefaultTreeModel(root);
    private Viewer v;
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    //private  JTree tree;
    private Image image;
    private JScrollPane qPane;


    public PatientBrowserImpl(){
        //this.setModel(model);

        setCellRenderer(new CellRenderer());
        this.setBorder(new TitledBorder("PatientBrowser"));
        this.setSize(50,60);
    }

    public Patient selectedPatient() {
        TreePath path = this.getSelectionPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        Object userob = node.getUserObject();
        if(userob != null)
        {
            if(node.getUserObject() instanceof Image){
                node = (DefaultMutableTreeNode)node.getParent();
            }
            if(node.getUserObject() instanceof Series){
                node = (DefaultMutableTreeNode)node.getParent();
            }
            if(node.getUserObject() instanceof Study){
                node = (DefaultMutableTreeNode)node.getParent();
            }
            if(node.getUserObject() instanceof Patient){
                return (Patient) node.getUserObject();
            }

        }
        return null;
    }

    public Study selectedStudy() {
        TreePath path = this.getSelectionPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        Object userob = node.getUserObject();
        if(userob != null)
        {
            if(node.getUserObject() instanceof Image){
                node = (DefaultMutableTreeNode)node.getParent();
            }
            if(node.getUserObject() instanceof Series){
                node = (DefaultMutableTreeNode)node.getParent();
            }
            if(node.getUserObject() instanceof Study){
                return (Study) node.getUserObject();
            }

        }

        return null;
    }

    public Series selectedSeries() {
        TreePath path = this.getSelectionPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        Object userob = node.getUserObject();
        if(userob != null)
        {
            if(node.getUserObject() instanceof Image){
                node = (DefaultMutableTreeNode)node.getParent();
            }
            if(node.getUserObject() instanceof Series){
                return (Series) node.getUserObject();
            }

        }return null;
    }

    public Image selectedImage() {
        TreePath path = this.getSelectionPath();
        if(path == null){
            return null;
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        Object userob = node.getUserObject();
        if(userob != null)
        {
            if(node.getUserObject() instanceof Image){
                return (Image) node.getUserObject();
            }

        }return null;
    }

    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void configure(Viewer viewer) {
        this.v = viewer;
        v.getPatientRepository().registerObserver(this);

        tree();
        /*
        this.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {

            }
        });
        */
    }
    private void tree(){


        for(Patient p : v.getPatientRepository().patientList()){
            DefaultMutableTreeNode pNode = new DefaultMutableTreeNode(p);
            root.add(pNode);
            for(Study st : p.getStudyList()){
                DefaultMutableTreeNode stNode = new DefaultMutableTreeNode(st);
                pNode.add(stNode);
                for(Series se : st.getSeriesList()){
                    DefaultMutableTreeNode seNode = new DefaultMutableTreeNode(se);
                    stNode.add(seNode);
                    for(Image i : se.getImageList()){
                        DefaultMutableTreeNode iNode = new DefaultMutableTreeNode(i);
                        seNode.add(iNode);
                    }
                }
            }

        }

        // tree = new JTree(root);
        //JScrollPane treeView = new JScrollPane(tree);
        this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                /* TreePath path = this.getSelectionPath();
                DefaultMutableTreeNode test = new DefaultMutableTreeNode(getSelectionPath());
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                if(node == null){
                    return;
                }
                */
                /*
                TreePath path = getSelectionPath();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
                Object userob = node.getUserObject();
                Image img = (Image)userob;
                image = img;
                */
                for(IObserver o : observers){
                    o.changed(me());
                }
               // System.out.println("SelectionListener");
            }
        });
        setModel(model);

        //qPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //pane = new JScrollPane(this);
        //setScrollsOnExpand(true);
    }

    public void changed(IBaseComponent c) {
        this.tree();
    }

    private PatientBrowserImpl me(){
        return this;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public Image setImage() {
        return null;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

class CellRenderer extends DefaultTreeCellRenderer{

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean selected, boolean expanded, boolean leaf, int row,
                                                      boolean hasFocus) {
        String str = value.toString();

        value = ((DefaultMutableTreeNode) value).getUserObject();
        if(value instanceof Patient){
            str = ((Patient) value).getName();
        }
        if(value instanceof Study){
            str = ((Study)value).getDescription();
        }
        if(value instanceof Series){
            str = ((Series)value).getDescription();
        }
        if(value instanceof Image){
            str = String.valueOf(((Image)value).getInstnr());
        }
        return super.getTreeCellRendererComponent(tree, str, selected, expanded, leaf, row, hasFocus);
    }
}
