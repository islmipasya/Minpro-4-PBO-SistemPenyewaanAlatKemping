package main;

import controller.PenyewaanController;
import view.MenuView;

public class main {

    public static void main(String[] args) {

        PenyewaanController controller =
                new PenyewaanController();

        MenuView view =
                new MenuView();

        view.jalankan(controller);
    }
}