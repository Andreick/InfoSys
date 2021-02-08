package infosys.controller;

import infosys.dao.ConnectionFactory;
import infosys.view.TelaCadastro;
import util.Look;

public class Principal {

    public static void main(String[] args) {
        Look.configuraLookAndFeel("Windows Classic");
        new TelaCadastro().setVisible(true);
        ConnectionFactory.getConnection();
    }
}
