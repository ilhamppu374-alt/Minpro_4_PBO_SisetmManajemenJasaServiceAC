/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

/**
 *
 * @author USER
 */
import config.DatabaseConnection;
import controller.ServiceController;
import view.ServiceView;

public class Main {

    public static void main(String[] args) {

        if (!DatabaseConnection.testKoneksi()) {

            System.out.println(
                    "Program dihentikan. Pastikan MySQL di Laragon "
                    + "sudah Start dan database db_service_ac "
                    + "sudah diimport."
            );

            return;
        }

        ServiceController controller =
                new ServiceController();

        ServiceView view =
                new ServiceView(controller);

        view.jalankanProgram();
    }
}
