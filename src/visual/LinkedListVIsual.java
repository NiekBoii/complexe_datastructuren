package visual;

import datastructuren.LinkedList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class LinkedListVIsual {
    private final LinkedList<String> linkedList = new LinkedList<>();
    public ListView<String> listView = new ListView<>();
    public TextArea itemInput = new TextArea();
    public Button addItem = new Button();
    public Button deleteItem = new Button();
    public TextArea containsItem = new TextArea();

    public void addNewItem() {
        String newItem = itemInput.getText();
        linkedList.add(newItem);
        refresh();
    }

    public void deleteItem() {
        String selectedItem = listView.selectionModelProperty().get().getSelectedItem();
        if (selectedItem != null) {
            linkedList.remove(selectedItem);
            refresh();
        }
    }

    public void containsItem() {
        String selectedItem = itemInput.getText();
        if (selectedItem != null) {
            containsItem.setText(linkedList.contains(selectedItem) ? "True" : "False");
        }
    }

    private void refresh() {
        listView.getItems().clear();
        linkedList.forEach(item -> listView.getItems().add(item));
        listView.refresh();
    }
}
