import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class SwingFoo extends JPanel {
   private JTextField fileField = new JTextField(20);
   private JButton showDialog = new JButton(new ShowDialogAction("Show Dialog",
         KeyEvent.VK_D, this));

   public SwingFoo() {
      fileField.setEditable(false);
      fileField.setFocusable(false);
      add(new JLabel("File Selected:"));
      add(fileField);
      add(showDialog);
   }

   public void setFileFieldText(String text) {
      fileField.setText(text);
   }

   private static void createAndShowGui() {
      SwingFoo mainPanel = new SwingFoo();

      JFrame frame = new JFrame("SwingFoo");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}

@SuppressWarnings("serial")
class ShowDialogAction extends AbstractAction {
   private SwingFoo swingFoo;

   public ShowDialogAction(String name, int mnemonic, SwingFoo swingFoo) {
      super(name);
      putValue(MNEMONIC_KEY, mnemonic);
      this.swingFoo = swingFoo;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      PatientPicker patientPicker = new PatientPicker();
      int result = JOptionPane.showConfirmDialog(swingFoo, patientPicker,
            "Select Something", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         swingFoo.setFileFieldText(patientPicker.getSelectedItem());
      }
      patientPicker.setVisible(true);
   }
}

@SuppressWarnings("serial")
class PatientPicker extends JPanel {
   private static final String[] ITEMS = {"Sunday", "Monday", "Tuesday", "Wednesday",
         "Thursday", "Friday", "Sunday", "Fubar", "Snafu", "DILLIGAF", "BOHICA"};
   private JList<String> selectionList = new JList<>(ITEMS);

   public PatientPicker() {
      add(new JScrollPane(selectionList));
      selectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

   public String getSelectedItem() {
      return selectionList.getSelectedValue();
   }

}